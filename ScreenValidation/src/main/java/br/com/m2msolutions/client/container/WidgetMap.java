package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.ui.Widget;

public class WidgetMap extends BaseWidget{
	public WidgetMap() {
		addWidget(createPrincipalWidget());
	}

	private Widget createPrincipalWidget() {
		LayoutContainer container = new LayoutContainer();
		
		return container;
	}
}
