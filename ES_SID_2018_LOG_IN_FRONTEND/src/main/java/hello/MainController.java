package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

	@GetMapping("/login")
	public String logIn(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/result")
	public String greetingSubmit(User user, Model model) {
		return "result";
	}

}