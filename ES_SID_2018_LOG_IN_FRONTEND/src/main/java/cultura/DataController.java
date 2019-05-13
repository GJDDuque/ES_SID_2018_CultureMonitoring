package cultura;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

//	, Model model
	
	@RequestMapping(method = RequestMethod.POST,path = "/welcome/chart")
	public AjaxResponseBody getMeasures(@RequestBody String userEmail) {
		System.out.println(userEmail);
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg("response");
		response.setResult(new Data("select measured_value from measures where user = " + userEmail).loadMeasures());
		return response;
	}

//	@GetMapping("/filtro")
//	public String registration(@ModelAttribute("filtro") Data data, BindingResult bindingResult, Model model) {
//		model.addAttribute("graph", new Data("select * from temperature_measure").loadData());
//		return "temperature_measures";
//	}
}
