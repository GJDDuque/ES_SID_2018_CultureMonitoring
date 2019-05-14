package iscte.sid.sensorsuite.mongo;

import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MongoDb {
	public static MongoDb instance;
	private MongoDatabase database;
	private MongoCollection<Document> dbsensormeasures;
	private MongoCollection<Document> dbsensorerrors;
	private MongoClient mongoClient;
	
	public static MongoDb getInstance() {
		if(instance==null)
			instance=new MongoDb();
		return instance;
	}

	/**
	 * 
	 */
	public MongoDb() {
		super();
		setupDatabaseConnection();
		// TODO Auto-generated constructor stub
	}
	private void setupDatabaseConnection() {
		
		MongoCredential userCredential = MongoCredential.createCredential("sid_31", "sid", "secret".toCharArray());
		mongoClient = new MongoClient(new ServerAddress("localhost", 27017),  userCredential,MongoClientOptions.builder().build());
		database = mongoClient.getDatabase("sid");
		dbsensormeasures = database.getCollection("sensors-measures");
		dbsensorerrors = database.getCollection("sensors-errors");

	}
	
	public void addEntry(String messageSensor) {
		Document message = new Document();
		message.put("timestamp", new Date().getTime());
		message.put("sensorvalue", messageSensor);
		message.put("processed", 0);
		log.debug("saved message -> " + message);
		dbsensormeasures.insertOne(message);
		
	}
	public void addErrorEntry(String messageSensor) {
		Document message = new Document();
		message.put("timestamp", new Date().getTime());
		message.put("sensorvalue", messageSensor);
		message.put("processed", 0);
		log.debug("saved error message -> " + message);
		dbsensorerrors.insertOne(message);
	}
	
	public void terminate() {
		mongoClient.close();
	}
	

}
