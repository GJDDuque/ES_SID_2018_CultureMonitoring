package hello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("professionalCategoryRepository")
public interface ProfessionalCategoryRepository extends JpaRepository<ProfessionalCategory, Integer> {
	ProfessionalCategory findByProfessionalCategory(String professionalCategory);
}