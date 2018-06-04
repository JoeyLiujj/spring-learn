package cn.joey.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class TestMongoConnection {
	public static void main(String[] args) {
		MongoCollection<Document> collection = getMongoCollection("localhost",
				27017, "mycol", "test");
		System.out.println(collection);

		insertMongoData(collection);

		iteratorDocument(collection);

		// updateMongoData(collection);

		// removeMongoData(collection);
	}

	public static MongoCollection<Document> getMongoCollection(String host,
			int port, String databaseStr, String collectionStr) {
		MongoClient mongoClient = new MongoClient(host, port);
		MongoDatabase database = mongoClient.getDatabase(databaseStr);
		MongoCollection<Document> collection = database
				.getCollection(collectionStr);
		return collection;
	}

	public static void insertMongoData(MongoCollection<Document> collection) {
		/*
		 * 创建文档org.bson.Document参数为key-value的格式
		 * 创建文档集合List<Document>将文档集合插入数据库集合中mongoCollection.insertMany(List<Document>)
		 * 插入单个文档可以用insertOne()
		 */
		Document document = new Document("title", "MongoDB 教程")
				.append("description", "database").append("likes", 100)
				.append("by", "lily");
		List<Document> documents = new ArrayList<Document>();
		documents.add(document);
		collection.insertMany(documents);
		System.out.println("插入文档成功");
	}

	public static void iteratorDocument(MongoCollection<Document> collection) {
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> iterator = findIterable.iterator();
		while (iterator.hasNext()) {
			Document next = iterator.next();
			System.out.println(next);
		}
	}

	public static void updateMongoData(MongoCollection<Document> collection) {
		/*
		 * 更新文档
		 * 将文档中的likes=100的文档修改为likes=200
		 */
		collection.updateMany(Filters.eq("likes", 100), new Document("$set",
				new Document("likes", 200)));

		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> iterator = findIterable.iterator();
		while (iterator.hasNext()) {
			Document next = iterator.next();
			System.out.println(next);
		}
	}

	public static void removeMongoData(MongoCollection<Document> collection) {
		// 删除符合条件的第一条数据
		collection.deleteOne(Filters.eq("likes", 200));

		// 删除所有符合条件的数据
		collection.deleteMany(Filters.eq("likes", 200));
		// 检索查询结果
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> iterator = findIterable.iterator();
		while (iterator.hasNext()) {
			Document next = iterator.next();
			System.out.println(next);
		}
	}

	public static MongoDatabase getDatabase() {
		MongoDatabase database = null;
		try {
			ServerAddress serverAddress = new ServerAddress("localhost", 27017);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);

			MongoCredential credential = MongoCredential
					.createScramSha1Credential("username", "databaseName",
							"password".toCharArray());
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
			credentials.add(credential);

			// 通过连接认证获取MongoDB连接
			MongoClient mongoClient = new MongoClient(addrs, credentials);
			// 连接到数据库
			database = mongoClient.getDatabase("databaseName");
			System.out.println("Connect to database Successfully");
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return database;
	}

}
