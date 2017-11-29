package net.atos.Codex_IOT.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import net.atos.Codex_IOT.dao.CustomerDao;
import net.atos.Codex_IOT.dao.DashboardDao;
import net.atos.Codex_IOT.pojo.Asset;
import net.atos.Codex_IOT.pojo.Customer;
import net.atos.Codex_IOT.pojo.Project;
import net.atos.Codex_IOT.pojo.Sensor;

@RunWith(MockitoJUnitRunner.class)
public class DashboardServiceImplTest {

	@Mock
	DashboardDao dashboardDao;

	@Mock
	CustomerDao customerDao;

	@InjectMocks
	private DashboardService dashbordService = new DashboardServiceImpl();

	@Test
	public void testGetNoOfProjects() {
		String projectId="As001";
		Project project = new Project();
		project.setActive(true);
		project.setCreatedDate(new Date());
		Asset asset = new Asset();
		asset.setAssetId("As001");
		asset.setAssetDesc("test desc");
		asset.setProject(project);
		String customerId ="1";
		Customer customer = new Customer();
		customer.setActive(true);
		customer.setCustomerId(1L);
		project.setCustomer(customer);
		
		when(dashboardDao.getNoOfProjects(customerId)).thenReturn(1);
		int result = dashbordService.getNoOfProjects(customerId);
		assertEquals(1, result);
		
		/*asset1.setAssetId("As001");
		asset1.setAssetDesc("test1");

		when(astdao.getAssetbyId("As001")).thenReturn(asset1);
		Asset newAsset = assertService.getAssetbyId("As001");
		// Validation
		assertNotNull(newAsset);
		assertEquals("As001", newAsset.getAssetId());*/
	}

	@Test
	public void testGetNoOfAssets() {
	//	fail("Not yet implemented");
	}

	@Test
	public void testGetNoOfSensors() {
		String projectId="As001";
		Project project = new Project();
		project.setActive(true);
		project.setCreatedDate(new Date());
		Asset asset = new Asset();
		asset.setAssetId("As001");
		asset.setAssetDesc("test desc");
		asset.setProject(project);
		String customerId ="1";
		Customer customer = new Customer();
		customer.setActive(true);
		customer.setCustomerId(1L);
		project.setCustomer(customer);
		Sensor sensor = new Sensor();
		sensor.setActive(true);
		sensor.setAsset(asset);
		sensor.setSensorsName("Sn001");
		sensor.setSensorId("Sn001");
		when(dashboardDao.getNoOfSensors(customerId)).thenReturn(1);
		int result= dashbordService.getNoOfSensors(customerId);
		assertEquals(1, result);
		//fail("Not yet implemented");
	}

	@Test
	public void testGetNoOfAssetsByProjectId() {
		String projectId="As001";
		Project project = new Project();
		project.setActive(true);
		project.setCreatedDate(new Date());
		Asset asset = new Asset();
		asset.setAssetId("As001");
		asset.setAssetDesc("test desc");
		asset.setProject(project);
		when(dashboardDao.getNoOfAssetsByProjectId(projectId)).thenReturn(1);
		int result= dashbordService.getNoOfAssetsByProjectId(projectId);
		assertEquals(1, result);
	}

	@Test
	public void testGetNoOfSensorsByAssetId() {
		String assetId="As001";
		Asset asset = new Asset();
		asset.setActive(true);
		asset.setAssetId(assetId);
		asset.setAssetDesc("test Desc");
		when(dashboardDao.getNoOfSensorsByAssetId(assetId)).thenReturn(1);
		int result= dashbordService.getNoOfSensorsByAssetId(assetId);
		assertEquals(1, result);
	}

}
