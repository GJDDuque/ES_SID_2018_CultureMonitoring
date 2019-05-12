package cultura;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cultura.data.Data;
import cultura.utilities.AjaxResponseBody;

@RestController
public class DataController {

//	@GetMapping("/welcome")
//	public String welcome(Model model) {
//		model.addAttribute()
//		return welcome";
//	}

//	@RequestMapping("/welcome/chart")
//	public List<Double> getMeasures(@RequestParam(name = "userEmail") String userEmail, Model model) {
//		String query = "select measured_value from measures where user='" + userEmail + "'";
//		return new Data(query).loadMeasures();
//	}

	@RequestMapping("/welcome/chart")
	public AjaxResponseBody getMeasures(@RequestParam(name = "userEmail") String userEmail, Model model) {
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg("response");
		response.setResult(new Data("measured_value", "measures", "user", userEmail).loadMeasures());
		return response;
	}

//	@GetMapping("/filtro")
//	public String registration(@ModelAttribute("filtro") Data data, BindingResult bindingResult, Model model) {
//		model.addAttribute("graph", new Data("select * from temperature_measure").loadData());
//		return "temperature_measures";
//	}
}
