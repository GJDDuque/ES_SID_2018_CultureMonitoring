package culture.cultures;

public class CultureForm {
	private double temperature_lower_limit;
	private double temperature_upper_limit;
	private double light_lower_limit;
	private double light_upper_limit;

	public double getTemperature_lower_limit() {
		return this.temperature_lower_limit;
	}

	public void setTemperature_lower_limit(double limit) {
		this.temperature_lower_limit = limit;
	}
	
	public double getTemperature_upper_limit() {
		return this.temperature_upper_limit;
	}

	public void setTemperature_upper_limit(double limit) {
		this.temperature_upper_limit = limit;
	}

	public double getLight_lower_limit() {
		return this.light_lower_limit;
	}

	public void setLight_lower_limit(double limit) {
		this.light_lower_limit = limit;
	}
	
	public double getLight_upper_limit() {
		return this.light_upper_limit;
	}

	public void setLight_upper_limit(double limit) {
		this.light_upper_limit = limit;
	}


}
