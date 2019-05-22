package cultura;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import culture.cultures.Culture;
import culture.cultures.CultureServiceImpl;

@Controller
public class CultureController {

	private CultureServiceImpl cultureServiceImpl = new CultureServiceImpl();

	@GetMapping("/addCulture/{userEmail}")
	public String AddCulture(Model model, @PathVariable String userEmail) {
		model.addAttribute("userEmail", userEmail );
		model.addAttribute("cultureForm", new CultureForm());
		return "addCulture";
	}

	@PostMapping(value = "/addCulture")
	public String AddCulture(@ModelAttribute("cultureForm") CultureForm cultureForm, BindingResult bindingResult, Model model) {
		cultureServiceImpl.saveCulture(culture);
		return "redirect:/addCulture";

	}
}
