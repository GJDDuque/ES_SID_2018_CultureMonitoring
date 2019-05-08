package culturas.user;

public interface UserService {

	void saveUser(User user);

	User findByUsername(String username);

	User findByEmail(String email);
}
