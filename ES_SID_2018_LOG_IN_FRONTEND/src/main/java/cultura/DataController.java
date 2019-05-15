package cultura;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cultura.data.Data;
import cultura.utilities.AjaxResponseBody;

@RestController
public class DataController {
	private String userEmail;

	@RequestMapping(method = RequestMethod.POST, path = "/welcome/chart")
	public AjaxResponseBody getMeasures(@RequestBody String userEmail) {
		this.userEmail = userEmail.replaceAll("\"", "");
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg("response");
		response.setYAxis(
				new Data("select measured_value from measures where user = " + userEmail + "order by date_time asc ")
						.loadMeasures());
		response.setXAxis(
				new Data("select date_time from measures where user = " + userEmail + "order by date_time asc ")
						.loadDate());
		return response;
	}

//	@RequestMapping("/welcome/chart")
//	public List<Double> getMeasures(@RequestParam(name = "userEmail") String userEmail, Model model) {
//		return new Data("select measure_value from measures where user = " + userEmail).loadMeasures();
//	}

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

	@RequestMapping(value = "/addMeasure/user", method = RequestMethod.GET)
	public AjaxResponseBody getUserEmail(Model model) {
		AjaxResponseBody response = new AjaxResponseBody(this.userEmail, "response");
		return response;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}