package br.com.m2msolutions.client;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.m2msolutions.shared.dto.DtoCriticalEvent;

import com.google.gwt.dev.util.collect.HashMap;

public class DtoCriticalEventsInfo implements Serializable {

	private static final long serialVersionUID = 3941988994703909425L;

	// TODO no futuro será um mapa de categorias e quantidades de eventos
	// daquela categoria
	private HashMap<String, Integer> eventsCount;
	private ArrayList<DtoCriticalEvent> events;
	
	public DtoCriticalEventsInfo() {
	
	}
	public HashMap<String, Integer> getEventsCount() {
		return eventsCount;
	}

	public void setEventsCount(HashMap<String, Integer> eventsCount) {
		this.eventsCount = eventsCount;
	}

	public ArrayList<DtoCriticalEvent> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<DtoCriticalEvent> events) {
		this.events = events;
	}

}
