package net.atos.Codex_IOT.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import net.atos.Codex_IOT.dao.ProjectDao;
import net.atos.Codex_IOT.dao.ProjectUserMap;
import net.atos.Codex_IOT.dao.ProjectUserMapImpl;
import net.atos.Codex_IOT.pojo.Asset;
import net.atos.Codex_IOT.pojo.Customer;
import net.atos.Codex_IOT.pojo.Project;
import net.atos.Codex_IOT.pojo.ProjectMapping;
import net.atos.Codex_IOT.pojo.Role;
import net.atos.Codex_IOT.pojo.User;

@RunWith(MockitoJUnitRunner.class)
public class ProjectMapServiceImplTest {
	@Mock
	private ProjectUserMap projectMappingDao;

	@Mock
	private ProjectDao projectDao;

	@InjectMocks
	private ProjectMapService projectMappingService = new ProjectMapServiceImpl();
	private User user = new User();
	private Project project = new Project();
	private ProjectMapping projectMapp = new ProjectMapping();
	@Before
	public void beforeTest() {

		user.setUserId(12L);
		user.setPassword("123");
		user.setFirstname("Amit");
		user.setLastname("Kumar");
		user.setUsername("Amit123");
		user.setDeviceToken("123");
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerName("Shaym");
		user.setCustomer(customer);
		Role role = new Role();
		role.setRoleId(2);
		role.setRolename("admin");
		user.setRole(role);
		project.setProjectId("Pl001");
		project.setProjectName("testproject");
		project.setProjectDescription("test Desc");
		project.setActive(true);
		projectMapp.setProjectmappingid(1L);
		projectMapp.setProject(project);
		projectMapp.setUser(user);
	}

	@Test
	public void testGetprojectAdminbycustomer() {
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		when(projectMappingDao.getprojectAdminbycustomer(user.getCustomer().getCustomerId())).thenReturn(userList);
		List<User> results = projectMappingService.getprojectAdminbycustomer(user.getCustomer().getCustomerId());
		// Validation
		assertNotNull(results);
		assertEquals(1, results.size());
	}

	@Test
	public void testSaveprojmap() {
		projectMappingService.saveprojmap(projectMapp);
		doNothing().when(projectMappingDao).saveprojmap(projectMapp);

		verify(projectMappingDao).saveprojmap(any(ProjectMapping.class));
	}

	@Test
	public void testGetProjectList() {
		List<ProjectMapping> projList = new ArrayList<>();
		projList.add(projectMapp);
		when(projectMappingDao.getProjectList(projectMapp.getUser().getUserId())).thenReturn(projList);
		List<ProjectMapping> resultpmList = projectMappingService.getProjectList(projectMapp.getUser().getUserId());
		assertNotNull(resultpmList);
		assertEquals(1, resultpmList.size());
	}

	@Test
	public void testGetUser() {
		List<ProjectMapping> projList = new ArrayList<>();
		projList.add(projectMapp);
		when(projectMappingDao.getUser(projectMapp.getProject().getProjectId())).thenReturn(projList);
		List<ProjectMapping> resultpmList = projectMappingService.getUser(projectMapp.getProject().getProjectId());
		assertNotNull(resultpmList);
		assertEquals(1, resultpmList.size());
	}

	@Test
	public void testUpdateProjectMapping() {
		when(projectMappingDao.updateProjectMapping(projectMapp)).thenReturn(true);
		boolean result = projectMappingService.updateProjectMapping(projectMapp);
		// assertFalse(result);
	}

}
