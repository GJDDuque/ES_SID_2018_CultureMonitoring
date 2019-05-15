package iscte.sid.sensorsuite.managers;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import iscte.sid.sensorsuite.model.SensorAdminError;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class EventManager {
	
   
    
	List<SensorAdminError> errors;

	@EventListener
	public void tester(Object event) {
		if(event instanceof SensorAdminError)
		errors.add((SensorAdminError) event);
		log.debug("this is a test" + event.getClass().getName());
		
	}
}
