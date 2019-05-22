package cultura.data;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.util.LinkedCaseInsensitiveMap;

import cultura.utilities.StoredProceduresService;

public class Data {

	private StoredProceduresService storedProcedureService;
	private String userEmail;
	private boolean bothDatesEmpty = false;
	private boolean bothMeasuresEmpty = false;
	private boolean cultureEmpty = false;

	public Data(String query) {
		storedProcedureService = new StoredProceduresService();
		storedProcedureService.Configure("anyQuery");
		storedProcedureService.SetQuery(query);
	}

	public Data(String query, String dateB, String dateF, Double measureL, Double measureH, String culture, String sensor, String user) {
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
	
	public int loadCulturesID() {
		int value = 0;
		LinkedHashMap measuresData = (LinkedHashMap) storedProcedureService.Execute();
		for (LinkedCaseInsensitiveMap data : (ArrayList<LinkedCaseInsensitiveMap>) measuresData.get("#result-set-1")) {
			value = (int) data.get("culture_id");
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

	public List<String> loadCultureNames() {
		LinkedHashMap culturesData = (LinkedHashMap) storedProcedureService.Execute();
		List<String> namesData = new ArrayList<String>();
		for (LinkedCaseInsensitiveMap name : (ArrayList<LinkedCaseInsensitiveMap>) culturesData.get("#result-set-1")) {
			namesData.add((String) name.get("culture_name"));
		}
		return namesData;
	}

	private String setupQuery(String query, String dateB, String dateF, Double measureL, Double measureH,
			String culture, String sensor, String user) {
		String joinQuery = joinQuery(culture);
		String whereDateQuery = whereDateQuery(dateB, dateF);
		String whereMeasureQuery = whereMeasureQuery(measureL, measureH);
		String cultureQuery = cultureQuery(culture);
		String userQuery = userAndSensorQuery(user, sensor);
		
		return query + joinQuery + whereDateQuery + whereMeasureQuery + cultureQuery + userQuery;
	}
	
	private String joinQuery(String culture) {
		if (culture.isEmpty())
			return " join measured_variables mv on mv.measured_variables_id = m.measured_variable_id"
					+ " join variables v on v.variable_id = mv.variable_id";

		if (!culture.isEmpty())
			return " join measured_variables mv on mv.measured_variables_id = m.measured_variable_id"
					+ " join cultures c on c.culture_id = mv.culture_id join variables v on v.variable_id = mv.variable_id";
		return "";
	}

	private String whereDateQuery(String dateB, String dateF) {
		if (!dateB.isEmpty() && !dateF.isEmpty() && dateB.equals(dateF)) {
			String newDateF = addOneDay(dateF);
			return " where m.date_time >= '" + dateB + "' and m.date_time < '" + newDateF + "'";
		}

		if (!dateB.isEmpty() && dateF.isEmpty())
			return " where m.date_time >= '" + dateB + "'";

		if (dateB.isEmpty() && !dateF.isEmpty()) {
			String newDateF = addOneDay(dateF);
			return " where m.date_time < '" + newDateF + "'";
		}

		if (!dateB.isEmpty() && !dateF.isEmpty() && !dateB.equals(dateF)) {
			String newDateF = addOneDay(dateF);
			return " where m.date_time >= '" + dateB + "' and m.date_time < '" + newDateF + "'";
		}

		bothDatesEmpty = true;
		return "";
	}

	private String whereMeasureQuery(Double measureL, Double measureH) {
		String whereQuery = "";

		String beforeQuery = " and";
		if (bothDatesEmpty)
			beforeQuery = " where";

		if (measureL != null && measureH != null && measureL.equals(measureH))
			return whereQuery = beforeQuery + " m.measured_value = '" + measureL + "'";

		if (measureL != null && measureH != null && !measureL.equals(measureH))
			return whereQuery = beforeQuery + " m.measured_value >= '" + measureL + "' and m.measured_value <= '"
					+ measureH + "'";

		if (measureL != null && measureH == null)
			return whereQuery = beforeQuery + " m.measured_value >= '" + measureL + "'";

		if (measureH != null && measureL == null)
			return whereQuery = beforeQuery + " m.measured_value <= '" + measureH + "'";

		bothMeasuresEmpty = true;
		return whereQuery;
	}

	private String cultureQuery(String culture) {
		if(culture.isEmpty()) {
			cultureEmpty = true;
			return "";
		}
		else {
			if (bothDatesEmpty && bothMeasuresEmpty)
				return " where c.culture_name = '" + culture + "'";

			return " and c.culture_name = '" + culture + "'";
		}
	}

	private String userAndSensorQuery(String user, String sensor) {
		if(bothDatesEmpty && bothMeasuresEmpty && cultureEmpty)
			return " where m.user = '" + user + "' and v.variable_name = '" + sensor + "'";
		return " and m.user = '" + user + "' and v.variable_name = '" + sensor + "'";
	}
	
	public String addOneDay(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, 1);
		return sdf.format(c.getTime());
	}
}
