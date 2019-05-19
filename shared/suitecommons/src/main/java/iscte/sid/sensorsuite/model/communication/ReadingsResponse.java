package iscte.sid.sensorsuite.model.communication;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ReadingsResponse {
	List<String> readings;

	/**
	 * @param readings
	 */
	public ReadingsResponse(List<String> readings) {
		super();
		this.readings = readings;
	}

	public ReadingsResponse() {
		super();
		this.readings = new ArrayList<String>();
	}

}
