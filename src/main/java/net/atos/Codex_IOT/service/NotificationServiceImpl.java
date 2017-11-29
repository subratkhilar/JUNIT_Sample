package net.atos.Codex_IOT.service;

import java.util.List;





import net.atos.Codex_IOT.pojo.Event;
import net.atos.Codex_IOT.pojo.User;
import net.atos.Codex_IOT.dao.EventDao;
import net.atos.Codex_IOT.dao.UserDao;
import net.atos.Codex_IOT.model.Data;
import net.atos.Codex_IOT.model.EventModel;
import net.atos.Codex_IOT.model.PushNotification;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;



@Transactional
@Repository

public class NotificationServiceImpl implements NotificationService {
	
	public static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";
	public static final String DeviceId = "cyF1nOfQkNQ:APA91bHZ_GTL10go_RTLBSjRUf7xam9NeGzw3iXWuIq12UU8Esb1rfXXpidQeTrk2TSPfo12xZLoLRQA1eOXyXktRKmm8UAMIt3OeVVDL7PC1rn44V5-0ZSNP5zbb_eSTf24WJubmN33";
	
	

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private EventService evtsservice;
	
		
	private static final Logger logger = Logger.getLogger(NotificationServiceImpl.class);   //Apache logger
	/*@SuppressWarnings("unchecked")
	@Override
	public boolean notificationtouser(PushNotification pushNotification) {
			
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public boolean notificationtouser() {

		System.getProperties().put("http.proxyHost","proxy-in.glb.my-it-solutions.net");
		System.getProperties().put( "http.proxyPort", "84" );
		System.getProperties().put("https.proxyHost","proxy-in.glb.my-it-solutions.net");
		System.getProperties().put( "https.proxyPort", "84" );
		
	logger.info("inside notification serviceimpl ");
	//User notifyingUser = this.userDao.getUserbyId(Long.parseLong(pushNotification.getTo()));
	JSONObject request =new JSONObject();
	Data data = new Data();
	data.setEventdesc("you have iot notification: " +"temperatureis high");
	request.put("to",DeviceId.trim());
	request.put("data", data);
	HttpHeaders header = new HttpHeaders();
	header.setContentType(MediaType.APPLICATION_JSON);
	header.set("Authorization","key = AIzaSyCe8rRGv2A72osp4irGSXUQXJbWUogN9FU");
    System.out.println(request);
	
	RestTemplate restTemplate = new RestTemplate();
	@SuppressWarnings("rawtypes")
	HttpEntity entity = new HttpEntity(request,header);
		logger.info("http fcm header");
		ResponseEntity<Object> response=restTemplate.exchange(FCM_URL, HttpMethod.POST, entity, Object.class);
		if(response==null)
			return false;
		return true;
	}



	@SuppressWarnings("unchecked")
	public boolean notifyfromeventtable(EventModel eventm) {
		logger.info("inside notification serviceimpl ");
		System.getProperties().put("http.proxyHost","proxy-in.glb.my-it-solutions.net");
		System.getProperties().put( "http.proxyPort", "84" );
		System.getProperties().put("https.proxyHost","proxy-in.glb.my-it-solutions.net");
		System.getProperties().put( "https.proxyPort", "84" );
		
		
		List<Event> events = evtsservice.getallevent();
	for (Event eve : events) {
	/*	try {
			Thread.sleep(30000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

		Data data2 = new Data();
		//data2.setEventId(eve.getEventId());
		data2.setEventdesc(eve.getEventdesc());
		data2.setEventtype(eve.getEventtype());
		data2.setCreatedDate(eve.getCreatedDate());
		data2.setSeverity(eve.getSeverity());
		data2.setSensorId(eve.getSensor().getSensorId());
		
		logger.info("inside notification serviceimpl 2");
		User notifyingUser = this.userDao.getUserbyId(Long.parseLong(eventm.getTo()));
		JSONObject request =new JSONObject();
//		Data data = new Data();
//		data.setMessage("you have iot notification: " +event.getData());
		/*request.put("to",DeviceId.trim());*/
		request.put("to", notifyingUser.getDeviceToken());
		request.put("data", data2);
		System.out.println(data2);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.set("Authorization","key = AIzaSyCe8rRGv2A72osp4irGSXUQXJbWUogN9FU");
	    System.out.println(request);
		
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("rawtypes")
		HttpEntity entity = new HttpEntity(request,header);
			logger.info("http fcm header");
			ResponseEntity<Object> response=restTemplate.exchange(FCM_URL, HttpMethod.POST, entity, Object.class);
			if(response==null)
				return false;
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 	}	
		return true;
	}
	
	
}
