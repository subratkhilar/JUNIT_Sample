package net.atos.Codex_IOT.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.atos.Codex_IOT.dao.SensorDao;
import net.atos.Codex_IOT.pojo.Sensor;



@Transactional
@Repository
public class SensorServiceImpl implements SensorService {

	private static final Logger logger =Logger.getLogger(SensorServiceImpl.class);
	
	
	@Autowired
	private SensorDao sensordao;
	
	@Override
	public List<Sensor> getSensor() {
		logger.info("iniotservice impl");	
		
		return sensordao.getSensors();
	}
	
	public List<Sensor> getSensorByAssetId(String id) {
		logger.info("iniotservice impl");	
		
		return sensordao.getSensorsByAssetId(id);
	}
	
	public List<Sensor> getSensorsByCustomerId(long id){
		return sensordao.getSensorsByCustomerId(id);
	}
	
	@Override
	public void updateSensorActiveState(String sensorId) {
		logger.info("inside updateSensorActiveState()");
		
		Sensor sensor=sensordao.getSensorById(sensorId);
		if (sensor.isActive()){
			sensor.setActive(false);
			
		}
		else {
			sensor.setActive(true);
		}
		sensor.setUpdatedDate(new Date());
		sensordao.updateSensor(sensor);
		
	}

	public Sensor saveSensor(Sensor s){
		logger.info("iniotservice sensor save impl");
		
		return sensordao.saveSensor(s);
	}

	
	public Sensor getSensorById(String id){
		
		return sensordao.getSensorById(id);
	}

	public String updateSensor(Sensor s){
		return sensordao.updateSensor(s);
	}

	@Override
	public List<Sensor> getallSensorsByCustomerId(long id) {
		return sensordao.getallSensorsByCustomerId(id);
	}

	@Override
	public List<Sensor> getallSensorByAssetId(String id) {
logger.info("iniotservice impl");	
		
		return sensordao.getallSensorsByAssetId(id);
	}

	@Override
	public List<Sensor> getallSensorCustomerIdTrue(long id) {
		return sensordao.getallSensorCustomerIdTrue(id);
	}
}
