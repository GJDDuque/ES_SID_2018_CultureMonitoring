package iscte.sid.sensorsuite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import iscte.sid.sensorsuite.broker.MongoBroker;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootApplication
public class SensorSuiteApplication {

	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication( SensorSuiteApplication.class);
		ConfigurableApplicationContext context = sa.run(args);
	}


	
	@EventListener
	public void eventHandler(Object event) {
		MongoBroker broker=null;
		
		
		if(event instanceof ApplicationStartedEvent) {
			broker = new MongoBroker();
		}
		if(event instanceof ContextClosedEvent)
			if(broker!=null)
			broker.terminate();
		log.debug("this is a test" + event.getClass().getName());
		
	}
}
