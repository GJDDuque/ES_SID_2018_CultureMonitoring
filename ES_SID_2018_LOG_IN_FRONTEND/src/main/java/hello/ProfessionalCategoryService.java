package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("professionalCategoryService")
public class ProfessionalCategoryService {

	private ProfessionalCategoryRepository professionalCategoryRepository;

	@Autowired
	public ProfessionalCategoryService(ProfessionalCategoryRepository professionalCategoryRepository) {
		this.professionalCategoryRepository = professionalCategoryRepository;
	}

	public boolean professionalCategoryExists(String professionalCategory) {
		return professionalCategoryRepository.findByProfessionalCategory(professionalCategory) != null;
	}
}
