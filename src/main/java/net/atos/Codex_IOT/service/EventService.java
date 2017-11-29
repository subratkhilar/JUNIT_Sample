package net.atos.Codex_IOT.service;

import java.util.List;





import net.atos.Codex_IOT.model.EventModels;
import net.atos.Codex_IOT.model.IOTEventStatusModel;
import net.atos.Codex_IOT.pojo.Event;

public interface EventService {

	List<Event> getallevent();

	List<Event> geteventbystatus(String status);

	boolean updateEventByEventId(IOTEventStatusModel iotevent);

	List<EventModels> getallEventbyCustomerID(long customerId);

	public int noOfEventsByCustomerId(long customerId);

	public void updateAllEventStatus(List<IOTEventStatusModel> eventStatusModels);

}
