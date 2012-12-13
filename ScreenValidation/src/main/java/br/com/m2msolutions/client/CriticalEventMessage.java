package br.com.m2msolutions.client;

import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import de.novanic.eventservice.client.event.Event;
import de.novanic.eventservice.client.event.domain.Domain;
import de.novanic.eventservice.client.event.domain.DomainFactory;

public class CriticalEventMessage implements Event {
	
	public static final Domain SERVER_MESSAGE_DOMAIN = DomainFactory.getDomain("critical_events_service_domain");

	private static final long serialVersionUID = 2682737574806334424L;


	private final DtoCriticalEvent event;

	public CriticalEventMessage(DtoCriticalEvent event) {
		this.event = event;
	}

	public DtoCriticalEvent getCriticalEvent() {
		return event;
	}
}