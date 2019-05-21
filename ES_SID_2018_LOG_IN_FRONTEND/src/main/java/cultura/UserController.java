package cultura;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cultura.data.Data;
import cultura.data.Filters;
import cultura.user.User;
import cultura.user.UserServiceImpl;
import cultura.user.UserValidator;
import cultura.utilities.AjaxResponseBody;
import culture.cultures.Culture;
import culture.cultures.CultureServiceImpl;

@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private UserValidator userValidator;

	private User user;

	private Culture culture;
	
	private CultureServiceImpl cultureServiceImpl = new CultureServiceImpl();
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}

	@PostMapping(value = "/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
			RedirectAttributes atributes) {
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		userServiceImpl.saveUser(userForm);
		atributes.addFlashAttribute("user", userForm);
		return "redirect:/homepage";
	}

	@GetMapping({ "/", "/login" })
	public String login(Model model, String error, String logout) {
		model.addAttribute("userForm", new User());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,
			RedirectAttributes atributes) {
		user = userServiceImpl.findByEmail(userForm.getEmail());
		if (user.getPassword().equals(userForm.getPassword())) {
			atributes.addFlashAttribute("user", user);
			if(user.getProfessional_category().equals("Administrador"))
				return "redirect:/admin";
			else
				return "redirect:/homepage";

		} else {
			model.addAttribute("logError", "logError");
			return "/login";
		}

	}
	
	@GetMapping("/homepage")
	public String homepage(Model model) {
		return "homepage";
	}
	
	@GetMapping("/admin")
	public String adminDash(Model model) {
		return "admin";
	}
	
	@GetMapping("/welcome/{culture}")
	public String success(Model model,@PathVariable String culture) {
		if (user != null) {
			List<String> cultures = cultureServiceImpl.findByResponsible(user.getEmail());
			
			model.addAttribute("cultures", cultures);
			model.addAttribute("cultura", culture);
			model.addAttribute("userEmail", user.getEmail());
			model.addAttribute("filters", new Filters());
			if(user.getProfessional_category().equals("Administrador"))
				return "redirect:/admin";
			return "welcome";
		} else {
			return "redirect:/login";
		}

	}

}