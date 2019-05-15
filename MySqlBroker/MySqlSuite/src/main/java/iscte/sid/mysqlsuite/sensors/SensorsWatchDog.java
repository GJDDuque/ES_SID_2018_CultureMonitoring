package iscte.sid.mysqlsuite.sensors;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import iscte.sid.sensorsuite.model.communication.CheckStatusRequest;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class SensorsWatchDog implements Runnable {

	private HttpClient client;
	private String sensorAddress;
	private SensorConnectionListener listener;
	private UUID id;
	private String check_statusMethod;
	private int sensorWatcherInterval;
	
	
	/**
	 * @param client
	 * @param sensorAddress
	 * @param check_statusMethod 
	 * @param sensorWatcherInterval 
	 * @param sensorManagerImpl 
	 */
	public SensorsWatchDog(HttpClient client, String sensorAddress, String check_statusMethod, SensorConnectionListener listener, int sensorWatcherInterval) {
		super();
		this.check_statusMethod = check_statusMethod;
		this.listener = listener;
		this.sensorWatcherInterval = sensorWatcherInterval;
		id = UUID.randomUUID();
		log.info("Initialized WatchDog for " + sensorAddress + " with the id " + id);
		this.client = client;
		this.sensorAddress = sensorAddress ;
	}


	@Override
	public void run() {
		
		while(!Thread.interrupted()) {
		HttpPost post = new HttpPost(sensorAddress+check_statusMethod);
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-type", "application/json; charset=utf-8");
		ObjectMapper mapper = new ObjectMapper();
			
			try {
				post.setEntity(new StringEntity(mapper.writeValueAsString(new CheckStatusRequest())) );
				client.execute(post);
				listener.onSucess(sensorAddress,check_statusMethod, new Date().getTime());
			} catch (IOException e) {
				listener.onError(sensorAddress, check_statusMethod,new Date().getTime());
			}
		
			try {
				Thread.sleep(sensorWatcherInterval*1000);
			} catch (InterruptedException e) {
				log.debug(Thread.currentThread().getName() + " was interrupted...");

			}
		}
	

	}

}
