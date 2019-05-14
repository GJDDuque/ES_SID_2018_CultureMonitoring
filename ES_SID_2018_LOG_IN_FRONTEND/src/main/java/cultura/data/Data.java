package cultura.data;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.util.LinkedCaseInsensitiveMap;

import cultura.utilities.StoredProceduresService;

public class Data {

	private StoredProceduresService storedProcedureService;
	private String userEmail;

	public Data(String query) {
		storedProcedureService = new StoredProceduresService();
		storedProcedureService.Configure("anyQuery");
		storedProcedureService.SetQuery(query);
	}

	public Data(String query, String dateB, String dateF, Double measureL, Double measureH, String culture,
			String sensor, String user) {
		String finalQuery = setupQuery(query, dateB, dateF, measureL, measureH, culture, sensor, user);
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

	public int loadID() {
		int value = 0;
		LinkedHashMap measuresData = (LinkedHashMap) storedProcedureService.Execute();
		for (LinkedCaseInsensitiveMap data : (ArrayList<LinkedCaseInsensitiveMap>) measuresData.get("#result-set-1")) {
			value = (int) data.get("measured_variables_id");
		}
		return value;
	}

	public List<String> loadDate() {
		LinkedHashMap dateData = (LinkedHashMap) storedProcedureService.Execute();
		List<String> chartData = new ArrayList<String>();
		for (LinkedCaseInsensitiveMap data : (ArrayList<LinkedCaseInsensitiveMap>) dateData.get("#result-set-1")) {
			chartData.add(((Timestamp) data.get("date_time")).toString());
		}
		return chartData;
	}

	private String setupQuery(String query, String dateB, String dateF, Double measureL, Double measureH,
			String culture, String sensor, String user) {
		String join = "";
		String whereDate = "";
		String whereMeasure = "";
		String andUser = " and user = '" + user + "'";

		if (dateF == "")
			dateF = null;

		if (dateB == "")
			dateB = null;

		// join
		if (!culture.isEmpty() && sensor.isEmpty())
			join = " join measured_variables mv on mv.measured_variables_id = m.measured_variable_id"
					+ " join cultures c on c.culture_id = mv.culture_id";

		if (culture.isEmpty() && !sensor.isEmpty())
			join = " join measured_variables mv on mv.measured_variables_id = m.measured_variable_id"
					+ " join variables v on v.variable_id = mv.variable_id";

		if (!culture.isEmpty() && !sensor.isEmpty())
			join = " join measured_variables mv on mv.measured_variables_id = m.measured_variable_id"
					+ " join cultures c on c.culture_id = mv.culture_id join variables v on v.variable_id = mv.variable_id";

		// where
		// Datas
		// se as datas sao iguais
		if (dateB == dateF && dateB != null)
			whereDate = " where m.date_time >= '" + dateB + "' and m.date_time <= '" + dateF + "'";
		// se apenas a being
		if (dateB != null && dateF == null)
			whereDate = " where m.date_time >= '" + dateB + "'";
		// se apenas a date final
		if (dateF != null && dateB == null)
			whereDate = " where m.date_time <= '" + dateF + "'";
		// se datas sao diferentes
		if (dateB != dateF && dateB != null && dateF != null)
			whereDate = " where m.date_time >= '" + dateB + "' and m.date_time <= '" + dateF + "'";
		// Measures
		// se sao iguais
		if (measureL != null && measureH != null && measureL.equals(measureH)) {
			if (dateB == null && dateF == null)
				whereMeasure = " where m.measured_value = '" + measureL + "'";
			else
				whereMeasure = " and m.measured_value = '" + measureL + "'";
		}
		// se sao diferentes
		if (measureL != null && measureH != null && !measureL.equals(measureH)) {
			if (dateB == null && dateF == null)
				whereMeasure = " where m.measured_value >= '" + measureL + "' and m.measured_value <= '" + measureH
						+ "'";
			else
				whereMeasure = " and m.measured_value >= '" + measureL + "' and m.measured_value <= '" + measureH + "'";
		}
		// se apenas a low
		if (measureL != null && measureH == null) {
			if (dateB == null && dateF == null)
				whereMeasure = " where m.measured_value >= '" + measureL + "'";
			else
				whereMeasure = " and m.measured_value >= '" + measureL + "'";
		}
		// se apenas high
		if (measureH != null && measureL == null) {
			if (dateB == null && dateF == null)
				whereMeasure = " where m.measured_value <= '" + measureH + "'";
			else
				whereMeasure = " and m.measured_value <= '" + measureH + "'";
		}

		return query + join + whereDate + whereMeasure + andUser;
	}
}
