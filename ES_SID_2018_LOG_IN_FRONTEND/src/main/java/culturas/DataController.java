package culturas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import culturas.data.Data;

@Controller
public class DataController {

//	@GetMapping("/welcome")
//	public String registration(Model model) {
//		model.addAttribute("graph", new Data("select * from temperature_measure").loadData());
//		return "welcome";
//	}
	

//	@GetMapping("/filtro")
//	public String registration(@ModelAttribute("filtro") Data data, BindingResult bindingResult, Model model) {
//		model.addAttribute("graph", new Data("select * from temperature_measure").loadData());
//		return "temperature_measures";
//	}
}
