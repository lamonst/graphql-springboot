package com.graphql.local.repository;

import static com.mongodb.client.model.Filters.eq;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.graphql.local.domain.User;
import com.mongodb.client.MongoCollection;

/**
 * Manages user persistence
 */
@Repository
public class UserRepository extends BaseRepository {

	private MongoCollection<Document> users;

	@PostConstruct
	public void init() {
		users = getMongoDB().getCollection(getMongoDocument("documents.users"));
	}

	public User findByEmail(String email) {
		Document doc = users.find(eq("email", email)).first();
		return user(doc);
	}

	public User findById(String id) {
		Document doc = users.find(eq("_id", new ObjectId(id))).first();
		return user(doc);
	}

	public User saveUser(User user) {
		Document doc = new Document();
		doc.append("name", user.getName());
		doc.append("email", user.getEmail());
		doc.append("password", user.getPassword());
		users.insertOne(doc);
		return new User(doc.get("_id").toString(), user.getName(), user.getEmail(), user.getPassword());
	}

	private User user(Document doc) {
		return new User(doc.get("_id").toString(), doc.getString("name"), doc.getString("email"),
				doc.getString("password"));
	}
}
