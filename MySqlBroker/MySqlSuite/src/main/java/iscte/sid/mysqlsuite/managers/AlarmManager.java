package iscte.sid.mysqlsuite.managers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import iscte.sid.mysqlsuite.database.MySQLDb;
import iscte.sid.mysqlsuite.model.Limit;
import iscte.sid.sensorsuite.model.LightMeasure;
import iscte.sid.sensorsuite.model.TemperatureMeasure;
import iscte.sid.sensorsuite.model.events.GenericEvent;
import iscte.sid.sensorsuite.model.events.MeasuresEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlarmManager {

	private List<TemperatureMeasure> temperatures;
	private List<LightMeasure> lights;
	private List<GenericEvent> events;
	private List<Limit> limits;

	@Autowired
	private MySQLDb db;
	private long lighttimetotop;
	private long lighttimetolow;
	private long temptimetotop;
	private long temptimetolow;

	/**
	 * 
	 */
	public AlarmManager(@Value("${sensors.light.timetotop}") long lighttimetotop,
			@Value("${sensors.light.timetolow}") long lighttimetolow,
			@Value("${sensors.temperature.timetotop}") long temptimetotop,
			@Value("${sensors.temperature.timetolow}") long temptimetolow) {
		super();
		this.lighttimetotop = lighttimetotop;
		this.lighttimetolow = lighttimetolow;
		this.temptimetotop = temptimetotop;
		this.temptimetolow = temptimetolow;
		events = new ArrayList<>();
		temperatures = new ArrayList<>(5);
		lights = new ArrayList<>(5);
		limits = new ArrayList<>(2);
	}

	public void addMeasure(TemperatureMeasure measure) {
			temperatures.add(measure);
			db.persistMeasure(measure);
			validateTemperature(measure);
	}
	
	private void validateTemperature(TemperatureMeasure measure) {
		TemperatureMeasure pre_last_measure = temperatures.get(temperatures.size()-2);
		TemperatureMeasure last_measure = temperatures.get(temperatures.size()-1);
		
		long time = last_measure.getTimestamp()-pre_last_measure.getTimestamp();
		double tmp = last_measure.getTmp()-pre_last_measure.getTmp();

			final double rate = time>0?tmp/time:0;
		
		
		limits.stream().filter(t->t.getVariableType()==1).forEach(l->{
			int ll =l.getLowerLimit();
			int ul = l.getUpperLimit();
			
			if(ll >= measure.getTmp()) {
				db.persistEvent(new MeasuresEvent(new Timestamp(measure.getTimestamp()), l.getVariableName(),ll,ul,measure.getTmp(),l.getCulture_name(),l.getVariableType(),1,0,"A temperatura é inferior ao limite inferior"));
			}else {
				if(ul<= measure.getTmp()) {
					db.persistEvent(new MeasuresEvent(new Timestamp(measure.getTimestamp()), l.getVariableName(),ll,ul,measure.getTmp(),l.getCulture_name(),l.getVariableType(),1,0,"A temperatura é superior ao limite superior"));
				}else {
				double overlimit = rate*temptimetotop;
				if(overlimit >= ul ) {
					db.persistEvent(new MeasuresEvent(new Timestamp(measure.getTimestamp()), l.getVariableName(),ll,ul,measure.getTmp(),l.getCulture_name(),l.getVariableType(),1,0,"A temperatura vai atingir o limite superior em menos de " + temptimetotop/60/1000 + "s."));
				}else {
					double underlimit = rate*temptimetolow;
					if(underlimit<= ll) {
					db.persistEvent(new MeasuresEvent(new Timestamp(measure.getTimestamp()), l.getVariableName(),ll,ul,measure.getTmp(),l.getCulture_name(),l.getVariableType(),1,0,"A temperatura vai atingir o limite inferior em menos de" + temptimetolow/60/1000 + "s." ));
					}
				}
			}
			}
		});
	}

	public void addMeasure(LightMeasure measure) {
			lights.add(measure);
			db.persistMeasure(measure);
			validateLight(measure);
			
	}

	private void validateLight(LightMeasure measure) {
		LightMeasure pre_last_measure = lights.get(lights.size()-2);
		LightMeasure last_measure = lights.get(lights.size()-1);
		
		long time = last_measure.getTimestamp()-pre_last_measure.getTimestamp();
		double cell = last_measure.getCell()-pre_last_measure.getCell();
		
		final double rate = time>0?cell/time:0;
		
		limits.stream().filter(t->t.getVariableType()==2).forEach(l->{
			int ll =l.getLowerLimit();
			int ul = l.getUpperLimit();
			
			if(ll >= measure.getCell()) {
				db.persistEvent(new MeasuresEvent(new Timestamp(measure.getTimestamp()), l.getVariableName(),ll,ul,measure.getCell(),l.getCulture_name(),l.getVariableType(),1,0,"A luminosidade é inferior ao limite inferior"));
			}else {
				if(ul<= measure.getCell()) {
					db.persistEvent(new MeasuresEvent(new Timestamp(measure.getTimestamp()), l.getVariableName(),ll,ul,measure.getCell(),l.getCulture_name(),l.getVariableType(),1,0,"A luminosidade é superior ao limite superior"));
				}else {
				double overlimit = rate*lighttimetotop;
				if(overlimit >= ul ) {
					db.persistEvent(new MeasuresEvent(new Timestamp(measure.getTimestamp()), l.getVariableName(),ll,ul,measure.getCell(),l.getCulture_name(),l.getVariableType(),1,0,"A luminosidade vai atingir o limite superior em menos de " + lighttimetotop/60/1000 + "s."));
				}else {
					double underlimit = rate*lighttimetolow;
					if(underlimit<= ll) {
					db.persistEvent(new MeasuresEvent(new Timestamp(measure.getTimestamp()), l.getVariableName(),ll,ul,measure.getCell(),l.getCulture_name(),l.getVariableType(),1,0,"A luminosidade vai atingir o limite inferior em menos de " + lighttimetolow/60/1000 + "s."));
					}
				}
			}
			}
		});
		
	}

	public void addEvent(GenericEvent genericEvent) {
		db.persistEvent(genericEvent);
		events.add(genericEvent);

	}

	public void addLimits(Limit limit) {
		log.debug("added a new limit to the am " + limit);
		limits.add(limit);

	}

}
