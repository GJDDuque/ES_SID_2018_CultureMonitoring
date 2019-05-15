package iscte.sid.mysqlsuite.sensors;

public interface SensorConnectionListener {
	public void onError(String sensorAddress,String method, long timestamp);
	public void onSucess(String sensorAddress,String method, long timestamp);
}
