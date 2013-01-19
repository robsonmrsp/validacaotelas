package br.com.mr.storageposition.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.maps.client.event.MapRightClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;

public class Principal extends Composite {
	private MapPanel mapPanel;

	final private PopupPanel popupPanel = new PopupPanel(true);

	final private PopupPanel popupBusServicesPanel = new PopupPanel(true);
	HTML html = new HTML();

	public Principal() {
		initComponents();
//		createPopupsMenu();
	}

	private void initComponents() {
		initWidget(getMapPanel());
	}

	private FlowPanel getMapPanel() {
		if (mapPanel == null) {
			mapPanel = new MapPanel();
			mapPanel.addMapRightClickHandler(new MapRightClickHandler() {
				@Override
				public void onRightClick(MapRightClickEvent event) {
					showMenu(event.getPoint());
				}
			});
		}
		return mapPanel;
	}

	protected void showBusServicesMenu(Point point) {
		popupBusServicesPanel.setPopupPosition(point.getX(), point.getY());
		popupBusServicesPanel.show();
	}

	protected void showMenu(Point point) {
		if (popupMenuBar != null) {
			popupPanel.setPopupPosition(point.getX(), point.getY());
			popupPanel.show();
		}
	}

	Command showBusStopCommand = new Command() {
		public void execute() {
			try {
				// TODO
			} catch (Exception e) {
				e.printStackTrace();
			}
			popupPanel.hide();
		}
	};
	Command showPharmaCommand = new Command() {
		public void execute() {

			popupPanel.hide();
		}
	};
	Command showMotelCommand = new Command() {
		public void execute() {

			popupPanel.hide();
		}
	};

	public void setContextMenu(MenuBar popupMenuBar) {
		this.popupMenuBar = popupMenuBar;
		popupPanel.setStyleName("popup");
		popupMenuBar.setVisible(true);
		popupPanel.add(popupMenuBar);
	}

	MenuBar popupMenuBar;

	private void createPopupsMenu() {
		MenuBar popupMenuBar = new MenuBar(true);

		MenuItem alertItem = new MenuItem("Pontos de onibus", true, showBusStopCommand);
		MenuItem imageItem = new MenuItem("Farmácias ", true, showPharmaCommand);
		MenuItem sponserItem = new MenuItem("Moteis ", true, showMotelCommand);

		popupPanel.setStyleName("popup");
		popupBusServicesPanel.setStyleName("popup");

		alertItem.addStyleName("popup-item");
		imageItem.addStyleName("popup-item");
		sponserItem.addStyleName("popup-item");

		popupMenuBar.addItem(alertItem);
		popupMenuBar.addItem(imageItem);
		popupMenuBar.addItem(sponserItem);

		popupMenuBar.setVisible(true);
		popupPanel.add(popupMenuBar);
		popupBusServicesPanel.add(html);
	}

}
