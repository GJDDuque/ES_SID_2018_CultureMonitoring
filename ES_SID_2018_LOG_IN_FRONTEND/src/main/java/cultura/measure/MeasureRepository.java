package cultura.measure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("measureRepository")
public interface MeasureRepository extends JpaRepository<Measure, Long> {
	
	Measure findByUser(String email);
}
