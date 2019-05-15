package iscte.sid.sensorsuite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import iscte.sid.sensorsuite.broker.SensorInformationBroker;
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
	public MongoDb provideMongoDb() {
		return new MongoDb(publisher);
	}

	@Bean
	public ObjectMapper provideObjectMapper() {
		ObjectMapper result = new ObjectMapper();
		result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return result;
	}

	@Bean
	public SensorInformationBroker provideBroker() {
		log.debug("provider broker called " + counter++ + " time");
		return new SensorInformationBroker();
	}

}
