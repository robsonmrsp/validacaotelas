package br.com.m2msolutions.client;

import com.extjs.gxt.ui.client.event.BaseEvent;

public class LoginEvent extends BaseEvent {

	private final String passValue;

	public LoginEvent(Object source, String passValue) {
		super(source);
		this.passValue = passValue;
	}

	public String getPassValue() {
		return passValue;
	}

}
