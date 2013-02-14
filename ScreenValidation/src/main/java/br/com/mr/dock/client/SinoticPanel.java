package br.com.mr.dock.client;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.ui.RootPanel;

public abstract class SinoticPanel extends LayoutContainer{
	public abstract Component getHeader();
	
	public void show() {
		super.show();

		setPosition(300, 100);
		if (!isAttached()) {
			RootPanel.get().add(this);
		}
		el().makePositionable(true);

		this.setStyleAttribute("opacity", "0.976543");
		this.el().dom.setAttribute("opacity", "0.976543");
	}
}
