package cultura.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
public class User {

	public User() {

	}

	@Id
	@Column
	private long user_id;
	@Id
	@Column
	private String email;
	@Column
	private String name;
	@Column
	private String professional_category;
	@Column
	private String password;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfessional_category() {
		return professional_category;
	}

	public void setProfessional_category(String professional_category) {
		this.professional_category = professional_category;
	}

	@Id
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setActive(int i) {
		// TODO Auto-generated method stub

	}

}