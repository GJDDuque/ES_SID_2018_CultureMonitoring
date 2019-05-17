package culture.cultures;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "cultures")
public class Culture {
	
	public Culture() {
	}
	
	@Id
	@Column
	private long culture_id;
	@Column
	private String name;
	@Column
	private String culture_responsible;
	@Column
	private String culture_description;
	
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
