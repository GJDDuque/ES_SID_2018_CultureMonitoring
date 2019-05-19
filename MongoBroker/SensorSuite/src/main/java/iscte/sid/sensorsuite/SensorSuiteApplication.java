package iscte.sid.sensorsuite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import iscte.sid.sensorsuite.broker.SensorInformationBroker;
import iscte.sid.sensorsuite.model.MqttConfig;
import iscte.sid.sensorsuite.mongo.MongoDb;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SensorSuiteApplication {
	int counter = 0;

	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(SensorSuiteApplication.class);
		sa.run(args);
	}

	
	
	@Autowired
	ApplicationEventPublisher publisher;

	@Primary
	@Bean
	public MongoDb provideMongoDb(@Value("${mongo.server.address}") String mongo_address,
			@Value("${mongo.server.port}") int mongo_port, 
			@Value("${mongo.username}") String username, 
			@Value("${mongo.password}") String password, 
			@Value("${mongo.database}") String database, 
			@Value("${mongo.measurescollection}") String measures_collection,
			@Value("${mongo.errorscollection}") String errors_collection ) {
		return new MongoDb(publisher, mongo_address,mongo_port,username,password,database,measures_collection, errors_collection );
	}

	@Bean
	public ObjectMapper provideObjectMapper() {
		ObjectMapper result = new ObjectMapper();
		result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return result;
	}

	@Bean
	public SensorInformationBroker provideBroker(@Value("${mqtt.qos}") int config_mqtt_qos, 
			@Value("${mqtt.to.start}") int mqtt ,
			@Value("${mqtt.main.client}") String serverUri,
			@Value("${mqtt.main.client}") String topic,
			@Value("${mqtt.backup.client}") String backserverUri,
			@Value("${mqtt.backup.client}") String backTopic,
			@Value("${mqtt.local.client}") String localServerUri,
			@Value("${mqtt.local.client}") String localTopic) {
		log.debug("provider broker called " + counter++ + " time");
		
		switch(mqtt) {
		case 0:
		return new SensorInformationBroker(Integer.valueOf(config_mqtt_qos),
				new MqttConfig(serverUri,topic), new MqttConfig(backserverUri,backTopic));
		case 1:
		return new SensorInformationBroker(Integer.valueOf(config_mqtt_qos),
				new MqttConfig(backserverUri,backTopic),new MqttConfig(backserverUri,backTopic));
		default:
		return new SensorInformationBroker(Integer.valueOf(config_mqtt_qos),
				new MqttConfig(localServerUri,localTopic),new MqttConfig(serverUri,topic));
		}
	}

}
