package iscte.sid.sensorsuite.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import iscte.sid.sensorsuite.model.exceptions.ArgumentNullExeption;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TemperatureMeasure extends AbstractMeasure{
//{"tmp":"26.00","hum":"48.50","dat":"12/5/2019","tim":"18:41:29","cell":"232""sens":"eth"}
	private double tmp;
	private String sens;
	public static TemperatureMeasure convertFromString(String message, ObjectMapper mapper) throws JsonParseException, JsonMappingException, IOException, ArgumentNullExeption {
			TemperatureMeasure temMeasure = mapper.readValue(message.replace("\"\"", "\",\""),
					TemperatureMeasure.class);
		
			return temMeasure;
	}
	
	 
}
