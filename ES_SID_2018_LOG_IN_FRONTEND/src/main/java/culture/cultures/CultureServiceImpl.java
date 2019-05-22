package culture.cultures;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cultura.data.Data;

@Service("cultureService")
public class CultureServiceImpl implements CultureService {

	@Autowired
	private CultureRepository cultureRepository;

	@Override	
	public List<String> findByResponsible(String user) {
		List<String> cultures = new Data("select culture_name from cultures where culture_responsible = '" + user + "'")
				.loadCultureNames();
		return cultures;
	}

	@Override
	public void saveCulture(Culture culture) {
		cultureRepository.save(culture);
	}

}
