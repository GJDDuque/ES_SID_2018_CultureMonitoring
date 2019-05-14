package iscte.sid.sensorsuite.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import iscte.sid.sensorsuite.model.MqttConfig;


public class PropertiesHelper {
	public static final String MQTT_MAIN_CLIENT = "mqtt_main_client";
	public static final String MQTT_BACKUP_CLIENT = "mqtt_backup_client";
	public static final String MQTT_MAIN_TOPIC = "mqtt_main_topic";
	public static final String MQTT_BACKUP_TOPIC = "mqtt_backup_topic";

	public static final String MQTT_LOCAL_CLIENT = "mqtt_local_client";
	public static final String MQTT_LOCAL_TOPIC = "mqtt_local_topic";
	
	public static final String MQTT_TO_START="mqtt_to_start";

	private Properties prop;

	public PropertiesHelper() {
		loadProperties();
	}

	private static PropertiesHelper instance = null;

	public static PropertiesHelper getInstance() {
		if (instance == null)
			instance = new PropertiesHelper();
		return instance;
	}

	private void loadProperties() {
		try (InputStream input = new FileInputStream("config.properties")) {

			prop = new Properties();

			// load a properties file
			prop.load(input);

		} catch (IOException ex) {
			System.err.println("error loading properties"+ ex);
		}
	}
	
	public String getProperties(String propertyName) {
		return prop.getProperty(propertyName);
	}
	
	public int getIntProperties(String propertyName) {
		return Integer.parseInt(prop.getProperty(propertyName, "0"));	
	}
	
	public MqttConfig getMainClient() {
		return new MqttConfig( prop.getProperty(MQTT_MAIN_CLIENT),prop.getProperty(MQTT_MAIN_TOPIC));
	}
	
	public MqttConfig getBackupClient() {
		return new MqttConfig( prop.getProperty(MQTT_BACKUP_CLIENT),prop.getProperty(MQTT_BACKUP_TOPIC));
	}
	public MqttConfig getLocalClient() {
		return new MqttConfig( prop.getProperty(MQTT_LOCAL_CLIENT),prop.getProperty(MQTT_LOCAL_TOPIC));
	}

	public MqttConfig getConfiguredClient() {

		switch (getIntProperties(MQTT_TO_START)) {
		case 0:
			return getMainClient();
			
		case 1:
			return getBackupClient();
		default:
			return getLocalClient();
		}
	}

}
