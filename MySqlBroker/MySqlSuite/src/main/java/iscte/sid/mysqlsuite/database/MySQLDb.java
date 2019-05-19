package iscte.sid.mysqlsuite.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import iscte.sid.mysqlsuite.managers.AlarmManager;
import iscte.sid.mysqlsuite.model.Limit;
import iscte.sid.sensorsuite.model.LightMeasure;
import iscte.sid.sensorsuite.model.TemperatureMeasure;
import iscte.sid.sensorsuite.model.events.GenericEvent;
import iscte.sid.sensorsuite.model.events.MeasuresEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MySQLDb {

	private String connectionString;

	private String dbUserName;

	private String dbUserPassword;

	@Autowired
	@Lazy
	private AlarmManager am;

	boolean closeConnection = false;
	private Connection con;

	private MySQLDb(@Value("${mysql.connectionstring}") String connectionString,
			@Value("${mysql.user}") String dbUserName, @Value("${mysql.password}") String dbUserPassword) {
		this.connectionString = connectionString;
		this.dbUserName = dbUserName;
		this.dbUserPassword = dbUserPassword;

		new Thread(new Runnable() {

			@Override
			public void run() {

				if (closeConnection) {
					try {
						con.close();
					} catch (SQLException e) {
					}
				}
				closeConnection = true;
				try {
					Thread.sleep(160000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}, "Connection Manager");
	}

	/**
	 * Metodo que inicia a ligação ao mysql e carrega os limites das variaveis
	 */
	@PostConstruct
	private void initMysqlConnection() {
		try {

			try (PreparedStatement stmt = getCon().prepareStatement(
					"select a.culture_id,culture_name,measured_variables_id,lower_limit,upper_limit, variable_name from measured_variables a, cultures b, variables c where a.culture_id=b.culture_id and a.measured_variables_id=c.variable_id");) {
				ResultSet limits = stmt.executeQuery();

				while (limits.next()) {
					try {
						am.addLimits(new Limit(limits.getInt(1), limits.getString(2), limits.getInt(3),
								limits.getInt(4), limits.getInt(5), limits.getString(6)));
					} catch (Exception e) {
						log.error("ups", e);
					}
				}
			}
		} catch (SQLException e) {
			log.error("erro ao iniciar a ligação à bd...", e);
		}

	}

	private Connection getCon() throws SQLException {
		if (con == null || con.isClosed()) {
			con = DriverManager.getConnection(connectionString, dbUserName, dbUserPassword);
		}
		closeConnection = false;
		return con;
	}

	/**
	 * Metodo usado para armazenar um evento genérico não relacionado com as
	 * medições na tabela alertas
	 * 
	 * @param genericEvent é o evento a ser introduzido na base de dados
	 */
	public void persistEvent(GenericEvent genericEvent) {
		try (PreparedStatement stmt = getCon().prepareStatement("INSERT INTO `alerts`" + "(`date_time`,"
				+ "`variable_name`," + "`lower_limit`," + "`upper_limit`," + "`measured_value`," + "`culture_name`,"
				+ "`type`," + "`sensor_id`," + "`user_id`," + "`description`)" + "VALUES(" + "?," + "?," + "?," + "?,"
				+ "?," + "?," + "?," + "?," + "?," + "?)");) {
			stmt.setTimestamp(1, new java.sql.Timestamp(genericEvent.getTimestamp()));
			stmt.setString(2, "Evento");
			stmt.setInt(3, 0);
			stmt.setInt(4, 0);
			stmt.setInt(5, 0);
			stmt.setString(6, genericEvent.getIdentifier());
			stmt.setInt(7, genericEvent.getType());
			stmt.setInt(8, 0);
			stmt.setInt(9, 0);
			stmt.setString(10, genericEvent.getMethod());

			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo usado para armazenar um evento relacionado com medições na tabela
	 * alertas
	 * 
	 * @param genericEvent é o evento a ser introduzido na base de dados
	 */
	public void persistEvent(MeasuresEvent event) {
		try (PreparedStatement stmt = getCon().prepareStatement("INSERT INTO `alerts`" + "(`date_time`,"
				+ "`variable_name`," + "`lower_limit`," + "`upper_limit`," + "`measured_value`," + "`culture_name`,"
				+ "`type`," + "`sensor_id`," + "`user_id`," + "`description`)" + "VALUES(" + "?," + "?," + "?," + "?,"
				+ "?," + "?," + "?," + "?," + "?," + "?)");) {
			stmt.setTimestamp(1, event.getDate_time());
			stmt.setString(2, event.getVariable_name());
			stmt.setInt(3, event.getLower_limit());
			stmt.setInt(4, event.getUpper_limit());
			stmt.setInt(5, event.getMeasured_value());
			stmt.setString(6, event.getCulture_name());
			stmt.setString(7, event.getType());
			stmt.setInt(8, event.getSensor_id());
			stmt.setInt(9, event.getUser_id());
			stmt.setString(10, event.getDescription());

			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo responsável por salvar as medidas de luz na tabela measures
	 * 
	 * @param measure
	 */
	public void persistMeasure(LightMeasure measure) {
		try (PreparedStatement stmt = getCon().prepareStatement("INSERT INTO `measures`" + "date_time`,"
				+ "measured_value`," + "user`," + "measured_variable_id`)" + "VALUES" + "(?," + "?," + "?," + "?)")) {
			stmt.setTimestamp(1, new Timestamp(measure.getTimestamp()));
			stmt.setInt(2, measure.getCell());
			stmt.setInt(3, 0);
			stmt.setInt(4, 2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Metodo responsável por salvar as medidas de temperatura na tabela measures
	 * 
	 * @param measure contem os valores a ser salvos
	 */
	public void persistMeasure(TemperatureMeasure measure) {

		try (PreparedStatement stmt = getCon().prepareStatement("INSERT INTO `measures`" + "date_time`,"
				+ "measured_value`," + "user`," + "measured_variable_id`)" + "VALUES" + "(?," + "?," + "?," + "?)")) {
			stmt.setTimestamp(1, new Timestamp(measure.getTimestamp()));
			stmt.setDouble(2, measure.getTmp());
			stmt.setInt(3, 0);
			stmt.setInt(4, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}