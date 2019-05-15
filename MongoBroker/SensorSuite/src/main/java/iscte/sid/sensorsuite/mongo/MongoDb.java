package iscte.sid.sensorsuite.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import iscte.sid.sensorsuite.model.SensorAdminError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MongoDb {
	public static MongoDb instance;
	private MongoDatabase database;
	private MongoCollection<Document> dbsensormeasures;
	private MongoCollection<Document> dbsensorerrors;
	private MongoClient mongoClient;

	private ApplicationEventPublisher eventPublisher;

	/**
	 * 
	 */
	public MongoDb(ApplicationEventPublisher eventPublisher) {
		super();
		this.eventPublisher = eventPublisher;

		try {
			setupDatabaseConnection();
		} catch (Exception e) {
			log.error("this one I catched...", e);

		}

		log.debug("MongoDb initialized with the UUID " + UUID.randomUUID());
	}

	private void setupDatabaseConnection() {

		MongoCredential userCredential = MongoCredential.createCredential("sid_31", "sid", "secret".toCharArray());
		mongoClient = new MongoClient(new ServerAddress("localhost", 27017), userCredential,
				MongoClientOptions.builder().build());
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
		try {
			dbsensormeasures.insertOne(message);
		} catch (Exception e) {

			eventPublisher.publishEvent(new SensorAdminError(new Date().getTime(), this.getClass().getName()));
		}

	}

	public void addErrorEntry(String messageSensor) {
		Document message = new Document();
		message.put("timestamp", new Date().getTime());
		message.put("message", messageSensor);
		message.put("errortype", "generic_error");
		message.put("processed", 0);
		log.debug("saved error message -> " + message);
		dbsensorerrors.insertOne(message);
	}

	public void addErrorEntry(String messageSensor, String sensorType, String errortype) {
		Document message = new Document();
		message.put("timestamp", new Date().getTime());
		message.put("sensorvalue", messageSensor);
		message.put("sensortype", sensorType);
		message.put("errortype", errortype);
		message.put("processed", 0);
		log.debug("saved error message -> " + message);
		dbsensorerrors.insertOne(message);
	}

	public List<String> getValidReadings(long starttime, long endtime) {
		List<String> result = new ArrayList<>();
		if (starttime > 0 && endtime > starttime) {
			dbsensormeasures.find(Filters.and(Filters.gt("timestamp", starttime), Filters.lte("timestamp", endtime)))
					.forEach((Document b) -> result.add(b.toJson()));
		} else {
			if (endtime == 0 && starttime > 0) {
				dbsensormeasures.find(Filters.gt("timestamp", starttime))
						.forEach((Document b) -> result.add(b.toJson()));
			} else {
				if (endtime > 0 && starttime == 0) {
					dbsensormeasures.find(Filters.lte("timestamp", endtime))
							.forEach((Document b) -> result.add(b.toJson()));
				} else {
					dbsensormeasures.find().forEach((Document b) -> result.add(b.toJson()));
				}
			}
		}
		log.debug("Getting a number of " + result.size() + " items from " + starttime + " to " + endtime);
		return result;
	}

	public void terminate() {
		mongoClient.close();
	}

}