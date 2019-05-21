package cultura.variable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "variables")
public class Variable {

	public Variable() {
		
	}
	
	@Id
	@Column
	private long variable_id;
	@Id
	@Column
	private String variable_name;
	
	public Variable(String variable_name) {
		this.variable_name = variable_name;
	}
	
	public long getVariable_id() {
		return variable_id;
	}
	
	public void setVariable_id(long variable_id) {
		this.variable_id = variable_id;
	}
	
	public String getVariable_name() {
		return variable_name;
	}
	
	public void setVariable_name(String variable_name) {
		this.variable_name = variable_name;
	}
	
}
