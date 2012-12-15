package br.com.m2msolutions.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import br.com.m2msolutions.client.container.DtoCategory;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;

public class DtoCriticalEventsInfo implements Serializable {

	private static final long serialVersionUID = 3941988994703909425L;

	// TODO no futuro será um mapa de categorias e quantidades de eventos
	// daquela categoria
	private HashMap<DtoCategory, Integer> eventsCount;
	private ArrayList<DtoCriticalEvent> events;
	
	public DtoCriticalEventsInfo() {
	
	}
	public HashMap<DtoCategory, Integer> getEventsCount() {
		return eventsCount;
	}

	public void setEventsCount(HashMap<DtoCategory, Integer> eventsCount) {
		this.eventsCount = eventsCount;
	}

	public ArrayList<DtoCriticalEvent> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<DtoCriticalEvent> events) {
		this.events = events;
	}

}
