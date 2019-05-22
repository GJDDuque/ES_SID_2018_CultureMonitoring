package culture.cultures;

public class CultureForm {

	private long culture_id;
	private String name;
	private String culture_responsible;
	private String culture_description;
	private Double temperature_lower_limit;
	private Double temperature_upper_limit;
	private Double light_lower_limit;
	private Double light_upper_limit;

	public Double getTemperature_lower_limit() {
		return this.temperature_lower_limit;
	}

	public void setTemperature_lower_limit(Double limit) {
		this.temperature_lower_limit = limit;
	}

	public Double getTemperature_upper_limit() {
		return this.temperature_upper_limit;
	}

	public void setTemperature_upper_limit(Double limit) {
		this.temperature_upper_limit = limit;
	}

	public Double getLight_lower_limit() {
		return this.light_lower_limit;
	}

	public void setLight_lower_limit(Double limit) {
		this.light_lower_limit = limit;
	}

	public Double getLight_upper_limit() {
		return this.light_upper_limit;
	}

	public void setLight_upper_limit(Double limit) {
		this.light_upper_limit = limit;
	}

	public long getCulture_id() {
		return culture_id;
	}

	public void setCulture_id(long culture_id) {
		this.culture_id = culture_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCulture_responsible() {
		return culture_responsible;
	}

	public void setCulture_responsible(String culture_responsible) {
		this.culture_responsible = culture_responsible;
	}

	public String getCulture_description() {
		return culture_description;
	}

	public void setCulture_description(String culture_description) {
		this.culture_description = culture_description;
	}
}
