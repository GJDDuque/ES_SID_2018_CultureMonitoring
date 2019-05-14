package cultura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cultura.measure.Measure;
import cultura.measure.MeasureServiceImpl;
import cultura.user.UserValidator;

@Controller
public class MeasureController {
	@Autowired
	private MeasureServiceImpl measureServiceImpl;
	
	@GetMapping("/addmeasure" )
	public String addmeasure(Model model) {
		model.addAttribute("measureForm", new Measure());
		return "addmeasure";
	}
	
	
	@PostMapping(value = "/addmeasure")
	public String addmeasure(@ModelAttribute("measureForm") Measure measureForm, BindingResult bindingResult, Model model,
			RedirectAttributes atributes) {
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		measureServiceImpl.save(measureForm);
		atributes.addFlashAttribute("measure", measureForm);
		return "redirect:/addmeasure";


	}

}
