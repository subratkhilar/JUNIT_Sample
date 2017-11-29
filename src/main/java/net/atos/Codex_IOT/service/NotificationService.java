package net.atos.Codex_IOT.service;

import net.atos.Codex_IOT.model.EventModel;
import net.atos.Codex_IOT.model.PushNotification;
import net.atos.Codex_IOT.pojo.Event;




public interface NotificationService {

	boolean notificationtouser();

	boolean notifyfromeventtable(EventModel eventm);

	/*boolean notificationtouser(PushNotification pushNotification);*/

}
