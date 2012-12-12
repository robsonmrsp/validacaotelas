package br.com.m2msolutions.client.container;

import br.com.mr.dock.client.containers.Position;

import com.extjs.gxt.ui.client.Style.Direction;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.ui.RootPanel;

public class SlidePanel extends ContentPanelImp {
	private Component anchor;
	private Position position;
	private LayoutContainer headerContainer;
	private LayoutContainer messagesContainer;
	private LayoutContainer editContainer;
	private SearchBox searchBox;
	public SlidePanel() {
		initComponents();
	}

	private void initComponents() {
		setHeaderVisible(false);
		setSize("300px", "400px");
		setLayout(new BorderLayout());
		getHeaderContainer().setLayout(new BorderLayout());
		add(getHeaderContainer(), new BorderLayoutData(LayoutRegion.NORTH, 25.0f));
		add(getMessagesContainer(), new BorderLayoutData(LayoutRegion.CENTER));
		getEditContainer().setLayout(new BorderLayout());
		add(getEditContainer(), new BorderLayoutData(LayoutRegion.SOUTH, 35.0f));
	}

	public void hide() {
		el().slideOut(Direction.LEFT, FxConfig.NONE);
	}
	public void show() {
		super.show();

		setPosition(14 + anchor.getAbsoluteLeft() + anchor.getOffsetWidth(), anchor.getAbsoluteTop());
		if (!isAttached()) {
			RootPanel.get().add(this);
		}
		el().makePositionable(true);

		this.setStyleAttribute("opacity", "0.976543");
		this.el().dom.setAttribute("opacity", "0.976543");
		el().slideIn(Direction.RIGHT, FxConfig.NONE);
	}
	
	
	public void anchorAt(ContentPanelImp anchor, Position position) {
		this.anchor = anchor;
		this.position = position;
	}

	private LayoutContainer getHeaderContainer() {
		if (headerContainer == null) {
			headerContainer = new LayoutContainer();
			headerContainer.setBorders(true);
			headerContainer.add(getSearchBox(), new BorderLayoutData(LayoutRegion.EAST, 185.0f));
		}
		return headerContainer;
	}
	private LayoutContainer getMessagesContainer() {
		if (messagesContainer == null) {
			messagesContainer = new LayoutContainer();
			messagesContainer.setBorders(true);
		}
		return messagesContainer;
	}
	private LayoutContainer getEditContainer() {
		if (editContainer == null) {
			editContainer = new LayoutContainer();
			editContainer.setBorders(true);
		}
		return editContainer;
	}
	private SearchBox getSearchBox() {
		if (searchBox == null) {
			searchBox = new SearchBox();
			searchBox.setSize("200", "24");
		}
		return searchBox;
	}
}
