package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.event.BaseEvent;

public class SearchBoxEvent extends BaseEvent {

	//valor exibido no textfield
	private String inputValue;

	public SearchBoxEvent(Object source, String inputValue) {
		super(source);
		this.inputValue = inputValue;
	}

	public String getInputValue() {
		return inputValue;
	}
}
