package cultura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cultura.measure.MeasureForm;
import cultura.measure.MeasureServiceImpl;

@Controller
public class MeasureController {
	@Autowired
	private MeasureServiceImpl measureServiceImpl;

	@GetMapping("/addMeasure")
	public String addmeasure(Model model, @ModelAttribute String userEmail) {
		model.addAttribute("userEmail", userEmail);
		model.addAttribute("measureForm", new MeasureForm());
		return "addMeasure";
	}

	@PostMapping(value = "/addMeasure")
	public String addmeasure(@ModelAttribute("measureForm") MeasureForm measureForm, BindingResult bindingResult,
			Model model) {
		measureServiceImpl.saveMeasure(measureForm);
		return "redirect:/addMeasure";

	}

}