package com.hm707.mongo.hello;



import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by LH on 2018/1/3.
 */
public class SampleConnect {

	/**
	 * http://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/
	 *
	 * 创建一个数据库连接有多种方式，Mongo已经实现了连接池，并且是线程安全的
	 * MongoClient实例表示一个数据库连接池，他是线程安全的。
	 * 通常只需要一个MongoClient实例
	 *
	 */
	public static void main(String[] args) {

		String host = "192.168.33.132";
		int port = 27017;
		//如果有多个，可以使用逗号隔开 "mongodb://localhost:27017,localhost:27018,localhost:27019"
		String uri = "mongodb://" + host + ":" + port;

		String dbName = "stocks";

		//1. 使用地址和端口创建
		MongoClient mongoClient = new MongoClient(host, port);

		//2. 使用URI创建
		MongoClientURI connectionString = new MongoClientURI(uri);
		MongoClient mongoClient1 = new MongoClient(connectionString);

		//3. 接到一个副本集，需要提供一个列表
		//MongoClient mongoClient2 = new MongoClient(
		//	Arrays.asList(new ServerAddress("localhost", 27017),
		//		new ServerAddress("localhost", 27018),
		//		new ServerAddress("localhost", 27019)));

		//如果一个数据库不存在，当你第一次存储数据库的数据时，MongoDB会创建数据库。MongoDatabase实例是不可变的
		MongoDatabase database = mongoClient.getDatabase(dbName);

		//获取一个集合，如果一个集合不存在，当你第一次存储那个集合的数据的时候，MongoDB会创建这个集合。
		String collection = "values";
		MongoCollection<Document> values = database.getCollection(collection);

		long count = values.count();
		System.out.println(collection + " ==> count [" + count + "]");


	}
}
