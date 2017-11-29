package net.atos.Codex_IOT.service;

import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import net.atos.Codex_IOT.pojo.Temperature;

public interface TemperatureService {

	public List<Temperature> getTemperature();
	
	public List<Temperature> getTempbyID(String id);
	
	public String save(Temperature t);
}
