package cultura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cultura.variable.VariableForm;
import cultura.variable.VariableServiceImpl;

@Controller
public class VariableController {
	@Autowired
	private VariableServiceImpl variableServiceImpl;
	
	@GetMapping("/addVariable")
	public String addvariable(Model model) {
		return "addVariable";
	}
	
	@PostMapping(value = "/addVariable")
	public String addvariable(@ModelAttribute("variableForm") VariableForm variableForm, BindingResult bindingResult,
			Model model) {
		variableServiceImpl.saveVariable(variableForm);
		return "redirect:/addVariable";	

	}
}
