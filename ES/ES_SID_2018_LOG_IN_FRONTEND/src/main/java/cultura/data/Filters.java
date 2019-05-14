package cultura.data;

import java.sql.Date;

public class Filters {

	private Date dataB;
	private Date dataF;
	private Double measureL;
	private Double measureH;
	private String culture;
	private String sensor;

	public Date getDataB() {
		return dataB;
	}

	public void setDataB(Date dataB) {
		this.dataB = dataB;
	}

	public Date getDataF() {
		return dataF;
	}

	public void setDataF(Date dataF) {
		this.dataF = dataF;
	}

	public Double getMeasureL() {
		return measureL;
	}

	public void setMeasureL(Double measureL) {
		this.measureL = measureL;
	}

	public Double getMeasureH() {
		return measureH;
	}

	public void setMeasureH(Double measureH) {
		this.measureH = measureH;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
}
