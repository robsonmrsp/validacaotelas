package br.com.mr.dock.client;

import br.com.mr.dock.client.containers.Position;
import br.com.mr.dock.client.menu.DockMenu;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
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
	}

	private LayoutContainer getPrincipalContainer() {
		if (principalContainer == null) {

			principalContainer = new LayoutContainer(); 
			principalContainer.setId("principalContainer");
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

//			bottomDock.addItem("images/portfolio.png", "Portfolio");
//			bottomDock.addItem("images/music.png", "Musicas");
//
//			bottomDock.addItem("images/video.png", "Videos");
//			bottomDock.addItem("images/history.png", "Historico");
//			bottomDock.addItem("images/calendar.png", "Calendario");
//			bottomDock.addItem("images/link.png", "Links");
//			bottomDock.addItem("images/rss.png", "Rss");
//			bottomDock.addItem("images/rss2.png", "Rss novos");

		}
		return bottomDock;
	}

	public DockMenu getTopDock() {
		if (topDock == null) {
			topDock = new DockMenu(Position.TOP);

			topDock.setProximity(80);
			topDock.setMaxWidth(60);
			topDock.setItemWidth(40);

//			topDock.addItem("images/portfolio.png", "Portfolio", new DockSelectionAction() {
//				@Override
//				public void action() {
//					Window.alert("portifolio");
//				}
//			});
//			topDock.addItem("images/music.png", "Musicas");
//
//			topDock.addItem("images/video.png", "Videos");
//			topDock.addItem("images/history.png", "Historico");
//			topDock.addItem("images/calendar.png", "Calendario");
//			topDock.addItem("images/link.png", "Links");
//			topDock.addItem("images/rss.png", "Rss");
//			topDock.addItem("images/rss2.png", "Rss novos");
		}
		return topDock;
	}
}
