package cultura.variable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("variableRepository")
public interface VariableRepository extends JpaRepository<Variable, Long>{

	
	
}
