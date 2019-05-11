package cultura;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import cultura.data.Data;

@Controller
public class DataController {

//	@GetMapping("/welcome")
//	public String welcome(Model model) {
//		model.addAttribute()
//		return welcome";
//	}

	@GetMapping("/welcome")
	public String welcome(@ModelAttribute(name = "userEmail") String userEmail, Model model) {
		String query = "select measured_value from measures where user='" + userEmail + "'";
		LinkedHashMap measuresData = new Data(query).loadData();
		List<Double> charData = new ArrayList<Double>();
		for (LinkedCaseInsensitiveMap data : (ArrayList<LinkedCaseInsensitiveMap>) measuresData.get("#result-set-1")) {
			BigDecimal finalValue = (BigDecimal) data.get(("measured_value"));
			charData.add((double) finalValue.doubleValue());
		}

		model.addAttribute("chartData", charData);
		return "welcome";
	}
//	@GetMapping("/filtro")
//	public String registration(@ModelAttribute("filtro") Data data, BindingResult bindingResult, Model model) {
//		model.addAttribute("graph", new Data("select * from temperature_measure").loadData());
//		return "temperature_measures";
//	}
}
