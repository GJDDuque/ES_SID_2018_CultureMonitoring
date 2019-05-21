package cultura.user;

import java.util.List;

public interface UserService {

	void saveUser(User user);

	User findByUsername(String username);

	User findByEmail(String email);

	List<User> findAllUsers();

	List<User> findAllUsersByProfessionalCategory(String Professional_category);

	void deleteUser(User user);	
}
