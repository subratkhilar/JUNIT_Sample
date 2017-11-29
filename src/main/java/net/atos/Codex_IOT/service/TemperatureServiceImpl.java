package net.atos.Codex_IOT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.Codex_IOT.dao.TemperatureDao;
import net.atos.Codex_IOT.dao.TemperatureRepo;
//import net.atos.Codex_IOT.dao.TemperatureRepo;
import net.atos.Codex_IOT.pojo.Temperature;

@Transactional
@Service
public class TemperatureServiceImpl implements TemperatureService{

	@Autowired
	public TemperatureRepo rep;
	
	@Autowired
	public TemperatureDao dao;
	
	public List<Temperature> getTemperature()
	{
		//List<Temperature> l = rep.findAll();
		List<Temperature> l = dao.getData();
		return l;
	}
	
	public List<Temperature> getTempbyID(String id)
	{
		return dao.getDataByID(id);
	}
	
	public String save(Temperature t)
	{
		return dao.saveTemperature(t);
	}
}
