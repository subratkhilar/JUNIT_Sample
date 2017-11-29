package net.atos.Codex_IOT.service;

import java.util.List;

import net.atos.Codex_IOT.model.ProjectDto;
import net.atos.Codex_IOT.pojo.Project;
import net.atos.Codex_IOT.pojo.ProjectMapping;
import net.atos.Codex_IOT.pojo.User;

public interface ProjectService {

	public void save(Project p);
	
	public void update(Project p);
	
	public void delete(String id);
	
	public List<Project> get();
	
	public Project findProjectById(String id);
	
	public List<Project> findByCustomerId(long id);
	
	public List<Project> findAllByCustomerId(long id);
	
	public ProjectDto findProjectDetails(String projectId);

	public void updateProjectActiveState(String projectId);
}
