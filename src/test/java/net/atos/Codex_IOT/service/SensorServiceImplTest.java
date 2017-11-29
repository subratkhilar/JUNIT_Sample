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

import net.atos.Codex_IOT.dao.SensorDao;
import net.atos.Codex_IOT.pojo.Asset;
import net.atos.Codex_IOT.pojo.Customer;
import net.atos.Codex_IOT.pojo.Project;
import net.atos.Codex_IOT.pojo.Sensor;

@RunWith(MockitoJUnitRunner.class)
public class SensorServiceImplTest {
	@Mock
	private SensorDao sensordao;
	@InjectMocks
	SensorService sensorService = new SensorServiceImpl();
	private Sensor sensor = new Sensor();

	@Before
	public void beforeTest() {
		sensor.setSensorId("SN001");
		sensor.setActive(true);
		sensor.setCreatedDate(new Date());
		sensor.setSensorDescription("test desc");
		sensor.setSensorsName("test Sensor");
		sensor.setSensorDatatype("TYPE1");
		sensor.setSensorSerialNumber("SL001");
		sensor.setUpdatedDate(new Date());
		Customer customer = new Customer();
		customer.setActive(true);
		customer.setCustomerId(12L);
		customer.setCustomerName("Xyz");
		Project project = new Project();
		project.setProjectId("p001");
		project.setActive(true);
		project.setProjectName("COdexIot");
		project.setCustomer(customer);
		Asset asset = new Asset();
		asset.setAssetId("As001");
		asset.setProject(project);
		sensor.setAsset(asset);

	}

	@Test
	public void testGetSensor() {
		List<Sensor> sensorList = new ArrayList<Sensor>();
		// Sensor sensor = new Sensor();
		sensorList.add(sensor);
		when(sensordao.getSensors()).thenReturn(sensorList);
		// Execute the method being tested
		List<Sensor> newSensorList = sensorService.getSensor();
		// Validation
		assertNotNull(newSensorList);
		assertEquals(sensorList.size(), newSensorList.size());

	}

	@Test
	public void testGetSensorByAssetId() {
		List<Sensor> sensorList = new ArrayList<Sensor>();
		sensorList.add(sensor);
		when(sensordao.getSensorsByAssetId(sensor.getSensorId())).thenReturn(sensorList);
		List<Sensor> newSensorList = sensorService.getSensorByAssetId(sensor.getSensorId());
		// Validation
		assertNotNull(newSensorList);
		assertEquals(sensorList.size(), newSensorList.size());

	}

	@Test
	public void testGetSensorsByCustomerId() {
		long customerId = 12L;
		List<Sensor> sensorList = new ArrayList<Sensor>();
		sensorList.add(sensor);
		when(sensordao.getSensorsByCustomerId(sensor.getAsset().getProject().getCustomer().getCustomerId()))
				.thenReturn(sensorList);
		List<Sensor> newSensorList = sensorService
				.getSensorsByCustomerId(sensor.getAsset().getProject().getCustomer().getCustomerId());
		// Validation
		assertNotNull(newSensorList);
		assertEquals(sensorList.size(), newSensorList.size());

	}

	@Test
	public void testUpdateSensorActiveState() {
		when(sensordao.getSensorById(sensor.getSensorId())).thenReturn(sensor);
		when(sensordao.updateSensor(sensor)).thenReturn("success");
		sensorService.updateSensorActiveState(sensor.getSensorId());
	}

	@Test
	public void testUpdateSensorInActiveState() {
		sensor.setActive(false);
		when(sensordao.getSensorById(sensor.getSensorId())).thenReturn(sensor);
		when(sensordao.updateSensor(sensor)).thenReturn("success");
		sensorService.updateSensorActiveState(sensor.getSensorId());
	}

	@Test
	public void testSaveSensor() {
		when(sensordao.saveSensor(sensor)).thenReturn(sensor);
		// Execute the method being tested
		Sensor result = sensorService.saveSensor(sensor);
		assertEquals(sensor.getSensorId(), result.getSensorId());
		verify(sensordao).saveSensor(any(Sensor.class));
	}

	@Test
	public void testGetSensorById() {
		when(sensordao.getSensorById(sensor.getSensorId())).thenReturn(sensor);
		// Execute the method being tested
		Sensor result = sensorService.getSensorById(sensor.getSensorId());
		assertEquals("SN001", result.getSensorId());

	}

	@Test
	public void testUpdateSensor() {
		// Mockito expectations
		when(sensordao.updateSensor(any(Sensor.class))).thenReturn("success");
		// Execute the method being tested
		String result = sensorService.updateSensor(sensor);
		assertEquals("success", result);
		verify(sensordao).updateSensor(any(Sensor.class));
	}

	@Test
	public void testGetallSensorsByCustomerId() {
		List<Sensor> sensorList = new ArrayList<Sensor>();
		sensorList.add(sensor);
		when(sensordao.getallSensorsByCustomerId(sensor.getAsset().getProject().getCustomer().getCustomerId()))
				.thenReturn(sensorList);
		List<Sensor> newSensorList = sensorService
				.getallSensorsByCustomerId(sensor.getAsset().getProject().getCustomer().getCustomerId());
		// Validation
		assertNotNull(newSensorList);
		assertEquals(sensorList.size(), newSensorList.size());
	}

	@Test
	public void testGetallSensorByAssetId() {
		List<Sensor> sensorList = new ArrayList<Sensor>();
		sensorList.add(sensor);
		when(sensordao.getallSensorsByAssetId(sensor.getAsset().getAssetId())).thenReturn(sensorList);
		List<Sensor> newSensorList = sensorService.getallSensorByAssetId(sensor.getAsset().getAssetId());
		// Validation
		assertNotNull(newSensorList);
		assertEquals(sensorList.size(), newSensorList.size());
	}

	@Test
	public void testGetallSensorCustomerIdTrue() {
		sensor.setActive(true);
		long customerId = 12L;
		List<Sensor> sensorList = new ArrayList<Sensor>();
		sensorList.add(sensor);
		when(sensordao.getallSensorCustomerIdTrue(sensor.getAsset().getProject().getCustomer().getCustomerId()))
				.thenReturn(sensorList);
		List<Sensor> newSensorList = sensorService
				.getallSensorCustomerIdTrue(sensor.getAsset().getProject().getCustomer().getCustomerId());
		// Validation
		assertNotNull(newSensorList);
		assertEquals(sensorList.size(), newSensorList.size());
	}

}
