package net.atos.Codex_IOT.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import net.atos.Codex_IOT.dao.AssetDao;
import net.atos.Codex_IOT.dao.CustomerDao;
import net.atos.Codex_IOT.dao.EventDao;
import net.atos.Codex_IOT.dao.ProjectDao;
import net.atos.Codex_IOT.dao.SensorDao;
import net.atos.Codex_IOT.model.EventModels;
import net.atos.Codex_IOT.model.IOTEventStatusModel;
import net.atos.Codex_IOT.pojo.Event;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class EventServiceImpl implements EventService {

	// Apache logger
			@SuppressWarnings("unused")
			private static final Logger logger = Logger
					.getLogger(EventServiceImpl.class);
	
	private static final String CREATED="created";
	private static final String SUCCESS="success";

	@Autowired
	EventDao eventdao;
		
	@Autowired
	ProjectDao projectdao;
	 
	@Autowired
	AssetDao assetdao;
	
	@Autowired
	SensorDao sensordao;
	
	@Autowired
	CustomerDao customerdao;
	
	@Override
	public List<Event> getallevent() {
		logger.info("in event list");
	return eventdao.getallevent();
	}


	@Override
	public List<Event> geteventbystatus(String status) {
		logger.info("in event list by status");
		return eventdao.geteventbystatus(status);
	}


	@Override
	public boolean updateEventByEventId(IOTEventStatusModel iotevent) {
		logger.info("in update event list");
		boolean msg=false;
		Event event=eventdao.getEventByEventId(Long.parseLong(iotevent.getEventId()));
		if(event!=null && iotevent.getStatus().equalsIgnoreCase(SUCCESS))
			{
				event.setStatus(CREATED);
				if(this.eventdao.updateEventStatus(event))
					msg=true;
			}
		return msg;
	}


	@Override
	public List<EventModels> getallEventbyCustomerID(long customerId) {
		logger.info("in event list");
		
		final List<Event> eventlist= eventdao.getallEventbyCustomerID(customerId);
		System.out.println("events" + eventlist);
		
		if (eventlist.isEmpty())
		{
			return null; 		
		}
		
		List<EventModels> eventmodels = new ArrayList<>();
		for (Event eventm : eventlist )
		{
			EventModels eventmo = new EventModels();
		    eventmo.setEventtype(eventm.getEventtype());
		 	eventmo.setEventdesc(eventm.getEventdesc());
			eventmo.setSeverity(eventm.getSeverity());
			eventmo.setStatus(eventm.getStatus());
			eventmo.setCreatedDate(eventm.getCreatedDate());
			eventmo.setEventId(eventm.getEventId());
			eventmo.setProjectId(eventm.getProject().getProjectId());
			eventmo.setSensorId(eventm.getSensor().getSensorId());
			eventmo.setAssetId(eventm.getAsset().getAssetId());
			eventmodels.add(eventmo);
      	}
		return eventmodels;
	}


	@Override
	public int noOfEventsByCustomerId(long customerId) {
		return eventdao.noOfEventsByCustomerId(customerId);
	}


	@Override
	public void updateAllEventStatus(List<IOTEventStatusModel> eventStatusModels) {
		logger.info("inside updateAllEventStatus()");
		for (IOTEventStatusModel IOTEventStatusModel : eventStatusModels) {
			if (!this.updateEventByEventId(IOTEventStatusModel)){
				throw new RuntimeException();
					
			}
		}

	}

	



	}
	
	
	
	


