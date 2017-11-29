package net.atos.Codex_IOT.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atos.Codex_IOT.dao.DashboardDao;
import net.atos.Codex_IOT.dao.ProjectDao;
import net.atos.Codex_IOT.mapper.Mapper;
import net.atos.Codex_IOT.model.ProjectDto;
import net.atos.Codex_IOT.pojo.Project;
import net.atos.Codex_IOT.pojo.ProjectMapping;
import net.atos.Codex_IOT.pojo.Sensor;
import net.atos.Codex_IOT.pojo.User;

@Transactional
@Service
public class ProjectServiceImpl implements ProjectService{

	
	@Autowired
	ProjectDao dao;
	
	@Autowired
	DashboardDao dashboardDao;
	
	@Autowired
	Mapper mapper;
		
	public void save(Project p) {
	
		dao.saveProject(p);
		
	}

	public void update(Project p) {
		
		dao.updateProject(p);
		
	}
	
	public void delete(String id) {
		
		dao.deleteProject(id);
		
	}
	
	public List<Project> get(){
		
		
		return dao.findAll();
	}
	
	public Project findProjectById(String id){
		
		return dao.findProjectById(id);
		//return null;
	}
	
	
	public List<Project> findByCustomerId(long id){
		
		return dao.findByCustomerId(id);
	}
	
	public List<Project> findAllByCustomerId(long id){
		return dao.findAllByCustomerId(id);
	}

	
	public ProjectDto findProjectDetails(String projectId){
		
		Project project = dao.findProjectById(projectId);
		if (null==project){
			throw new RuntimeException("Id not found");
		}
		
		ProjectDto projectDto=mapper.mapProjectToProjectDto(project);
		projectDto.setNoOfAssets(dashboardDao.getNoOfAssetsForProject(projectId));
		projectDto.setNoOfSensors(dashboardDao.getNoOfSensorsForProject(projectId));
		return projectDto;
	}

	@Override
	public void updateProjectActiveState(String projectId) {
		Project project=dao.findProjectById(projectId);
		if (project.isActive()){
			project.setActive(false);
			
		}
		else {
			project.setActive(true);
		}
		project.setUpdatedDate(new Date());
		dao.updateProject(project);
		
	}

	
	

}
