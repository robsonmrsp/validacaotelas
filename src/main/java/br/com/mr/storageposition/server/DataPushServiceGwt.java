package br.com.mr.storageposition.server;

import javax.inject.Named;

import br.com.mr.storageposition.client.UserInfoPositionEvent;
import br.com.mr.storageposition.shared.UserInfoPosition;
import de.novanic.eventservice.client.event.Event;
import de.novanic.eventservice.client.event.domain.DomainFactory;
import de.novanic.eventservice.service.EventExecutorService;
import de.novanic.eventservice.service.EventExecutorServiceFactory;

@Named("dataPushService")
public class DataPushServiceGwt implements DataPushService<JsonPosition> {

	private EventExecutorService getEventExecutorService() {
		final EventExecutorServiceFactory theEventExecutorServiceFactory = EventExecutorServiceFactory.getInstance();
		return theEventExecutorServiceFactory.getEventExecutorService("DATA_PUSH");
	}

	@Override
	public void send(JsonPosition object) {
		getEventExecutorService().addEvent(DomainFactory.getDomain("DOMAIN_POSITION_EVENT"), createUserInfoPositionEvent(object));
	}

	private Event createUserInfoPositionEvent(JsonPosition jsonPosition) {
		UserInfoPositionEvent event = new UserInfoPositionEvent();
//		event.setUserInfoPosition(new UserInfoPosition(jsonPosition.getDeviceId(), jsonPosition.getLatitude(), jsonPosition.getLongitude(), imageSrc));
		return null;
	}

}
