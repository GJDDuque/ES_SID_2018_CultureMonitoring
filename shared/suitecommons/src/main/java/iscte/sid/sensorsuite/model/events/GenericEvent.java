package iscte.sid.sensorsuite.model.events;

import lombok.Data;

@Data
public class GenericEvent {
	public final static int ERROR = 0;
	public final static int SUCCESS = 1;
	private String identifier;
	private long timestamp;
	private int type;
	private String method;
	/**
	 * @param identifier
	 * @param method 
	 * @param timestamp
	 * @param type
	 */
	public GenericEvent(String identifier, String method, long timestamp, int type) {
		super();
		this.identifier = identifier;
		this.method = method;
		this.timestamp = timestamp;
		this.type = type;
	}	
}
