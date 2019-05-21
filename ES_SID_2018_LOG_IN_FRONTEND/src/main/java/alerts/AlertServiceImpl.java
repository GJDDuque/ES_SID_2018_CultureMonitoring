package alerts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("alertService")
public class AlertServiceImpl implements AlertService {

	@Autowired
	private AlertRepository alertRepository;

	@Override
	public List<Alert> getAlerts() {
		return alertRepository.findAll();
	}

}
