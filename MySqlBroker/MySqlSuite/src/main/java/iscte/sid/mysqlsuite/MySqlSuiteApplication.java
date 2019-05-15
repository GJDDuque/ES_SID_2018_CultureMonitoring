package iscte.sid.mysqlsuite;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import iscte.sid.mysqlsuite.sensors.SensorManager;
import iscte.sid.mysqlsuite.sensors.SensorManagerImpl;

@SpringBootApplication
public class MySqlSuiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySqlSuiteApplication.class, args);
	}
	
	@Value("${sensor.address}")
	private String sensorAddress;
	
	@Value("${sensor.checkstatus.rate}")
	private int watchDogRefreshRate;
	
	@Value("${sensor.query.rate}")
	private int sensorQueryInterval;
	
	@Value("${sensor.checkstatus.method}")
	private String checkstatusMethod;
	
	@Value("${sensor.query.method}")
	private String queryMethod;

	
	@Bean
	SensorManager provideSensorManager() {
		return new SensorManagerImpl(sensorAddress,checkstatusMethod,queryMethod, watchDogRefreshRate,sensorQueryInterval );
	}

}
