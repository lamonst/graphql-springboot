package com.graphql.local.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.graphql.local.resolvers.BaseResolver;
import com.graphql.local.util.PropertiesUtils;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class BaseRepository {

	private static final String SCHEMA_DB = "schema.db";
	private static final String FILE_NAME_PROPERTIES = "mapper_db.properties";
	private static final Logger log = LoggerFactory.getLogger(BaseResolver.class);

	private static MongoDatabase mongoDB;

	@SuppressWarnings("resource")
	public MongoDatabase getMongoDB() {
		try {
			if (mongoDB != null) {
				return mongoDB;
			}
			String dbName = (String) PropertiesUtils.obterPropriedades(FILE_NAME_PROPERTIES, SCHEMA_DB);
			mongoDB = new MongoClient().getDatabase(dbName);
		} catch (Exception e) {
			log.error("Error: ", e);
		}
		return mongoDB;
	}

	public String getMongoDocument(String keyProperty) {
		try {
			return (String) PropertiesUtils.obterPropriedades("mapper_db.properties", keyProperty);
		} catch (Exception e) {
			log.error("Error: ", e);
		}
		return null;
	}
}
