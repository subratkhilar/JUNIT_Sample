package net.atos.Codex_IOT.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import net.atos.Codex_IOT.dao.WorkflowDao;
import net.atos.Codex_IOT.pojo.Asset;
import net.atos.Codex_IOT.pojo.Customer;
import net.atos.Codex_IOT.pojo.Project;
import net.atos.Codex_IOT.pojo.Sensor;
import net.atos.Codex_IOT.pojo.Workflow;

@RunWith(MockitoJUnitRunner.class)
public class WorkflowServiceImplTest {
	@Mock
	private WorkflowDao dao;
	@InjectMocks
	private WorkflowService workFlowService = new WorkflowServiceImpl();
	private Workflow workFlow = new Workflow();
	private List<Workflow> list = new ArrayList<Workflow>();
	private Sensor sensor = new Sensor();

	@Before
	public void beforeTest() {

		workFlow.setActive(true);
		workFlow.setCreatedDate(new Date());
		workFlow.setWorkflowId(1L);
		Project project = new Project();
		project.setProjectName("testproject");
		project.setProjectDescription("test Desc");
		project.setActive(true);
		project.setProjectId("pl001");
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerName("Shaym");
		project.setUpdatedDate(new Date());
		project.setCustomer(customer);

		sensor.setSensorId("SN001");
		sensor.setActive(true);
		sensor.setCreatedDate(new Date());
		sensor.setSensorDescription("test desc");
		sensor.setSensorsName("test Sensor");
		sensor.setSensorDatatype("TYPE1");
		sensor.setSensorSerialNumber("SL001");
		sensor.setUpdatedDate(new Date());
		Asset asset = new Asset();
		asset.setAssetId("AS001");
		asset.setActive(true);
		asset.setAssetDesc("test desc");
		sensor.setAsset(asset);
		workFlow.setEventType("Type1");
		workFlow.setCustomer(customer);
		workFlow.setProject(project);
		workFlow.setSensor(sensor);
		list.add(workFlow);
	}

	@Test
	public void testSave() {

		when(dao.save(any(Workflow.class))).thenReturn("success");
		// Execute the method being tested
		String result = workFlowService.save(workFlow);
		assertEquals("success", result);
		verify(dao).save(any(Workflow.class));

	}

	@Test
	public void testUpdate() {
		when(dao.update(any(Workflow.class))).thenReturn("success");
		// Execute the method being tested
		String result = workFlowService.update(workFlow);
		assertEquals("success", result);
		verify(dao).update(any(Workflow.class));
	}

	@Test
	public void testGetbyid() {
		when(dao.getbyid(workFlow.getWorkflowId())).thenReturn(workFlow);
		// Execute the method being tested
		Workflow result = workFlowService.getbyid(workFlow.getWorkflowId());
		assertNotNull(result);

	}

	@Test
	public void testGetbyProjectId() {
		when(dao.getbyProjectId(workFlow.getProject().getProjectId())).thenReturn(list);
		List<Workflow> workFlowList = workFlowService.getbyProjectId(workFlow.getProject().getProjectId());
		assertNotNull(workFlowList);
		assertEquals(list, workFlowList);

	}

	@Test
	public void testGetbyCustomerId() {
		when(dao.getbyCustomerId(workFlow.getCustomer().getCustomerId())).thenReturn(list);
		List<Workflow> workFlowList = workFlowService.getbyCustomerId((workFlow.getCustomer().getCustomerId()));
		assertNotNull(workFlowList);
		assertEquals(list, workFlowList);
	}

	@Test
	public void testAllSensorsforWorkflow() {
		List<Sensor> sensorList = new ArrayList<>();
		sensorList.add(sensor);
		when(dao.allSensorsforWorkflow(sensor.getAsset().getAssetId(), workFlow.getEventType())).thenReturn(sensorList);
		List<Sensor> resultList = workFlowService.allSensorsforWorkflow(sensor.getAsset().getAssetId(),
				workFlow.getEventType());
		assertNotNull(resultList);
		assertEquals(sensorList.size(), resultList.size());
	}

}
