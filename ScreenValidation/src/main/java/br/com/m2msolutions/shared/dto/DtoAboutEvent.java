package br.com.m2msolutions.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DtoAboutEvent extends BaseModelData {

	private static final long serialVersionUID = -7149310209638501419L;

	public static final String EVENT_ID = "eventId";
	public static final String PROTOCOL = "protocol";
	public static final String START_EVENT = "startEvent";
	public static final String START_ATTENDANCE = "startAttendance";
	public static final String DURATION = "duration";
	public static final String OPERATOR = "operator";
	public static final String CONSLUSION = "conclusion";

	public DtoAboutEvent(Long eventId, String protocol, String startEvent, String startAttendance , String duration, String operatorName, String conclusionTime) {
		this();
		setEventId(eventId);
		setProtocol(protocol);
		setStartEvent(startEvent);
		setStartAttendance(startAttendance);
		setDuration(duration);
		setOperator(operatorName);
		setConclusion(conclusionTime);
	}

	public DtoAboutEvent() {

	}

	public String getStartEvent() {
		return get(START_EVENT);
	}

	public void setStartEvent(String startTime) {
		set(START_EVENT, startTime);
	}
	
	public String getStartAttendance() {
		return get(START_ATTENDANCE);
	}

	public void setStartAttendance(String startAttendance) {
		set(START_ATTENDANCE, startAttendance);
	}

	public void setOperator(String operator) {
		set(OPERATOR, operator);
	}

	public String getOperator() {
		return get(OPERATOR);
	}

	public void setProtocol(String protocol) {
		set(PROTOCOL, protocol);
	}

	public String getProtocol() {
		return get(PROTOCOL);
	}

	public String getDuration() {
		return get(DURATION);
	}

	public void setDuration(String duration) {
		get(DURATION, duration);
	}

	public String getConclusion() {
		return get(CONSLUSION);
	}

	public void setConclusion(String conclusion) {
		set(CONSLUSION, conclusion);
	}

	public Long getEventId() {
		return get(EVENT_ID);
	}

	public void setEventId(Long eventId) {
		set(EVENT_ID, eventId);
	}
}
