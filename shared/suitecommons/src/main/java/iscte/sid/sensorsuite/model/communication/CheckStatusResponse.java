package iscte.sid.sensorsuite.model.communication;

import lombok.Data;

@Data
public class CheckStatusResponse {
	private long startdate;
	private long lastrequestdate;
	/**
	 * @param startdate
	 * @param lastrequestdate
	 */
	public CheckStatusResponse(long startdate, long lastrequestdate) {
		super();
		this.startdate = startdate;
		this.lastrequestdate = lastrequestdate;
	}
	

}
