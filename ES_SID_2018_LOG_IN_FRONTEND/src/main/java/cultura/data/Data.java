package cultura.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.util.LinkedCaseInsensitiveMap;

import cultura.utilities.StoredProceduresService;

public class Data {

	private StoredProceduresService storedProcedureService;
	private String query;

	public Data(String query) {
//		setupQuery(line, table, row, filter);
		storedProcedureService = new StoredProceduresService();
		storedProcedureService.Configure("anyQuery");
		storedProcedureService.SetQuery(query);
	}

	public List<Double> loadMeasures() {
		LinkedHashMap measuresData = (LinkedHashMap) storedProcedureService.Execute();
		List<Double> chartData = new ArrayList<Double>();
		for (LinkedCaseInsensitiveMap data : (ArrayList<LinkedCaseInsensitiveMap>) measuresData.get("#result-set-1")) {
			chartData.add((double) ((BigDecimal) data.get(("measured_value"))).doubleValue());
		}
		return chartData;
	}
	
	private void setupQuery(String line, String table, String row, String filter) {
		query = "select " + line + " from " + table + " where " + row + "='" + filter + "'";		
	}

	
	
	
}
