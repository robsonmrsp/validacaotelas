package br.com.m2msolutions.server.actors;

import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class CriticalEventsActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public void onReceive(Object message) throws Exception {
		if (message instanceof DtoCriticalEvent) {
			
		} else {

		}
		unhandled(message);
	}
}