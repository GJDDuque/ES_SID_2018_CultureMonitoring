package cultura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cultura.data.Data;
import cultura.user.UserServiceImpl;
import cultura.utilities.AjaxResponseBody;

@RestController
public class DataController {
	private String userEmail;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping(method = RequestMethod.GET, value = "/welcome/temperature")
	public AjaxResponseBody getMeasuresTemperature(@RequestParam(name = "userEmail") String userEmail, @RequestParam(name = "culture") String culture) {
		this.userEmail = userEmail.replaceAll("\"", "");
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg("response");
		response.setYAxis(new Data("select measured_value from measures m "
				+ "join measured_variables mv on mv.measured_variables_id = m.measured_variable_id "
				+ "join variables v on mv.variable_id = v.variable_id join cultures c on c.culture_id = mv.culture_id"
				+ " where v.variable_name = 'Temperature' "
				+ "and m.user = '" + userEmail + "' and c.culture_name = '" + culture + "' order by date_time asc").loadMeasures());
		response.setXAxis(new Data("select date_time from measures m "
				+ "join measured_variables mv on mv.measured_variables_id = m.measured_variable_id "
				+ "join variables v on mv.variable_id = v.variable_id join cultures c on c.culture_id = mv.culture_id"
				+ " where v.variable_name = 'Temperature' "
				+ "and m.user = '" + userEmail + "' and c.culture_name = '" + culture + "' order by date_time asc").loadDate());
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/welcome/Light")
	public AjaxResponseBody getMeasuresLight(@RequestParam(name = "userEmail") String userEmail, @RequestParam(name = "culture") String culture) {
		this.userEmail = userEmail.replaceAll("\"", "");
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg("response");
		response.setYAxis(new Data("select measured_value from measures m "
				+ "join measured_variables mv on mv.measured_variables_id = m.measured_variable_id "
				+ "join variables v on mv.variable_id = v.variable_id join cultures c on c.culture_id = mv.culture_id"
				+ " where v.variable_name = 'Light' "
				+ "and m.user = '" + userEmail + "' and c.culture_name = '" + culture +  "' order by date_time asc").loadMeasures());
		response.setXAxis(new Data("select date_time from measures m "
				+ "join measured_variables mv on mv.measured_variables_id = m.measured_variable_id "
				+ "join variables v on mv.variable_id = v.variable_id join cultures c on c.culture_id = mv.culture_id"
				+ " where v.variable_name = 'Light' "
				+ "and m.user = '" + userEmail + "' and c.culture_name = '" + culture + "' order by date_time asc").loadDate());
		return response;
	}

	// O sensor é desnecessario
	@RequestMapping(value = "/welcome/filters", method = RequestMethod.GET)
	public AjaxResponseBody getFilteredMeasures(@RequestParam(name = "dataB") String dataB,
			@RequestParam(name = "dataF") String dataF, @RequestParam(name = "measureL") Double measureL,
			@RequestParam(name = "measureH") Double measureH, @RequestParam(name = "culture") String culture,
			@RequestParam(name = "sensor") String sensor, Model model) {

		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg("response");
		response.setYAxis(new Data("select measured_value from measures m", dataB, dataF, measureL, measureH, culture,
				sensor, this.userEmail).loadMeasures());
		response.setXAxis(new Data("select date_time from measures m", dataB, dataF, measureL, measureH, culture,
				sensor, this.userEmail).loadDate());
		return response;
	}

	@RequestMapping(value = "/addMeasure/user", method = RequestMethod.POST)
	public AjaxResponseBody getUserEmail(Model model) {
		AjaxResponseBody response = new AjaxResponseBody(this.userEmail, "response");
		return response;
	}

	@RequestMapping(value = "/welcomeAdmin/getUsers", method = RequestMethod.POST)
	public AjaxResponseBody getUserDetails(Model model) {
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg("response");
//		if (profCat != null) {
//			response.setUsers(userServiceImpl.findAllUsersByProfessionalCategory(profCat));
//		} else {
			response.setUsers(userServiceImpl.findAllUsers());
//		}
		return response;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}