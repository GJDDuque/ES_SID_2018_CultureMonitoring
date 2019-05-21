
package cultura.measure;

public class MeasureForm {

	private String culture;
	private String variable;
	private Double measured_value;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public Double getMeasured_value() {
		return measured_value;
	}

	public void setMeasured_value(Double measured_value) {
		this.measured_value = measured_value;
	}
}
