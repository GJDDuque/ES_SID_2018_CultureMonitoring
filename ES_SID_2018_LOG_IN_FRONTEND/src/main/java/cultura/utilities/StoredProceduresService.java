package cultura.utilities;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class StoredProceduresService {
	private SimpleJdbcCall jdbcCall;
	private JdbcTemplate template;
	private MapSqlParameterSource paramMap;
	public static DataSource dataSource;

	public StoredProceduresService() {
		if (this.dataSource == null) {
			this.dataSource = getDataSource();
		}
	}

	public DataSource getDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url(
				"jdbc:mysql://localhost:3306/culturesdb?serverTimezone=GMT&useSSL=false&allowPublicKeyRetrieval=true");
		dataSourceBuilder.username("root");
		return dataSourceBuilder.build();
	}

	public void Configure(String StoredProcedureName) {
		template = new JdbcTemplate(dataSource);
		jdbcCall = new SimpleJdbcCall(template).withProcedureName(StoredProcedureName);
	}

	public void SetQuery(String query) {
		paramMap = new MapSqlParameterSource().addValue("selectcommand", query);
	}

	public void SetParams(HashMap<String, String> Params) {
		paramMap = new MapSqlParameterSource().addValues(Params);
	}

	public Object Execute() {
		Object object = jdbcCall.execute(paramMap);
		return object;
	}

	public void VoidExecute() {
		jdbcCall.execute(paramMap);
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
