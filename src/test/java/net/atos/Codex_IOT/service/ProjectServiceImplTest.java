package net.atos.Codex_IOT.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import net.atos.Codex_IOT.dao.DashboardDao;
import net.atos.Codex_IOT.dao.ProjectDao;
import net.atos.Codex_IOT.mapper.Mapper;
import net.atos.Codex_IOT.model.ProjectDto;
import net.atos.Codex_IOT.pojo.Customer;
import net.atos.Codex_IOT.pojo.Project;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImplTest {

	@Mock
	ProjectDao dao;

	@Mock
	DashboardDao dashboardDao;

	@Mock
	Mapper mapper;

	@InjectMocks
	private ProjectService projectService = new ProjectServiceImpl();

	@Test
	public void testSave() {
		Project project = new Project();
		project.setProjectName("testproject");
		project.setProjectDescription("test Desc");
		project.setActive(true);
		// Mockito expectations
		when(dao.saveProject(any(Project.class))).thenReturn("sucess");
		// Execute the method being tested
		projectService.save(project);

		verify(dao).saveProject(any(Project.class));
	}

	@Test
	public void testUpdate() {
		Project project = new Project();
		project.setProjectName("testproject");
		project.setProjectDescription("test Desc");
		project.setActive(true);
		// Mockito expectations
		when(dao.updateProject(any(Project.class))).thenReturn("sucess");
		// Execute the method being tested
		projectService.update(project);

		verify(dao).updateProject(any(Project.class));
	}

	@Test
	public void testDelete() {
		Project project = new Project();
		project.setProjectId("P001");
		project.setProjectName("testproject");
		project.setProjectDescription("test Desc");
		project.setActive(true);
		// Mockito expectations
		when(dao.deleteProject("P001")).thenReturn("sucess");
		// Execute the method being tested
		projectService.delete("P001");
	
	}

	@Test
	public void testGet() {
		List<Project> projectList = new ArrayList<Project>();
		Project project = new Project();
		project.setProjectId("P001");
		project.setProjectName("testproject");
		project.setProjectDescription("test Desc");
		project.setActive(true);
		projectList.add(project);
		when(dao.findAll()).thenReturn(projectList);
		List<Project> results = projectService.get();
		assertNotNull(results);
		assertEquals(projectList.size(), results.size());
	}

	@Test
	public void testFindProjectById() {
		Project project = new Project();
		project.setProjectId("P001");
		project.setProjectName("testproject");
		project.setProjectDescription("test Desc");
		project.setActive(false);
		when(dao.findProjectById("P001")).thenReturn(project);
		Project results = projectService.findProjectById("P001");
		assertNotNull(results);
		assertEquals("P001", results.getProjectId());
	}

	@Test
	public void testFindByCustomerId() {
		long customerId=12L;
		List<Project> projectList = new ArrayList<Project>();
		Project project = new Project();
		project.setProjectId("P001");
		project.setProjectName("testproject");
		project.setProjectDescription("test Desc");
		project.setLatitude("test");
		project.setLongitude("L1");
		project.setCreatedDate(new Date());
		project.setProjectLocation("BLR");
		project.setActive(true);
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerName("Shaym");
		project.setUpdatedDate(new Date());
		project.setCustomer(customer);
		projectList.add(project);
		when(dao.findByCustomerId(customerId)).thenReturn(projectList);
		List<Project> results = projectService.findByCustomerId(customerId);
		for (Project project2 : results) {
			Project newProject = new Project();
			newProject.setProjectId(project2.getProjectId());
			newProject.setProjectName(project2.getProjectName());
			newProject.setProjectDescription(project2.getProjectDescription());
			newProject.setLatitude(project2.getLatitude());
			newProject.setLongitude(project2.getLongitude());
			newProject.setCreatedDate(project2.getCreatedDate());
			newProject.setProjectLocation(project2.getProjectLocation());
			
		}
		assertNotNull(results);
		assertEquals(projectList.size(), results.size());
	}

	@Test
	public void testFindAllByCustomerId() {
		long customerId=12L;
		List<Project> projectList = new ArrayList<Project>();
		Project project = new Project();
		project.setProjectId("P001");
		project.setProjectName("testproject");
		project.setProjectDescription("test Desc");
		project.setLatitude("test");
		project.setLongitude("L1");
		project.setCreatedDate(new Date());
		project.setProjectLocation("BLR");
		project.setActive(true);
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerName("Shaym");
		project.setUpdatedDate(new Date());
		project.setCustomer(customer);
		projectList.add(project);
		when(dao.findAllByCustomerId(customerId)).thenReturn(projectList);
		List<Project> results = projectService.findAllByCustomerId(customerId);
		assertNotNull(results);
		assertEquals(projectList.size(), results.size());
	}

	@Test
	public void testFindProjectDetails() {
		ProjectDto project = new ProjectDto();
		project.setProjectId("P001");
		project.setProjectName("testproject");
		project.setProjectDescription("test Desc");
		project.setLatitude("test");
		project.setLongitude("L1");
		project.setCreatedDate(new Date());
		project.setProjectLocation("BLR");
		project.setActive(true);
		Project project1 = new Project();
		project1.setProjectId("P001");
		project1.setProjectName("testproject");
		project1.setProjectDescription("test Desc");
		project1.setLatitude("test");
		project1.setLongitude("L1");
		project1.setCreatedDate(new Date());
		project1.setProjectLocation("BLR");
		project1.setActive(true);
		when(mapper.mapProjectToProjectDto(project1)).thenReturn(project);
		when(dao.findProjectById("P001")).thenReturn(project1);
		ProjectDto results = projectService.findProjectDetails("P001");
		assertNotNull(results);
		//assertEquals(projectList.size(), results.size());
	}
	@Test(expected=RuntimeException.class)
	public void testFindProjectDetails_faliure() {
		ProjectDto project = new ProjectDto();
		project.setProjectId("P001");
		project.setProjectName("testproject");
		project.setProjectDescription("test Desc");
		project.setLatitude("test");
		project.setLongitude("L1");
		project.setCreatedDate(new Date());
		project.setProjectLocation("BLR");
		project.setActive(true);
		Project project1 = new Project();
		project1.setProjectId("P001");
		project1.setProjectName("testproject");
		project1.setProjectDescription("test Desc");
		project1.setLatitude("test");
		project1.setLongitude("L1");
		project1.setCreatedDate(new Date());
		project1.setProjectLocation("BLR");
		project1.setActive(true);
		when(mapper.mapProjectToProjectDto(project1)).thenReturn(project);
		when(dao.findProjectById("P001")).thenReturn(null);
		//when(dao.findProjectById("P001")).thenReturn(project1);
		ProjectDto results = projectService.findProjectDetails("P001");
		//assertNotNull(results);
		//assertEquals(projectList.size(), results.size());
	}

	@Test
	public void testUpdateProjectActiveState() {
		Project project1 = new Project();
		project1.setProjectId("P001");
		project1.setProjectName("testproject");
		project1.setProjectDescription("test Desc");
		project1.setLatitude("test");
		project1.setLongitude("L1");
		project1.setCreatedDate(new Date());
		project1.setProjectLocation("BLR");
		project1.setActive(true);
		when(dao.findProjectById("P001")).thenReturn(project1);
		when(dao.updateProject(project1)).thenReturn("success");
		
		projectService.updateProjectActiveState("P001");
	}

	@Test
	public void testUpdateProjectActiveState_NotActive() {
		Project project1 = new Project();
		project1.setProjectId("P001");
		project1.setProjectName("testproject");
		project1.setProjectDescription("test Desc");
		project1.setLatitude("test");
		project1.setLongitude("L1");
		project1.setCreatedDate(new Date());
		project1.setProjectLocation("BLR");
		project1.setActive(false);
		when(dao.findProjectById("P001")).thenReturn(project1);
		when(dao.updateProject(project1)).thenReturn("success");
		
		projectService.updateProjectActiveState("P001");
	}

}
