package com.graphql.local.repository;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.graphql.local.domain.Link;
import com.graphql.local.filter.LinkFilter;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

/**
 * Manages link persistence
 */

@Repository
public class LinkRepository extends BaseRepository {

	private MongoCollection<Document> links;

	@PostConstruct
	public void init() {
		links = getMongoDB().getCollection(getMongoDocument("documents.links"));
	}

	public Link findById(String id) {
		Document doc = links.find(eq("_id", new ObjectId(id))).first();
		return link(doc);
	}

	public List<Link> getAllLinks(LinkFilter filter, int skip, int first) {
		Optional<Bson> mongoFilter = Optional.ofNullable(filter).map(this::buildFilter);

		List<Link> allLinks = new ArrayList<>();
		FindIterable<Document> documents = mongoFilter.map(links::find).orElseGet(links::find);
		for (Document doc : documents.skip(skip).limit(first)) {
			allLinks.add(link(doc));
		}
		return allLinks;
	}

	public Link saveLink(Link link) {
		Document doc = new Document();
		doc.append("url", link.getUrl());
		doc.append("description", link.getDescription());
		doc.append("postedBy", link.getUserId());
		links.insertOne(doc);

		return new Link(doc.getObjectId("_id").toString(), link.getUrl(), link.getDescription(), link.getUserId());
	}

	private Bson buildFilter(LinkFilter filter) {
		String descriptionPattern = filter.getDescriptionContains();
		String urlPattern = filter.getUrlContains();
		Bson descriptionCondition = null;
		Bson urlCondition = null;
		if (descriptionPattern != null && !descriptionPattern.isEmpty()) {
			descriptionCondition = regex("description", ".*" + descriptionPattern + ".*", "i");
		}
		if (urlPattern != null && !urlPattern.isEmpty()) {
			urlCondition = regex("url", ".*" + urlPattern + ".*", "i");
		}
		if (descriptionCondition != null && urlCondition != null) {
			return and(descriptionCondition, urlCondition);
		}
		return descriptionCondition != null ? descriptionCondition : urlCondition;
	}

	private Link link(Document doc) {
		return new Link(doc.get("_id").toString(), doc.getString("url"), doc.getString("description"),
				doc.getString("postedBy"));
	}
}
