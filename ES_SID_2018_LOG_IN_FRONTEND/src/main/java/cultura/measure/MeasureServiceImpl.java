package cultura.measure;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cultura.data.Data;

@Service("measureService")
public class MeasureServiceImpl implements MeasureService {

	@Autowired
	private MeasureRepository measureRepository;	

	@Override
	public void saveMeasure(MeasureForm measureForm) {
		int measure_variable_id = new Data(
				"select measured_variables_id from measured_variables mv join cultures c on mv.culture_id = c.culture_id join variables v on mv.variable_id = v.variable_id where c.culture_name = '"
						+ measureForm.getCulture() + "' and v.variable_name = '" + measureForm.getVariable() + "'")
								.loadID();
		measureRepository.save(
				new Measure((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))),
						measureForm.getMeasured_value(), measureForm.getEmail(), measure_variable_id));
	}

}
