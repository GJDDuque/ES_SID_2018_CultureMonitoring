package culturas.user;

import java.util.HashSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import culturas.role.RoleRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private DataSource dataSource;
	private SimpleJdbcCall jdbcCall;

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));

		JdbcTemplate template = new JdbcTemplate(dataSource);
		jdbcCall = new SimpleJdbcCall(template).withProcedureName("addUser");
		MapSqlParameterSource paramMap = new MapSqlParameterSource().addValue("name", user.getName())
				.addValue("email", user.getEmail()).addValue("professional_category", user.getProfessional_category())
				.addValue("password", user.getPassword());
		jdbcCall.execute(paramMap);
	}

	@Override
	public User loadUserByUsername(String username) {
		return userRepository.loadUserByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.loadUserByUsername(email);
	}

}
