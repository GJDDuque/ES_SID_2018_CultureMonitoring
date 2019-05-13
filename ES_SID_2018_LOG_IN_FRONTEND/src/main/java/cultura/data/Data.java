package cultura.data;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.util.LinkedCaseInsensitiveMap;

import cultura.utilities.StoredProceduresService;

public class Data {

	private StoredProceduresService storedProcedureService;

	public Data(String query) {
		storedProcedureService = new StoredProceduresService();
		storedProcedureService.Configure("anyQuery");
		storedProcedureService.SetQuery(query);
	}

	public Data(Date dateB, Date dateF, double measureL, double measureH, String culture, String sensor) {
		String finalQuery = setupQuery(dateB, dateF, measureL, measureH, culture, sensor);
		storedProcedureService = new StoredProceduresService();
		storedProcedureService.Configure("anyQuery");
		storedProcedureService.SetQuery(finalQuery);
	}

	public List<Double> loadMeasures() {
		LinkedHashMap measuresData = (LinkedHashMap) storedProcedureService.Execute();
		List<Double> chartData = new ArrayList<Double>();
		for (LinkedCaseInsensitiveMap data : (ArrayList<LinkedCaseInsensitiveMap>) measuresData.get("#result-set-1")) {
			chartData.add((double) ((BigDecimal) data.get(("measured_value"))).doubleValue());
		}
		return chartData;
	}

	public List<String> loadDate() {
		LinkedHashMap dateData = (LinkedHashMap) storedProcedureService.Execute();
		List<String> chartData = new ArrayList<String>();
		for (LinkedCaseInsensitiveMap data : (ArrayList<LinkedCaseInsensitiveMap>) dateData.get("#result-set-1")) {
			chartData.add(((Timestamp) data.get("date_time")).toString());
//					Date.from(
//					((Timestamp) data.get("date_time")).toLocalDateTime().atZone(ZoneId.systemDefault()).toInstant()).toString());
		}
		return chartData;
	}

	private String setupQuery(Date dateB, Date dateF, Double measureL, Double measureH, String culture, String sensor) {
		String query = "select measured_variables from measures m";
		String join = "";
		String whereDate = "";
		String whereMeasure = "";

		// join
		if (culture != null && sensor == null)
			join = " join measured_variables mv on mv.measured_variables_id = m.measured_variables_id"
					+ " join cultures c on c.culture_id = mv.culture_id";

		if (culture == null && sensor != null)
			join = " join measured_variables mv on mv.measured_variables_id = m.measured_variables_id"
					+ " join variables v on v.variable_id = mv.variable_id";

		if (culture != null && sensor != null)
			join = " join measured_variables mv on mv.measured_variables_id = m.measured_variables_id"
					+ " join cultures c on c.culture_id = mv.culture_id join variables v on v.variable_id = mv.variable_id";

		// where
		if (dateB == dateF && dateB != null)
			whereDate = " where m.date_time = dateF";

		if (dateB != dateF && dateB != null && dateF != null)
			whereDate = " where m.date_time > dateB and m.date_time < dateF";

		if (measureL.equals(measureH) && measureL != null && measureH != null) {
			if (dateB == null && dateF == null)
				whereMeasure = " where m.measured_value = measureL";
			else
				whereMeasure = " and m.measured_value = measureL";
		}

		if (!measureL.equals(measureH) && measureL != null && measureH != null) {
			if (dateB == null && dateF == null)
				whereMeasure = " where m.measured_value > measureL and m.measured_value < measureH";
			else
				whereMeasure = " and m.measured_value > measureL and m.measured_value < measureH";
		}
		return query + join + whereDate + whereMeasure;
	}
}
