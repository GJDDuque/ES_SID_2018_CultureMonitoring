package iscte.sid.mysqlsuite.managers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import iscte.sid.sensorsuite.model.AbstractMeasure;
import iscte.sid.sensorsuite.model.events.GenericEvent;

@Component
public class AlarmManager {
	
	private List<AbstractMeasure> measures;
	private List<GenericEvent> events;

	/**
	 * 
	 */
	public AlarmManager() {
		super();
		events = new ArrayList<>();
		measures = new ArrayList<>(5);
		}
	
	public void addMeasure(AbstractMeasure measure) {
		measures.add(measure);		
		
	}

	public  void addEvent(GenericEvent genericEvent) {
		events.add(genericEvent);
		
	}
	
	

}
