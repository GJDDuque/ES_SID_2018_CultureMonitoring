package utilities;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service("userService")
public class StoredProceduresService {

	@Autowired
	private DataSource dataSource;
	private SimpleJdbcCall jdbcCall;
	private JdbcTemplate template;
	private MapSqlParameterSource paramMap;

	public StoredProceduresService(String StoredProcedureName) {
		template = new JdbcTemplate(dataSource);
		jdbcCall = new SimpleJdbcCall(template).withProcedureName(StoredProcedureName);
	}
	
	public void SetQuery(String query) {
		paramMap = new MapSqlParameterSource().addValue("selectcommand", query);
	}

	public Object Execute() {
		Object object = jdbcCall.execute(paramMap);
		return object;
	}

	public void VoidExecute() {
		jdbcCall.execute(paramMap);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public SimpleJdbcCall getJdbcCall() {
		return jdbcCall;
	}

	public void setJdbcCall(SimpleJdbcCall jdbcCall) {
		this.jdbcCall = jdbcCall;
	}

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public MapSqlParameterSource getParamMap() {
		return paramMap;
	}

	public void setParamMap(MapSqlParameterSource paramMap) {
		this.paramMap = paramMap;
	}

}
