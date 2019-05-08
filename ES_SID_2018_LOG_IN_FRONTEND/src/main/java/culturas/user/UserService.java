package culturas.user;

public interface UserService {

	void saveUser(User user);

	User findByEmail(String email);
}
