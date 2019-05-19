package iscte.sid.sensorsuite.model.communication;

import lombok.Data;

@Data
public class ReadingsRequest {
	private long startTime;
	private long endTime;

	/**
	 * @param starttime
	 * @param endtime
	 */
	public ReadingsRequest(long starttime, long endtime) {
		super();
		this.startTime = starttime;
		this.endTime = endtime;
	}

	/**
	 * 
	 */
	public ReadingsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
