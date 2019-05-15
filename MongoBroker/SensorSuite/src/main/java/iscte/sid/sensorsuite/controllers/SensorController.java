package iscte.sid.sensorsuite.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iscte.sid.sensorsuite.model.communication.CheckStatusRequest;
import iscte.sid.sensorsuite.model.communication.CheckStatusResponse;
import iscte.sid.sensorsuite.model.communication.ReadingsRequest;
import iscte.sid.sensorsuite.model.communication.ReadingsResponse;
import iscte.sid.sensorsuite.mongo.MongoDb;

@RestController
public class SensorController {

	private long startdate;
	private long lastrequestdate;

	@Autowired
	MongoDb db;

	/**
	 * 
	 */
	public SensorController() {
		super();
		this.startdate = new Date().getTime();
	}

	@PostMapping("check-status")
	public CheckStatusResponse provideCheckStatus(@RequestBody CheckStatusRequest request) {
		if (validateRequest(request)) {
			return new CheckStatusResponse(startdate, lastrequestdate);
		}
		return null;
	}

	@PostMapping("readings")
	public ReadingsResponse getReadings(@RequestBody ReadingsRequest request) {
		if (validateRequest(request)) {
			long starttime = request.getStartTime();
			long endtime = request.getEndTime();
			ReadingsResponse result = new ReadingsResponse(db.getValidReadings(starttime, endtime));
			lastrequestdate = new Date().getTime();
			return result;
		}
		return null;
	}

	private boolean validateRequest(Object request) {
		return request != null;

	}
}
