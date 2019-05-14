package iscte.sid.sensorsuite.model;

import lombok.Data;

@Data
public class MqttConfig {
private String address;
private String topic;
/**
 * @param address
 * @param topic
 */
public MqttConfig(String address, String topic) {
	super();
	this.address = address;
	this.topic = topic;
}


}
