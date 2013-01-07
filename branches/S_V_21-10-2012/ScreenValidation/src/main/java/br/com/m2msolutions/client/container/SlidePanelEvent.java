package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.event.BaseEvent;

public class SlidePanelEvent extends BaseEvent {

	private final DtoPredefinedMessage message;

	public SlidePanelEvent(Object source, DtoPredefinedMessage message) {
		super(source);
		this.message = message;
	}

	public DtoPredefinedMessage getMessage() {
		return message;
	}
}
