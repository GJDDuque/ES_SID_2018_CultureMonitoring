package culture.cultures;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "cultures")
public class Culture {
	@Id
	@Column
	private long culture_id;
	@Column
	private String culture_name;
	@ManyToOne
	@JoinColumn(name = "email", table = "users")
	private String culture_responsible;
	@Column
	private String culture_description;

	public Culture() {
	}

	public Culture(String name, String culture_responsible, String culture_description) {
		this.culture_description = culture_description;
		this.culture_responsible = culture_responsible;
		this.culture_name = name;
	}

	public long getCulture_id() {
		return culture_id;
	}

	public void setCulture_id(long culture_id) {
		this.culture_id = culture_id;
	}

	public String getName() {
		return culture_name;
	}

	public void setName(String name) {
		this.culture_name = name;
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
