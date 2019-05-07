package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

	private UserRepository userRepository;
	private ProfessionalCategoryRepository professionalCategoryRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, ProfessionalCategoryRepository professionalCategoryRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.professionalCategoryRepository = professionalCategoryRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
//		ProfessionalCategory profCat = professionalCategoryRepository.findByProfessionalCategory("ADMIN");
//		user.setProfessional_categories(new HashSet<ProfessionalCategory>(Arrays.asList(profCat)));
		userRepository.save(user);
	}

}
