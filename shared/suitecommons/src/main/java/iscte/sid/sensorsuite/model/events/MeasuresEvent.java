package iscte.sid.sensorsuite.model.events;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MeasuresEvent {
	
//	"(`date_time`," + 
//	"`variable_name`," + 
//	"`lower_limit`," + 
//	"`upper_limit`," + 
//	"`measured_value`," + 
//	"`culture_name`," + 
//	"`type`," + 
//	"`sensor_id`," + 
//	"`user_id`," + 
//	"`description`)" + 
private Timestamp date_time;
private String variable_name;
private int lower_limit;
private int upper_limit;
private int measured_value;
private String culture_name;
private String type;
private int sensor_id;
private int user_id;
private String description;


	public MeasuresEvent() {
		
	}


	/**
	 * @param date_time
	 * @param variable_name
	 * @param lower_limit
	 * @param upper_limit
	 * @param measured_value
	 * @param culture_name
	 * @param type
	 * @param sensor_id
	 * @param user_id
	 * @param description
	 */
	public MeasuresEvent(Timestamp date_time, String variable_name, int lower_limit, int upper_limit,
			int measured_value, String culture_name, String type, int sensor_id, int user_id, String description) {
		super();
		this.date_time = date_time;
		this.variable_name = variable_name;
		this.lower_limit = lower_limit;
		this.upper_limit = upper_limit;
		this.measured_value = measured_value;
		this.culture_name = culture_name;
		this.type = type;
		this.sensor_id = sensor_id;
		this.user_id = user_id;
		this.description = description;
	}


	public MeasuresEvent(Timestamp timestamp, String variableName, int ll, int ul, double tmp, String culture_name,
			int variableType, int i, int j, String string) {
		this.date_time = timestamp;
		this.variable_name = variableName;
		this.lower_limit = ll;
		this.upper_limit = ul;
		this.measured_value = (int) tmp;
		this.culture_name = culture_name;
		this.type =""+variableType;
		this.sensor_id = i;
		this.user_id = j;
		this.description = string;
	}

}
