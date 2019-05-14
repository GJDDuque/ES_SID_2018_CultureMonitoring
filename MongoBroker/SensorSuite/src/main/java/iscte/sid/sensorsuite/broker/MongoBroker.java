package iscte.sid.sensorsuite.broker;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import com.fasterxml.jackson.databind.ObjectMapper;

import iscte.sid.sensorsuite.helpers.PropertiesHelper;
import iscte.sid.sensorsuite.model.MeasuresFromSensors;
import iscte.sid.sensorsuite.model.MqttConfig;
import iscte.sid.sensorsuite.mongo.MongoDb;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class MongoBroker {

	 private static final String SID_31_2019 = "SID-31-2019";
	private MqttAsyncClient client;
	private ObjectMapper mapper;
	private MongoDb db;
	
	
	public MongoBroker() {
		super();
		 mapper = new ObjectMapper();
		createClient(PropertiesHelper.getInstance().getConfiguredClient());
		db = new MongoDb();
	}

	private void createClient(MqttConfig config) {
		try {
			setClient(new MqttAsyncClient(config.getAddress(), SID_31_2019));
		} catch (MqttException e) {
			e.printStackTrace();
			System.out.println("not connected...");
			setClient(null);
			System.err.println("client is null and mongo broker is useless now...");
			return;
		}
		
		setCallBack(config.getTopic());
		
		connectToMqtt();
	}

	private void connectToMqtt() {
		try {
			MqttConnectOptions options = new MqttConnectOptions();
			options.setConnectionTimeout(60000);
			getClient().connect(options,null, new IMqttActionListener() {
				
				public void onSuccess(IMqttToken asyncActionToken) {
					System.out.println("connected ");
					
				}
				
				public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
					tryAnotherMqtt();
					System.out.println("not connected " + exception);
					
				}
			});
		} catch (MqttSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setCallBack(String topic) {
		getClient().setCallback(new MqttCallbackExtended() {
			
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				
				db.addEntry(message.toString());
				System.out.println(message.toString().replace("\"\"","\",\"" ));
				MeasuresFromSensors messageObj;
				try {
					 messageObj = mapper.readValue(message.toString().replace("\"\"","\",\"" ), MeasuresFromSensors.class);
					if(messageObj.getCell().equals(null) || messageObj.getTmp().equals(null)) {
						db.addErrorEntry(message.toString());
					}
				} catch (Exception e) {
					log.error("error on the messge:" + message,e);
					db.addErrorEntry(message.toString());
				}
				
			}
			
			public void deliveryComplete(IMqttDeliveryToken token) {
				// TODO Auto-generated method stub
				
			}
			
			public void connectionLost(Throwable cause) {
				// TODO Auto-generated method stub
				
			}
			
			public void connectComplete(boolean reconnect, String serverURI) {
				System.out.println("connected to " + serverURI);
				try {
					System.out.println("subscribing to " + topic);
					client.subscribe(topic, 0);
				} catch (MqttException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
	
	protected void tryAnotherMqtt() {
		System.out.println("Trying another client...");
		createClient(PropertiesHelper.getInstance().getBackupClient());
	}



@SuppressWarnings("unused")
	public static void main(String[] args) {

		MongoBroker john = new MongoBroker();


}


	public MqttAsyncClient getClient() {
		return client;
	}


	public void setClient(MqttAsyncClient client) {
		this.client = client;
	}
	
	public void terminate() {
		
		try {
			client.close(true);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.terminate();
	}
}
