package culture.cultures;

import java.util.List;

public interface CultureService {

	List<String> findByResponsible(String user);

	void saveCulture(Culture culture);

}
