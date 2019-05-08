package culturas;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import culturas.user.User;
import culturas.user.UserRepository;
import culturas.user.UserServiceImpl;
import culturas.user.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private DataSource dataSource;
	private SimpleJdbcCall jdbcCall;
	private JdbcTemplate template;

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

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		model.addAttribute("userForm", new User());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		String mapping;
// Como se faz um stored procedure....

//		template = new JdbcTemplate(dataSource);
//		jdbcCall = new SimpleJdbcCall(template).withProcedureName("anyQuery");
//		String aux = "select email, password from users where email = '" + userForm.getEmail() + "'
//		MapSqlParameterSource paramMap = new MapSqlParameterSource().addValue("selectcommand", aux);
//		User user = (User) jdbcCall.execute(paramMap); {
//
		User user = userServiceImpl.findByEmail(userForm.getEmail());
		if (user.getPassword().equals(userForm.getPassword())) {
			mapping = "redirect:/welcome";
		} else {
			mapping = "/login";
		}
		return mapping;

	}

	@GetMapping({ "/", "/welcome" })
	public String welcome(Model model) {
		return "welcome";
	}
}