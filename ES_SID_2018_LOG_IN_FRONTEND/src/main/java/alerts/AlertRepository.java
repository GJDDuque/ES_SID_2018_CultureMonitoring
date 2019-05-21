package alerts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("alertRepository")
public interface AlertRepository extends JpaRepository<Alert, Long> {
	
	
}
