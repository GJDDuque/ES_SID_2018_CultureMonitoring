package culturas.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@Column(name = "id_Role")
	private int id_role;
	@Id
	@Column
	private String role;
	@Transient
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
