package iscte.sid.sensorsuite.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.context.ApplicationEventPublisher;

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
public class MongoDb {
	public static MongoDb instance;
	private MongoDatabase database;
	private MongoCollection<Document> dbsensormeasures;
	private MongoCollection<Document> dbsensorerrors;
	private MongoClient mongoClient;

	private ApplicationEventPublisher eventPublisher;
	private String mongoAddress;
	private int mongoPort;
	private String user;
	private String password;
	private String databaseName;
	private String measuresCollection;
	private String errorsCollection;

	/**
	 * @param mongo_address 
	 * 
	 */
	public MongoDb(ApplicationEventPublisher eventPublisher, String mongo_address, int mongo_port, String user, String password, String database, String measures_collection, String errors_collection) {
		super();
		this.eventPublisher = eventPublisher;
		this.mongoAddress = mongo_address;
		this.mongoPort = mongo_port;
		this.user = user;
		this.password = password;
		this.databaseName = database;
		this.measuresCollection = measures_collection;
		this.errorsCollection = errors_collection;


		try {
			setupDatabaseConnection();
		} catch (Exception e) {
			log.error("this one I catched...", e);

		}

		log.debug("MongoDb initialized with the UUID " + UUID.randomUUID());
	}

	/**
	 * Prepara a ligação com a base de dados usando os elementos guardados no application.properties incluindo autenticação.
	 */
	private void setupDatabaseConnection() {

		MongoCredential userCredential = MongoCredential.createCredential(user, databaseName, password.toCharArray());
		mongoClient = new MongoClient(new ServerAddress(mongoAddress, mongoPort), userCredential,
				MongoClientOptions.builder().build());
		database = mongoClient.getDatabase(databaseName);
		dbsensormeasures = database.getCollection(measuresCollection);
		dbsensorerrors = database.getCollection(errorsCollection);

	}

	/**
	 * Metodo utilizado para adicionar uma nova entrada às medições, esta entrada coloca a data actual,
	 * em combinação com a mensagem recebida do sensor e ainda
	 * uma flag de processamento para indicar se esta medida já foi pedida pelo broker de mysql  
	 * @param messageSensor é a mensagem a colocar na colecção
	 */
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

	/**
	 * Normalmente esta introdução seria feita através do mapeamento de um objecto completo, 
	 * mas devido a restrições de tem é colocado campo a campo dentro de um documento generico e salvo assim
	 *
	 * A mensagem do erro
	 * @param errorMessage
	 */
	public void addErrorEntry(String errorMessage) {
		Document message = new Document();
		message.put("timestamp", new Date().getTime());
		message.put("message", errorMessage);
		message.put("errortype", "generic_error");
		message.put("processed", 0);
		log.debug("saved error message -> " + message);
		dbsensorerrors.insertOne(message);
	}

	/**
	 * Metodo usado para salvar as medições que tenham algum tipo de erro (valores nulos, campos inexistentes...)
	 * @param messageSensor a mensagem completa vinda do sensor
	 * @param sensorType o tipo de sensor (Luz ou Temperatura)
	 * @param errortype a mensagem de error associada às excepções capturadas.
	 */
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

	/**
	 * Método responsavel pela obtenção das leituras entre datas, após a obtenção das datas o campo processed é alterado para 1.
	 * @param starttime indica a data em numero longo de inicio do filtro 
	 * @param endtime indica o final do filtro
	 * @return a lista de medidas que se encontrem entre os parametros.
	 */
	public List<String> getValidReadings(long starttime, long endtime) {
		List<String> result = new ArrayList<>();

		//filtro com os tempos maiores que 0 e em que o tempo de fim é maior do que o de inicio
		if (starttime > 0 && endtime > starttime) {
			Bson filter = Filters.and(Filters.gt("timestamp", starttime), Filters.lte("timestamp", endtime),
					Filters.ne("processed", 1));
			dbsensormeasures.find(filter).forEach((Consumer<? super Document>)  b  -> {
				result.add(b.toJson());

			});
			dbsensormeasures.updateMany(filter, new Document("$set", new Document("processed", 1)));
		} else {
			//filtro apenas com o tempo de inicio
			if (endtime == 0 && starttime > 0) {
				Bson filter = Filters.and(Filters.gt("timestamp", starttime), Filters.ne("processed", 1));
				dbsensormeasures.find(filter).forEach((Consumer<? super Document>)  b -> {
					result.add(b.toJson());

				});
				dbsensormeasures.updateMany(filter, new Document("$set", new Document("processed", 1)));
			} else {
				//filtro apenas com o tempo de fim
				if (endtime > 0 && starttime == 0) {
					Bson filter = Filters.and(Filters.lte("timestamp", endtime), Filters.ne("processed", 1));
					dbsensormeasures.find(filter).forEach((Consumer<? super Document>)  b -> {
						result.add(b.toJson());

					});
					dbsensormeasures.updateMany(filter, new Document("$set", new Document("processed", 1)));
				} else {
					//filtro sem tempos
					dbsensormeasures.find(Filters.ne("processed", 1)).forEach((Consumer<? super Document>)  b  -> {
						result.add(b.toJson());

					});
					dbsensormeasures.updateMany(Filters.ne("processed", 1),
							new Document("$set", new Document("processed", 1)));
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
