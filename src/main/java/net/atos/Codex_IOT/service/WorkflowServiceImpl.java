package net.atos.Codex_IOT.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.atos.Codex_IOT.dao.WorkflowDao;
import net.atos.Codex_IOT.pojo.Sensor;
import net.atos.Codex_IOT.pojo.Workflow;

@Transactional
@Repository
public class WorkflowServiceImpl implements WorkflowService {

	@Autowired
	private WorkflowDao dao;
	
	public String save(Workflow w) {
		return dao.save(w);
	}

	public String update(Workflow w){
		return dao.update(w);
	}
	
	public Workflow getbyid(long id){
		return dao.getbyid(id);
	}
	
	public List<Workflow> getbyProjectId(String id){
		return dao.getbyProjectId(id);
	}
	
	public List<Workflow> getbyCustomerId(long id){
		return dao.getbyCustomerId(id);
	}

	@Override
	public List<Sensor> allSensorsforWorkflow(String assetId, String eventType) {
		return dao.allSensorsforWorkflow(assetId, eventType);
	}
	
	
}
