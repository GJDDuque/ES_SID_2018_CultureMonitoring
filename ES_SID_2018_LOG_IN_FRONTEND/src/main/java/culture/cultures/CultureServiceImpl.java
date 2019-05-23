package culture.cultures;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cultura.data.Data;

@Component
@Service("cultureService")
public class CultureServiceImpl implements CultureService {

	public CultureServiceImpl() {

	}

	@Override
	public List<String> findByResponsible(String user) {
		List<String> cultures = new Data("select culture_name from cultures where culture_responsible = '" + user + "'")
				.loadCultureNames();
		return cultures;
	}

}
