package cultura.utilities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBody {

	@JsonView(Views.Public.class)
	private String msg;

	@JsonView(Views.Public.class)
	private String code;

	@JsonView(Views.Public.class)
	private List<Double> result;

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

	public List<Double> getResult() {
		return result;
	}

	public void setResult(List<Double> result) {
		this.result = result;
	}

}