package net.atos.Codex_IOT.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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

import net.atos.Codex_IOT.dao.AssetDao;
import net.atos.Codex_IOT.dao.CustomerDao;
import net.atos.Codex_IOT.dao.EventDao;
import net.atos.Codex_IOT.dao.ProjectDao;
import net.atos.Codex_IOT.dao.SensorDao;
import net.atos.Codex_IOT.model.EventModels;
import net.atos.Codex_IOT.model.IOTEventStatusModel;
import net.atos.Codex_IOT.pojo.Asset;
import net.atos.Codex_IOT.pojo.Customer;
import net.atos.Codex_IOT.pojo.Event;
import net.atos.Codex_IOT.pojo.Project;
import net.atos.Codex_IOT.pojo.Sensor;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceImplTest {
	@Mock
	EventDao eventdao;

	@Mock
	ProjectDao projectdao;

	@Mock
	AssetDao assetdao;
	@Mock
	SensorDao sensordao;
	@Mock
	CustomerDao customerdao;
	@InjectMocks
	private EventService eventService = new EventServiceImpl();
	Event event = new Event();

	@Before
	public void beforeTest() {
		event.setEventId(100L);
		event.setEventdesc("sample desc");
		event.setEventtype("desk");
		event.setStatus("completed");
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerName("Amit");
		customer.setCustomerLocation("BLR");
		customer.setCreatedDate(new Date());
		event.setCustomer(customer);
		Project project = new Project();
		project.setProjectId("p001");
		project.setActive(true);
		project.setProjectName("COdexIot");
		event.setProject(project);
		Sensor sensor = new Sensor();
		sensor.setSensorId("sn001");
		sensor.setSensorDatatype("type1");
		sensor.setSensorsName("Test sensor");
		Asset asset = new Asset();
		asset.setAssetId("As001");
		sensor.setAsset(asset);
		event.setSensor(sensor);
		event.setAsset(asset);
	}

	@Test
	public void testGetallevent() {
		List<Event> list = new ArrayList<Event>();
		list.add(event);
		when(eventdao.getallevent()).thenReturn(list);
		List<Event> results = eventService.getallevent();
		assertEquals(1, results.size());

	}

	@Test
	public void testGeteventbystatus() {
		List<Event> list = new ArrayList<Event>();

		list.add(event);
		when(eventdao.geteventbystatus(event.getStatus())).thenReturn(list);
		List<Event> eventList = eventService.geteventbystatus(event.getStatus());
		// Validation
		assertNotNull(eventList);
		assertEquals(list.size(), eventList.size());
	}

	@Test
	public void testUpdateEventByEventId() {
		IOTEventStatusModel iotevent = new IOTEventStatusModel();
		boolean msg = false;
		event.setStatus("failure");
		iotevent.setEventId("100");
		when(eventdao.getEventByEventId(1L)).thenReturn(event);
		boolean result = eventService.updateEventByEventId(iotevent);
		assertFalse(result);
	}

	@Test
	public void testUpdateEventByEventId_sucess() {
		IOTEventStatusModel iotevent = new IOTEventStatusModel();
		boolean msg = false;
		iotevent.setEventId("23");
		iotevent.setStatus("success");
		event.setStatus("success");
		when(eventdao.getEventByEventId(Long.parseLong(iotevent.getEventId()))).thenReturn(event);
		when(eventdao.updateEventStatus(event)).thenReturn(true);

		boolean result = eventService.updateEventByEventId(iotevent);
		// assertFalse(result);
	}

	@Test
	public void testGetallEventbyCustomerID() {
		long customerId = 1L;

		List<EventModels> eventModelList = new ArrayList<EventModels>();
		EventModels model = new EventModels();
		model.setAssetId("As001");
		model.setEventdesc("sample desc");
		model.setEventId(23L);
		model.setEventtype("Type1");
		eventModelList.add(model);
		event.setStatus("success");
		List<Event> eventList = new ArrayList<Event>();
		eventList.add(event);
		when(eventdao.getallEventbyCustomerID(event.getCustomer().getCustomerId())).thenReturn(eventList);
		List<EventModels> eventModelList1 = eventService.getallEventbyCustomerID(event.getCustomer().getCustomerId());
		// assertNotNull(eventModelList1);
	}

	@Test
	public void testNoOfEventsByCustomerId() {
		event.setStatus("success");
		when(eventdao.noOfEventsByCustomerId(event.getCustomer().getCustomerId())).thenReturn(1);
		int result = eventService.noOfEventsByCustomerId(event.getCustomer().getCustomerId());
		assertEquals(1, result);

	}

	@Test(expected = RuntimeException.class)
	public void testUpdateAllEventStatus_faliure() {
		List<IOTEventStatusModel> eventStatusModels = new ArrayList<IOTEventStatusModel>();
		IOTEventStatusModel model = new IOTEventStatusModel();
		model.setEventId("100");
		model.setStatus("success");
		eventStatusModels.add(model);
		eventService.updateAllEventStatus(eventStatusModels);

	}

}
