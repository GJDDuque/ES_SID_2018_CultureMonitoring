package iscte.sid.sensorsuite.model;

import lombok.Data;

@Data
public abstract class AbstractMeasure {
	private String dat;
	private String tim;
	private long timestamp;
}
