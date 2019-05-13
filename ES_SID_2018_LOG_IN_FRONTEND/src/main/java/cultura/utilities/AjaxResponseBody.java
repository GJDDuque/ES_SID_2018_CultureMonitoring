package cultura.utilities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBody {

	@JsonView(Views.Public.class)
	private String msg;

	@JsonView(Views.Public.class)
	private String code;

	@JsonView(Views.Public.class)
	private List<Double> YAxis;

	@JsonView(Views.Public.class)
	private List<String> XAxis;

	public List<Double> getYAxis() {
		return YAxis;
	}

	public void setYAxis(List<Double> yAxis) {
		YAxis = yAxis;
	}

	public List<String> getXAxis() {
		return XAxis;
	}

	public void setXAxis(List<String> xAxis) {
		XAxis = xAxis;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}