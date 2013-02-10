package br.com.m2msolutions.client.sinotic.container;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;

public class TerminalContainer extends LayoutContainer {
	private LayoutContainer leftContainer;
	private LayoutContainer rightContainer;
	private LayoutContainer info3Container;
	private Html info3;
	private LayoutContainer info2Container;
	private Html info2;
	private LayoutContainer info1Container;
	private Html info1;
	private LayoutContainer terminalHeaderContainer;
	private LayoutContainer gridContainer;
	private ListView<DTOSimpleVehicle> listView;
	private Html name;
	private LayoutContainer countContainer;
	private Html count;
	private LayoutContainer nameContainer;

	public TerminalContainer() {
		initComponents();
	}

	private void initComponents() {
		setSize("132", "172");
		setStyleName("sinotic-terminal");
		addStyleName("overflow-visible");
		setLayout(new RowLayout(Orientation.HORIZONTAL));
		add(getLeftContainer());
		add(getRightContainer());
	}

	private LayoutContainer getLeftContainer() {
		if (leftContainer == null) {
			leftContainer = new LayoutContainer();
			leftContainer.setSize("19", "172");
			leftContainer.addStyleName("overflow-visible");
			leftContainer.setId("leftContainer");
			leftContainer.setBorders(false);
			leftContainer.setLayout(new RowLayout(Orientation.VERTICAL));
			leftContainer.add(getInfo3Container());
			leftContainer.add(getInfo2Container());
			leftContainer.add(getInfo1Container());
		}
		return leftContainer;
	}

	private LayoutContainer getRightContainer() {
		if (rightContainer == null) {
			rightContainer = new LayoutContainer();
			rightContainer.setSize("114", "172");
			rightContainer.setId("rightContainer");
			rightContainer.setStyleName("sinotic-terminal-right");
			rightContainer.addStyleName("overflow-visible");
			rightContainer.setBorders(false);
			rightContainer.setLayout(new BorderLayout());

			BorderLayoutData bld_terminalHeaderContainer = new BorderLayoutData(LayoutRegion.CENTER, 35.0f);
			bld_terminalHeaderContainer.setMaxSize(35);
			bld_terminalHeaderContainer.setMinSize(35);

			rightContainer.add(getTerminalHeaderContainer(), bld_terminalHeaderContainer);
			rightContainer.add(getGridContainer(), new BorderLayoutData(LayoutRegion.SOUTH, 129.0f));
		}
		return rightContainer;
	}

	private LayoutContainer getInfo3Container() {
		if (info3Container == null) {
			info3Container = new LayoutContainer();
			info3Container.setId("info3");
			info3Container.setStyleName("blur-info");
			info3Container.setSize("18", "56");
			info3Container.add(getInfo3());
			info3Container.setLayout(new CenterLayout());
			info3Container.sinkEvents(Event.ONCLICK);
			info3Container.setBorders(false);
			info3Container.addListener(Events.OnClick, new Listener<BaseEvent>() {
				@Override
				public void handleEvent(BaseEvent be) {
					info3Container.setStyleName("focus-info");
					info2Container.setStyleName("blur-info");
					info1Container.setStyleName("blur-info");
				}
			});
		}
		return info3Container;
	}

	private LayoutContainer getInfo2Container() {
		if (info2Container == null) {
			info2Container = new LayoutContainer();
			info2Container.setId("info2");
			info2Container.setStyleName("blur-info");
			info2Container.setSize("18", "56");
			info2Container.setLayout(new CenterLayout());
			info2Container.add(getInfo2());
			info2Container.setBorders(false);
			info2Container.addListener(Events.OnClick, new Listener<BaseEvent>() {
				@Override
				public void handleEvent(BaseEvent be) {
					info2Container.setStyleName("focus-info");
					info3Container.setStyleName("blur-info");
					info1Container.setStyleName("blur-info");
				}
			});

		}
		return info2Container;
	}

	private LayoutContainer getInfo1Container() {
		if (info1Container == null) {
			info1Container = new LayoutContainer();
			info1Container.setId("info1");
			info1Container.setStyleName("focus-info");
			info1Container.setLayout(new CenterLayout());
			info1Container.setSize("24", "56");
			info1Container.add(getInfo1());
			info1Container.setBorders(false);
			info1Container.addListener(Events.OnClick, new Listener<BaseEvent>() {
				@Override
				public void handleEvent(BaseEvent be) {
					info1Container.setStyleName("focus-info");
					info2Container.setStyleName("blur-info");
					info3Container.setStyleName("blur-info");
				}
			});

		}
		return info1Container;
	}

	private LayoutContainer getTerminalHeaderContainer() {
		if (terminalHeaderContainer == null) {
			terminalHeaderContainer = new LayoutContainer();
			terminalHeaderContainer.setHeight("35");
			terminalHeaderContainer.setId("terminalHeaderContainer");
			terminalHeaderContainer.setStyleName("terminal-header");
			terminalHeaderContainer.addStyleName("overflow-visible");
			terminalHeaderContainer.setBorders(false);
			terminalHeaderContainer.setLayout(new RowLayout(Orientation.VERTICAL));
			terminalHeaderContainer.add(getCountContainer(), new RowData(Style.DEFAULT, 10.0, new Margins()));
			terminalHeaderContainer.add(getNameContainer(), new RowData(Style.DEFAULT, 25.0, new Margins()));
		}
		return terminalHeaderContainer;
	}

	private LayoutContainer getGridContainer() {
		if (gridContainer == null) {
			gridContainer = new LayoutContainer();
			gridContainer.setId("gridContainer");
			gridContainer.setBorders(false);
			gridContainer.setStyleName("terminal-grid");
			gridContainer.setLayout(new CenterLayout());
			gridContainer.add(getListView());
		}
		return gridContainer;
	}

	private ListView<DTOSimpleVehicle> getListView() {
		if (listView == null) {
			listView = new ListView<DTOSimpleVehicle>();
			listView.setStore(createListStore());
			listView.setTemplate(getTemplate());
			listView.setDisplayProperty(DTOSimpleVehicle.CODE);
			listView.setBorders(false);
			listView.setSize("-1", "-1");
		}
		return listView;
	}

	private ListStore<DTOSimpleVehicle> createListStore() {
		ListStore<DTOSimpleVehicle> listStore = new ListStore<DTOSimpleVehicle>();
		listStore.add(new DTOSimpleVehicle(0l, "12225"));
		listStore.add(new DTOSimpleVehicle(0l, "24480"));
		listStore.add(new DTOSimpleVehicle(0l, "14123"));
		listStore.add(new DTOSimpleVehicle(0l, "76889"));
		listStore.add(new DTOSimpleVehicle(0l, "14133"));
		return listStore;
	}

	private Html getName() {
		if (name == null) {
			name = new Html("C. Ceará");
			name.setId("name");
		}
		return name;
	}

	private LayoutContainer getCountContainer() {
		if (countContainer == null) {
			countContainer = new LayoutContainer();
			countContainer.setId("countContainer");
			countContainer.addStyleName("overflow-visible");
			countContainer.setBorders(false);
			countContainer.setLayout(new RowLayout(Orientation.HORIZONTAL));
			countContainer.add(getCount());
		}
		return countContainer;
	}

	private Html getCount() {
		if (count == null) {
			count = new Html("10");
			count.setStyleName("count-overflow");
		}
		return count;
	}

	private Html getInfo1() {
		if (info1 == null) {
			info1 = new Html("Info. 01 ");
			info1.setStyleName("vertical-info");
		}
		return info1;
	}

	private Html getInfo2() {
		if (info2 == null) {
			info2 = new Html("Info. 02 ");
			info2.setStyleName("vertical-info");
		}
		return info2;
	}

	private Html getInfo3() {
		if (info3 == null) {
			info3 = new Html("Info. 03 ");
			info3.setStyleName("vertical-info");
		}
		return info3;
	}

	private LayoutContainer getNameContainer() {
		if (nameContainer == null) {
			nameContainer = new LayoutContainer();
			nameContainer.setId("nameContainer");
			nameContainer.setBorders(false);
			nameContainer.setLayout(new CenterLayout());
			nameContainer.add(getName());
		}
		return nameContainer;
	}

	private String getTemplate() {
		StringBuffer template = new StringBuffer();
		template.append("<tpl for=\".\">");

		template.append("<div class=\"grid\"> <span> {" + DTOSimpleVehicle.CODE + "} </span> </div>");

		template.append("</tpl>");
		return template.toString();
	}
}
