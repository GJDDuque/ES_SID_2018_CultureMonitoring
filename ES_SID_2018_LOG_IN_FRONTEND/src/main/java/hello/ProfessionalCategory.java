package hello;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "professional_categories")
public class ProfessionalCategory {

	@Id
	@Column(name = "professional_category")
	private String professionalCategory;
	@Column
	private String professional_categories;
	@Column
	private String cultures;
	@Column
	private String light_measures;
	@Column
	private String temperature_measures;
	@Column
	private String measures;
	@Column
	private String measured_variables;
	@Column
	private String variables;
	@Column
	private String users;
	@Column
	private String system;
	@Column
	private String alerts;
	@Column
	private String logs_users;
	@Column
	private String logs_measures;
	@Column
	private String logs_cultures;
	@Column
	private String logs_select;
	@Column
	private String logs_professional_categories;
	@Column
	private String addUser;
	@Column
	private String selectMeasures;

	public String getProfessional_category() {
		return professionalCategory;
	}

	public void setProfessional_category(String professional_category) {
		this.professionalCategory = professional_category;
	}

	public String getProfessional_categories() {
		return professional_categories;
	}

	public void setProfessional_categories(String professional_categories) {
		this.professional_categories = professional_categories;
	}

	public String getCultures() {
		return cultures;
	}

	public void setCultures(String cultures) {
		this.cultures = cultures;
	}

	public String getLight_measures() {
		return light_measures;
	}

	public void setLight_measures(String light_measures) {
		this.light_measures = light_measures;
	}

	public String getTemperature_measures() {
		return temperature_measures;
	}

	public void setTemperature_measures(String temperature_measures) {
		this.temperature_measures = temperature_measures;
	}

	public String getMeasures() {
		return measures;
	}

	public void setMeasures(String measures) {
		this.measures = measures;
	}

	public String getMeasured_variables() {
		return measured_variables;
	}

	public void setMeasured_variables(String measured_variables) {
		this.measured_variables = measured_variables;
	}

	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getAlerts() {
		return alerts;
	}

	public void setAlerts(String alerts) {
		this.alerts = alerts;
	}

	public String getLogs_users() {
		return logs_users;
	}

	public void setLogs_users(String logs_users) {
		this.logs_users = logs_users;
	}

	public String getLogs_measures() {
		return logs_measures;
	}

	public void setLogs_measures(String logs_measures) {
		this.logs_measures = logs_measures;
	}

	public String getLogs_cultures() {
		return logs_cultures;
	}

	public void setLogs_cultures(String logs_cultures) {
		this.logs_cultures = logs_cultures;
	}

	public String getLogs_select() {
		return logs_select;
	}

	public void setLogs_select(String logs_select) {
		this.logs_select = logs_select;
	}

	public String getLogs_professional_categories() {
		return logs_professional_categories;
	}

	public void setLogs_professional_categories(String logs_professional_categories) {
		this.logs_professional_categories = logs_professional_categories;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getSelectMeasures() {
		return selectMeasures;
	}

	public void setSelectMeasures(String selectMeasures) {
		this.selectMeasures = selectMeasures;
	}

}