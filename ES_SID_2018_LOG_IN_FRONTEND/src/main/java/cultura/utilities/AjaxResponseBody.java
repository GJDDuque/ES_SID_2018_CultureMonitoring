package cultura.utilities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import cultura.alerts.Alert;
import cultura.user.User;

public class AjaxResponseBody {

	@JsonView(Views.Public.class)
	private String msg;

	@JsonView(Views.Public.class)
	private String code;

	@JsonView(Views.Public.class)
	private List<Double> YAxis;

	@JsonView(Views.Public.class)
	private List<String> XAxis;

	@JsonView(Views.Public.class)
	private String userEmail;
	
	@JsonView(Views.Public.class)
	private List<User> users;
	
	public List<Alert> getAlert() {
		return Alert;
	}

	public void setAlert(List<Alert> alert) {
		Alert = alert;
	}

	@JsonView(Views.Public.class)
	private List<Alert> Alert;
	

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public AjaxResponseBody() {

	}

	public AjaxResponseBody(String userEmail, String msg) {
		this.userEmail = userEmail;
		this.msg = msg;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<Double> getYAxis() {
		return YAxis;
	}

	public void setYAxis(List<Double> yAxis) {
		YAxis = yAxis;
	}

	public List<String> getXAxis() {
		return XAxis;
	}

	public void setXAxis(List<String> xAxis) {
		XAxis = xAxis;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}