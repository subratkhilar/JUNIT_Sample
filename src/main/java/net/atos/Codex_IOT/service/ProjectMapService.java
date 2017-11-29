package net.atos.Codex_IOT.service;

import java.util.List;


import net.atos.Codex_IOT.pojo.ProjectMapping;
import net.atos.Codex_IOT.pojo.User;

public interface ProjectMapService {

	public List<User> getprojectAdminbycustomer(long customer_id);

	public void saveprojmap(ProjectMapping p);
	
	public List<ProjectMapping> getProjectList(long id);

	public List<ProjectMapping> getUser(String project_id);

	public boolean updateProjectMapping(ProjectMapping m);


	
}
