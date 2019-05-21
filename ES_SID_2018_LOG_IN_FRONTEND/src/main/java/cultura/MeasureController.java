package cultura;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cultura.measure.MeasureForm;
import cultura.measure.MeasureServiceImpl;
import culture.cultures.Culture;
import culture.cultures.CultureServiceImpl;

@Controller
public class MeasureController {
	@Autowired
	private MeasureServiceImpl measureServiceImpl;
	
	private Culture culture;
	
	private CultureServiceImpl cultureServiceImpl = new CultureServiceImpl();

	@GetMapping("/addMeasure/{userEmail}")
	public String addmeasure(Model model, @PathVariable String userEmail) {
		
		List<String> cultures = cultureServiceImpl.findByResponsible(userEmail);
		
		model.addAttribute("cultures", cultures);
			
		model.addAttribute("userEmail", userEmail);
		model.addAttribute("measureForm", new MeasureForm());
		return "addMeasure";
	}

	@PostMapping(value = "/addMeasure")
	public String addmeasure(@ModelAttribute("measureForm") MeasureForm measureForm, BindingResult bindingResult,
			Model model) {
		measureServiceImpl.saveMeasure(measureForm);
		return "redirect:/welcome";
	}

}
