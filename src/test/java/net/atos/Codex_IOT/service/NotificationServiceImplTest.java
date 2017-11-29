/*package net.atos.Codex_IOT.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import net.atos.Codex_IOT.dao.EventDao;
import net.atos.Codex_IOT.dao.UserDao;
import net.atos.Codex_IOT.model.EventModel;
import net.atos.Codex_IOT.pojo.Asset;
import net.atos.Codex_IOT.pojo.Customer;
import net.atos.Codex_IOT.pojo.Event;
import net.atos.Codex_IOT.pojo.Sensor;
import net.atos.Codex_IOT.pojo.User;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceImplTest {
	@Mock
	private UserDao userDao;

	@Mock
	private EventDao eventDao;
	@InjectMocks
	private NotificationService noficationService = new NotificationServiceImpl();
	@Mock
	private EventService evtsservice = new EventServiceImpl();

	@Test
	public void testNotificationtouser() {
		boolean result = noficationService.notificationtouser();
	}

	@Test
	public void testNotifyfromeventtable() {
		EventModel eventm = new EventModel();
		eventm.setTo("12");
		long id =12L;
		User user = new User();
		user.setUserId(12L);
		Customer customer = new Customer();
		customer.setCustomerId(21L);
		customer.setCustomerName("Shaym");
		user.setCustomer(customer);
		user.setDeviceToken("12");
		List<Event> list = new ArrayList<Event>();
		Event event = new Event();
		event.setEventId(100L);
		event.setEventdesc("sample desc");
		event.setEventtype("desk");
		
		Sensor sensor = new Sensor();
		sensor.setSensorId("sn001");
		sensor.setActive(true);
		event.setSensor(sensor);
		list.add(event);
		
		when(evtsservice.getallevent()).thenReturn(list);
		List<Event> events = evtsservice.getallevent();
		when(userDao.getUserbyId(id)).thenReturn(user);
		boolean  results = noficationService.notifyfromeventtable(eventm);
		//assertEquals(1, results.size());
	}

}
*/