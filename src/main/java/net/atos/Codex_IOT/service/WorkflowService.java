package net.atos.Codex_IOT.service;

import java.util.List;

import org.springframework.stereotype.Service;

import net.atos.Codex_IOT.pojo.Sensor;
import net.atos.Codex_IOT.pojo.Workflow;

@Service
public interface WorkflowService {

	public String save(Workflow w);
	
	public Workflow getbyid(long id);

	public String update(Workflow w);
	
	public List<Workflow> getbyProjectId(String id);
	
	public List<Workflow> getbyCustomerId(long id);
	
	public List<Sensor> allSensorsforWorkflow(String assetId, String eventType);
}
