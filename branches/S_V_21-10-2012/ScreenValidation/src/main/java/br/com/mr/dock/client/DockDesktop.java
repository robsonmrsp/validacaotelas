package br.com.mr.dock.client;

import br.com.mr.dock.client.containers.Position;
import br.com.mr.dock.client.menu.DockMenu;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.core.DomQuery;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.fx.Draggable;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;

public class DockDesktop extends LayoutContainer {
	private LayoutContainer dockTopContainer;
	private LayoutContainer principalContainer;
	private LayoutContainer dockBottomContainer;
	private DockMenu bottomDock;
	private DockMenu topDock;

	public DockDesktop() {
		initComponents();
	}

	@Override
	protected void onResize(int width, int height) {
		super.onResize(width, height);
		setSize(width, height);
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		setStyleName("dock-desktop");
		addListener(Events.Attach, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				setSize(Window.getClientWidth(), Window.getClientHeight());
			}
		});
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				setSize(event.getWidth(), event.getHeight());
			}
		});

		add(getPrincipalContainer(), new BorderLayoutData(LayoutRegion.CENTER));
		add(getDockTopContainer(), new BorderLayoutData(LayoutRegion.NORTH, 50));
		add(getDockBottomContainer(), new BorderLayoutData(LayoutRegion.SOUTH, 50));

		// RootPanel.get().add(this);
	}

	private LayoutContainer getPrincipalContainer() {
		if (principalContainer == null) {
			principalContainer = new LayoutContainer() {
				@Override
				protected void onRender(Element parent, int index) {
					super.onRender(parent, index);
					Element el = DomQuery.selectNode("#dock-desktop");
					if (el == null) {
						el = DOM.createDiv();
					}
					getElement().appendChild(el);
				}
			};

			principalContainer.setBorders(false);
		}
		return principalContainer;
	}

	private LayoutContainer getDockTopContainer() {
		if (dockTopContainer == null) {
			dockTopContainer = new LayoutContainer();
			dockTopContainer.setId("dockTopContainer");
			dockTopContainer.setLayout(new CenterLayout());
			dockTopContainer.add(getTopDock());
			dockTopContainer.setBorders(false);
		}
		return dockTopContainer;
	}

	private LayoutContainer getDockBottomContainer() {
		if (dockBottomContainer == null) {
			dockBottomContainer = new LayoutContainer();
			dockBottomContainer.setId("dockBottomContainer");
			dockBottomContainer.setBorders(false);
			dockBottomContainer.setLayout(new CenterLayout());
			dockBottomContainer.add(getBottomDock());
		}
		return dockBottomContainer;
	}

	public DockMenu getBottomDock() {
		if (bottomDock == null) {
			bottomDock = new DockMenu(Position.BOTTOM);
			bottomDock.setId("bottomDock");
			bottomDock.setProximity(80);
			bottomDock.setMaxWidth(60);
			bottomDock.setItemWidth(40);
		}
		return bottomDock;
	}

	public DockMenu getTopDock() {
		if (topDock == null) {
			topDock = new DockMenu(Position.TOP);

			topDock.setProximity(80);
			topDock.setMaxWidth(60);
		}
		return topDock;
	}

	public void addWindow(DockWindow window) {
		Draggable dragger = new Draggable(window, window.getHeader());
		dragger.setContainer(this);  
		dragger.setUseProxy(false);		
		principalContainer.add(window);
	}
}
