package iscte.sid.sensorsuite.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class MeasuresFromSensors{
//{"tmp":"26.00","hum":"48.50","dat":"12/5/2019","tim":"18:41:29","cell":"232""sens":"eth"}
	private String tmp;
	private String hum;
	private String dat;
	private String tim;
	private String cell;
	private String sens;
	 
	public MeasuresFromSensors() {
		
	}
	
}
