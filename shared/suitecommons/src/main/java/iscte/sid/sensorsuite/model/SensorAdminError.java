package iscte.sid.sensorsuite.model;

import lombok.Data;

@Data
public class SensorAdminError {
private long timestamp;
private String componentName;


/**
 * @param timestamp
 * @param componentName
 */
public SensorAdminError(long timestamp, String componentName) {
	super();
	this.timestamp = timestamp;
	this.componentName = componentName;
}


}
