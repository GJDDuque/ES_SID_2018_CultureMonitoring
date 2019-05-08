package culturas.user;

public interface UserService {

	void saveUser(User user);

	public User loadUserByUsername(String username);

	public User findByEmail(String email);
}
