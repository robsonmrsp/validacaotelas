package br.com.m2msolutions.client.container;

import br.com.m2msolutions.shared.dto.DtoOperator;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.widget.Component;

public class TransferEvent extends BaseEvent {

	private final DtoOperator operator;

	public TransferEvent(Component source, DtoOperator operator) {
		super(source);
		this.operator = operator;
	}

	public DtoOperator getOperator() {
		return operator;
	}
}
