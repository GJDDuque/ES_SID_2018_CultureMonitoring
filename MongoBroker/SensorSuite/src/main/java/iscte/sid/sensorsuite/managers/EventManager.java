package iscte.sid.sensorsuite.managers;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class EventManager {

//	@EventListener
	public void tester(Object event) {
		log.debug("this is a test" + event.getClass().getName());
		
	}
}
