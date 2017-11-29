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

import net.atos.Codex_IOT.dao.TemperatureDao;
import net.atos.Codex_IOT.dao.TemperatureRepo;
import net.atos.Codex_IOT.pojo.Sensor;
import net.atos.Codex_IOT.pojo.Temperature;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureServiceImplTest {
	@Mock
	public TemperatureRepo rep;

	@Mock
	public TemperatureDao dao;
	@InjectMocks
	private TemperatureService temparatureService = new TemperatureServiceImpl();
	private Temperature temp = new Temperature();

	@Before
	public void beforeTest() {
		temp.setTempId(1L);
		temp.setTemperature(10);
		temp.setDate(new Date());
		Sensor sensor = new Sensor();
		sensor.setSensorId("S01");
		temp.setSensor(sensor);
	}

	@Test
	public void testGetTemperature() {
		List<Temperature> list = new ArrayList<Temperature>();
		list.add(temp);
		when(dao.getData()).thenReturn(list);
		List<Temperature> results = temparatureService.getTemperature();
		assertEquals(1, results.size());
	}

	@Test
	public void testGetTempbyID() {
		List<Temperature> list = new ArrayList<Temperature>();
		list.add(temp);
		when(dao.getDataByID(temp.getSensor().getSensorId())).thenReturn(list);
		List<Temperature> results = temparatureService.getTempbyID(temp.getSensor().getSensorId());
		assertEquals(1, results.size());
	}

	@Test
	public void testSave() {
		when(dao.saveTemperature(any(Temperature.class))).thenReturn("success");
		// Execute the method being tested
		String result = temparatureService.save(temp);
		assertNotNull(result);
		verify(dao).saveTemperature(any(Temperature.class));
	}

}
