package culture.cultures;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("cultureRepository")
public interface CultureRepository extends JpaRepository<Culture, Long>{
	
	Culture findByResponsible(String email);
	
}
