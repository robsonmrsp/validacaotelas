package br.com.m2msolutions.client.sinotic.container;

import br.com.mr.dock.client.SinoticWindow;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;

public class SinoticContainer extends SinoticWindow {
	private TerminalContainer terminalContainer;
	private LayoutContainer layoutContainer;
	private LayoutContainer layoutContainer_1;

	public SinoticContainer() {
		initComponents();
	}

	private void initComponents() {
		setSize("500", "172");
		setLayout(new BorderLayout());
		add(getTerminalContainer(), new BorderLayoutData(LayoutRegion.WEST, 130.0f));
		add(getLayoutContainer(), new BorderLayoutData(LayoutRegion.EAST, 135.0f));
		add(getLayoutContainer_1(), new BorderLayoutData(LayoutRegion.CENTER));
	}

	private TerminalContainer getTerminalContainer() {
		if (terminalContainer == null) {
			terminalContainer = new TerminalContainer();
		}
		return terminalContainer;
	}

	private LayoutContainer getLayoutContainer() {
		if (layoutContainer == null) {
			layoutContainer = new LayoutContainer();
			layoutContainer.setBorders(true);
		}
		return layoutContainer;
	}

	private LayoutContainer getLayoutContainer_1() {
		if (layoutContainer_1 == null) {
			layoutContainer_1 = new LayoutContainer();
			layoutContainer_1.setBorders(true);
		}
		return layoutContainer_1;
	}

	@Override
	public Component getHeader() {
		return this;
	}
}
