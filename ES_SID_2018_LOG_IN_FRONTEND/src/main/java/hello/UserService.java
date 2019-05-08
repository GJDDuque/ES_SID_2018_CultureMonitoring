package hello;

public interface UserService {

	void saveUser(User user);

	User findByUsername(String username);
}
