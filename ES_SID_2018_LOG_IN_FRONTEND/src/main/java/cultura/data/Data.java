package cultura.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import cultura.utilities.StoredProceduresService;

public class Data {

	private LinkedHashMap data;
	private StoredProceduresService storedProcedureService;

	public Data(String selectCommand) {
		storedProcedureService = new StoredProceduresService();
		storedProcedureService.Configure("anyQuery");
		storedProcedureService.SetQuery(selectCommand);
	}

	public LinkedHashMap getData() {
		return data;
	}

	public void setData(LinkedHashMap data) {
		this.data = data;
	}

	public LinkedHashMap loadData() {
		return data = (LinkedHashMap) storedProcedureService.Execute();
	}

}
