package cultura.alerts;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "alerts")
public class Alert {

	public Alert() {

	}

	@Id
	@Column
	private long id_alerts;
	@Column
	private Timestamp date_time;
	@Column
	private String variable_name;
	@Column
	private String lower_limit;
	@Column
	private Double upper_limit;
	@Column
	private Double measured_value;
	@Column
	private String culture_name;
	@Column
	private String type;
	@Column
	private int sensor_id;
	@Column
	private int user_id;
	@Column
	private String description;

	public long getId_alert() {
		return id_alerts;
	}

	public void setId_alert(long id_alerts) {
		this.id_alerts = id_alerts;
	}

	public Timestamp getDate_time() {
		return date_time;
	}

	public void setDate_time(Timestamp date_time) {
		this.date_time = date_time;
	}

	public String getVariable_name() {
		return variable_name;
	}

	public void setVariable_name(String variable_name) {
		this.variable_name = variable_name;
	}

	public String getLower_limit() {
		return lower_limit;
	}

	public void setLower_limit(String lower_limit) {
		this.lower_limit = lower_limit;
	}

	public Double getUpper_limit() {
		return upper_limit;
	}

	public void setUpper_limit(Double upper_limit) {
		this.upper_limit = upper_limit;
	}

	public Double getMeasured_value() {
		return measured_value;
	}

	public void setMeasured_value(Double measured_value) {
		this.measured_value = measured_value;
	}

	public String getCulture_name() {
		return culture_name;
	}

	public void setCulture_name(String culture_name) {
		this.culture_name = culture_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSensor_id() {
		return sensor_id;
	}

	public void setSensor_id(int sensor_id) {
		this.sensor_id = sensor_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}