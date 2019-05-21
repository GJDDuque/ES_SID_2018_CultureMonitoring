package cultura.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cultura.role.RoleRepository;
import cultura.utilities.StoredProceduresService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	private StoredProceduresService storedProcedureService;

	@Override
	public void saveUser(User user) {
		storedProcedureService = new StoredProceduresService();
		storedProcedureService.Configure("addUser");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", user.getName());
		map.put("email", user.getEmail());
		map.put("professional_category", user.getProfessional_category());
		map.put("password", user.getPassword());
		storedProcedureService.SetParams(map);
		storedProcedureService.VoidExecute();
	}

	@Override
	public List<User> findAllUsersByProfessionalCategory(String profCat) {
		return userRepository.findAllByProfessionalCategory(profCat);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByName(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public StoredProceduresService getStoredProcedureService() {
		return storedProcedureService;
	}

	public void setStoredProcedureService(StoredProceduresService storedProcedureService) {
		this.storedProcedureService = storedProcedureService;
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);

	}
}
