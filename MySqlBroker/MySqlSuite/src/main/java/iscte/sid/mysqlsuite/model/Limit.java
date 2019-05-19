package iscte.sid.mysqlsuite.model;

import lombok.Data;

@Data
public class Limit {
	private int cultureId;
	private int variableType;
	private int lowerLimit;
	private int upperLimit;
	private String culture_name;
	private String variableName;
	/**
	 * 
	 */
	public Limit() {
		super();
		// TODO Auto-generated constructor stub
	}




	/**
	 * @param cultureId
	 * @param culture_name 
	 * @param variableType
	 * @param lowerLimit
	 * @param upperLimit
	 * @param variablename 
	 */
	public Limit(int cultureId, String culture_name, int variableType, int lowerLimit, int upperLimit, String variablename) {
		super();
		this.cultureId = cultureId;
		this.culture_name = culture_name;
		this.variableType = variableType;
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.variableName = variablename;
	}

}
