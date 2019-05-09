package culturas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import culturas.data.Data;

@Controller
public class DataController {

	@GetMapping("/temperature_measures")
	public String registration(Model model) {
		model.addAttribute("graph", new Data("select * from temperature_measure").loadData());
		return "temperature_measures";
	}

	@PostMapping("/temperature_measures")
	public String registration(@ModelAttribute("graph") Data data, BindingResult bindingResult) {
		return "temperature_measures";
	}
}
