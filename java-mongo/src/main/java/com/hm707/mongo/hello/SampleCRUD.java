package com.hm707.mongo.hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.bson.BsonValue;
import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

/**
 * Created by LH on 2018/1/3.
 */
public class SampleCRUD {
	private static MongoClient mongoClient;
	private static MongoDatabase database;
	private static MongoCollection<Document> collection ;

	static {
		String host = "192.168.33.132";
		int port = 27017;
		mongoClient = new MongoClient(host, port);

		database = mongoClient.getDatabase("mydb");

		collection = database.getCollection("test");

	}
	public static void main(String[] args) {
		/** create **/
		//testCreateOne();
		//testCreateMultiple();

		/** count **/
		//System.out.println(collection.count());

		/** findFirst **/
		//If the collection is empty, the operation returns null.
		Document myDoc = collection.find().first();

		System.out.println(myDoc.toJson());

		System.out.println("---------------");

		/** findMany **/
		//testFindMany();

		//这种方式虽然是允许的，但是如果循环中出错，会导致游标无法关闭.
		//for (Document cur : collection.find()) {
		//	System.out.println(cur.toJson());
		//}


		/** filter **/
		//testFilter();

		/** update **/
		testUpdate();
	}

	private static void testUpdate() {

		UpdateResult result = collection.updateOne(Filters.eq("i", 10), new Document("$set", new Document("i", 110)));
		BsonValue value = result.getUpsertedId();
		System.out.println(value);
	}

	private static void testFilter() {
		Document myDoc = collection.find(Filters.eq("i", 71)).first();
		System.out.println(myDoc.toJson());

		Block<Document> block = document -> System.out.println(document.toJson());

		//
		collection.find(Filters.and(Filters.gt("i", 50), Filters.lte("i", 60))).forEach(block);

	}

	private static void testFindMany() {
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}
	}

	private static void testCreateMultiple() {
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < 100; i++) {
			documents.add(new Document("i", i));
		}
		collection.insertMany(documents);
	}

	private static void testCreateOne() {
		Document doc = new Document("name", "MongoDB")
			.append("type", "database")
			.append("count", 1)
			.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
			.append("info", new Document("x", 203).append("y", 102));

		collection.insertOne(doc);

	}

}
