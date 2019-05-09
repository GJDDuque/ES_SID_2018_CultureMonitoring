package culturas.data;

import java.util.ArrayList;

import utilities.StoredProceduresService;

public class Data {

	private ArrayList data;
	private StoredProceduresService storedProcedureService;

	public Data(String selectCommand) {
		storedProcedureService = new StoredProceduresService("anyQuery");
		storedProcedureService.SetQuery(selectCommand);
	}

	public ArrayList getData() {
		return data;
	}

	public void setData(ArrayList data) {
		this.data = data;
	}

	public ArrayList loadData() {
		return data = (ArrayList) storedProcedureService.Execute();
	}

}
