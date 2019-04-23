package hello;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping(path = "/index")
public class MainController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/login")
	public String logIn(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/result")
	public String greetingSubmit(User user, Model model) {
		model.addAttribute("user", getUser(user).get());
		return "result";
	}

	public @ResponseBody Optional<User> getUser(User user) {
		// This returns a JSON or XML with the users
		return userRepository.findById(user.getEmail());
	}
}