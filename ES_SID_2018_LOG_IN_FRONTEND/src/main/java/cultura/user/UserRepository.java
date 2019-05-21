package cultura.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String username);

	User findByEmail(String email);

	@Query( value = "SELECT user_id, email, name, professional_category FROM culturesdb.users WHERE professional_category = ?1", nativeQuery = true)
	List<User> findAllByProfessionalCategory(String professionalCategory);

}