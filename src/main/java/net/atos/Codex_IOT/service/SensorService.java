package net.atos.Codex_IOT.service;

import java.util.List;

import net.atos.Codex_IOT.pojo.Sensor;







import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public interface SensorService {
	
	
	
	public List<Sensor> getSensor();

	public List<Sensor> getSensorByAssetId(String id);
	
	public List<Sensor> getSensorsByCustomerId(long id);
	
	public Sensor getSensorById(String id);
	
	public Sensor saveSensor(Sensor s);
	
	public String updateSensor(Sensor s);
	
	public void updateSensorActiveState(String sensorId);

	public List<Sensor> getallSensorsByCustomerId(long id);

	public List<Sensor> getallSensorByAssetId(String asset_id);

	public List<Sensor> getallSensorCustomerIdTrue(long customer_id);
}
