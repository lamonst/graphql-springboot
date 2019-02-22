package com.graphql.local.repository;

import static com.mongodb.client.model.Filters.eq;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.graphql.local.domain.Vote;
import com.graphql.local.scalars.Scalars;
import com.mongodb.client.MongoCollection;

/**
 * Manages vote persistence
 */
@Repository
public class VoteRepository extends BaseRepository {

	private MongoCollection<Document> votes;

	@PostConstruct
	public void init() {
		votes = getMongoDB().getCollection(getMongoDocument("documents.votes"));
	}

	public List<Vote> findByUserId(String userId) {
		List<Vote> list = new ArrayList<>();
		for (Document doc : votes.find(eq("userId", userId))) {
			list.add(vote(doc));
		}
		return list;
	}

	public List<Vote> findByLinkId(String linkId) {
		List<Vote> list = new ArrayList<>();
		for (Document doc : votes.find(eq("linkId", linkId))) {
			list.add(vote(doc));
		}
		return list;
	}

	public Vote saveVote(Vote vote) {
		Document doc = new Document();
		doc.append("userId", vote.getUserId());
		doc.append("linkId", vote.getLinkId());
		doc.append("createdAt", Scalars.dateTime.getCoercing().serialize(vote.getCreatedAt()));
		votes.insertOne(doc);
		return new Vote(doc.get("_id").toString(), vote.getCreatedAt(), vote.getUserId(), vote.getLinkId());
	}

	private Vote vote(Document doc) {
		// ZonedDateTime.parse(doc.getString("createdAt")),
		return new Vote(doc.get("_id").toString(),
				(ZonedDateTime) Scalars.dateTime.getCoercing().parseLiteral(doc.getDate("createdAt")),
				doc.getString("userId"), doc.getString("linkId"));
	}
}
