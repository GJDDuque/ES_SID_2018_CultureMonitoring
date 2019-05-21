package cultura;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cultura.variable.Variable;
import cultura.variable.VariableServiceImpl;

@Controller
public class VariableController {
	private VariableServiceImpl variableServiceImpl = new VariableServiceImpl();

	@GetMapping("/addVariable")
	public String addvariable(Model model) {
		return "addVariable";
	}

	@PostMapping(value = "/addVariable")
	public String addvariable(@ModelAttribute("variable") Variable variable, BindingResult bindingResult,
			Model model) {
		variableServiceImpl.saveVariable(variable);
		return "redirect:/addVariable";

	}
}
