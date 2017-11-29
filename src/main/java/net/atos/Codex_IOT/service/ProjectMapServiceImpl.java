package net.atos.Codex_IOT.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atos.Codex_IOT.dao.ProjectDao;
import net.atos.Codex_IOT.dao.ProjectUserMap;
import net.atos.Codex_IOT.pojo.ProjectMapping;
import net.atos.Codex_IOT.pojo.User;

@Service
@Transactional
public class ProjectMapServiceImpl implements ProjectMapService {

	
	@Autowired
	private ProjectUserMap projectMappingDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Override
	public List<User> getprojectAdminbycustomer(long customerId) {
		return projectMappingDao.getprojectAdminbycustomer(customerId);
	}

	@Override
	public void saveprojmap(ProjectMapping p) {
		projectMappingDao.saveprojmap(p);
	}

	@Override
	public List<ProjectMapping> getProjectList(long id) {
		return projectMappingDao.getProjectList(id);
	}

	@Override
	public List<ProjectMapping> getUser(String projectId) {
		return projectMappingDao.getUser(projectId);
	}

	@Override
	public boolean updateProjectMapping(ProjectMapping m) {
		projectMappingDao.updateProjectMapping(m);
		return true;
	}


	
	
	

}
