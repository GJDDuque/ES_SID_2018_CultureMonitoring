package cultura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import cultura.user.User;
import cultura.user.UserServiceImpl;
import cultura.user.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private UserValidator userValidator;

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@PostMapping(value = "/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userServiceImpl.saveUser(userForm);

		return "redirect:/welcome";
	}

	@GetMapping({ "/", "/login" })
	public String login(Model model, String error, String logout) {
		model.addAttribute("userForm", new User());
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		ModelAndView modelAndView = null;
		User user = userServiceImpl.findByEmail(userForm.getEmail());
		if (user.getPassword().equals(userForm.getPassword())) {
			return new ModelAndView("redirect:/welcome?" + "userEmail=" + user.getEmail());
		} else {
			return new ModelAndView("/login");
		}

	}

	@GetMapping({ "/welcome" })
	public String success(Model model) {
		return "welcome";
	}

}