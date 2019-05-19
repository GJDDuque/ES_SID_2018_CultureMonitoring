package iscte.sid.mysqlsuite.sensors;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import iscte.sid.mysqlsuite.managers.AlarmManager;
import iscte.sid.sensorsuite.model.LightMeasure;
import iscte.sid.sensorsuite.model.TemperatureMeasure;
import iscte.sid.sensorsuite.model.communication.ReadingsRequest;
import iscte.sid.sensorsuite.model.communication.ReadingsResponse;
import iscte.sid.sensorsuite.model.events.GenericEvent;
import iscte.sid.sensorsuite.model.exceptions.ArgumentNullExeption;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SensorManagerImpl implements SensorManager, SensorConnectionListener {
	private String sensorAddress;

	@Autowired
	private AlarmManager am;

	private int sensorWatcherInterval;

	private HttpClient client;

	private String check_statusMethod;

	private String queryMethod;

	private int sensorQueryInterval;

	/**
	 * 
	 */
	public SensorManagerImpl() {
		super();
		
	}

	/**
	 * 
	 * @param sensorAddress
	 * @param check_statusMethod
	 * @param queryMethod
	 * @param sensorWatcherInterval
	 * @param sensorQueryInterval
	 */
	public SensorManagerImpl(String sensorAddress, String check_statusMethod, String queryMethod,
			int sensorWatcherInterval, int sensorQueryInterval) {
		super();
		this.sensorAddress = sensorAddress;
		this.check_statusMethod = check_statusMethod;
		this.queryMethod = queryMethod;
		this.sensorWatcherInterval = sensorWatcherInterval;
		this.sensorQueryInterval = sensorQueryInterval;
		client = HttpClientBuilder.create().build();
		setUpSensorWatcher();
		connectToSensors();
	}

	private void connectToSensors() {
		Thread t = new Thread(new Runnable() {

			private long lastcall = 0;

			@Override
			public void run() {

				while (!Thread.interrupted()) {
					HttpPost post = new HttpPost(sensorAddress + queryMethod);
					post.setHeader("Accept", "application/json");
					post.setHeader("Content-type", "application/json; charset=utf-8");
					log.debug("printing lastcall value" + lastcall);
					ObjectMapper mapper = new ObjectMapper();
					mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					long now = new Date().getTime();
					try {

						post.setEntity(new StringEntity(mapper.writeValueAsString(new ReadingsRequest(lastcall, now))));
						client.execute(post, new ResponseHandler<ReadingsResponse>() {

							@Override
							public ReadingsResponse handleResponse(HttpResponse response)
									throws ClientProtocolException, IOException {
								ReadingsResponse result = mapper.readValue(response.getEntity().getContent(), ReadingsResponse.class);
								result.getReadings().forEach(t-> {
									try {
										am.addMeasure(TemperatureMeasure.convertFromString(t, mapper));
										am.addMeasure(LightMeasure.convertFromString(t, mapper));
									} catch (IOException | ArgumentNullExeption e) {
										
									}
								});
								return null;
							}
						});
						lastcall = now;
					} catch (IOException e) {
						log.error("Error getting the measures", e);
					}
					
					try {
						Thread.sleep(sensorQueryInterval * 1000);
					} catch (InterruptedException e) {
						log.debug(Thread.currentThread().getName() + " was interrupted...");

					}
				}
			}
		}, "Sensor Requester Thread");
		t.start();
	}

	private void setUpSensorWatcher() {
		Thread t = new Thread(
				new SensorsWatchDog(client, sensorAddress, check_statusMethod, this, sensorWatcherInterval),
				"SensorWatchDog Thread");
		t.start();

	}

	@Override
	public void onError(String sensorAddress, String method, long timestamp) {
		am.addEvent(new GenericEvent(sensorAddress, method, timestamp, GenericEvent.ERROR));
	}

	@Override
	public void onSucess(String sensorAddress, String method, long timestamp) {
		am.addEvent(new GenericEvent(sensorAddress, method, timestamp, GenericEvent.SUCCESS));
	}

}
