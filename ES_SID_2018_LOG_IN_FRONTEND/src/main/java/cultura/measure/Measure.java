package cultura.measure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "measures")
public class Measure {

	
	public Measure() {
		
	}
	
	@Id
	@Column
	private long measure_id;
	@Column
	private String date_time;
	@Column
	private Double measured_value;
	@Column
	private String user;
	@Column
	private int measured_variable_id;
	
	public long getMeasureId() {
		return measure_id;
	}
	
	public void setMeasureId(long measure_id) {
		this.measure_id = measure_id;
	}
	public String getDatetime() {
		return date_time;
	}
	
	public void setDatetime(String date_time) {
		this.date_time = date_time;
	}
	public Double getMeasuredValue() {
		return measured_value;
	}
	
	public void setMeasuredValue(Double measured_value) {
		this.measured_value = measured_value;
	}
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	public int getMeasuredVariableId() {
		return measured_variable_id;
	}
	
	public void getMeasuredVariableId(int measured_variable_id) {
		this.measured_variable_id = measured_variable_id;
	}

}
