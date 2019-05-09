package utilities;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class StoredProceduresService {

	@Autowired
	private DataSource dataSource;
	private SimpleJdbcCall jdbcCall;
	private JdbcTemplate template;
	private MapSqlParameterSource paramMap;

	public StoredProceduresService() {

	}

	public void PrepareStoredProcedure(String StoredProcedureName) {
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
}
