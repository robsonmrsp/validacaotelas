package br.com.mr.storageposition.client;

import br.com.mr.storageposition.client.images.Images;
import br.com.mr.storageposition.shared.UserInfoPosition;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.MenuMapTypeControl;
import com.google.gwt.maps.client.control.SmallZoomControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import de.novanic.eventservice.client.event.Event;
import de.novanic.eventservice.client.event.RemoteEventService;
import de.novanic.eventservice.client.event.RemoteEventServiceFactory;
import de.novanic.eventservice.client.event.domain.DomainFactory;
import de.novanic.eventservice.client.event.listener.RemoteEventListener;

public class MonitoringScreen extends SplitLayoutPanel {
	private MapWidget mapWidget;
	private FlexTable flexTable;
	private FlowPanel flowPanel;
	private SidePanelItem itemConfig;
	private SidePanelItem itemDrawRoute;
	private SidePanelItem itemFixSafePoint;
	private SidePanelItem itemAlowedLocation;
	private SidePanelItem itemStatistics;
	private SidePanelItem itemSendSoundSignal;
	private SidePanelItem itemSendSms;
	private SidePanelItem itemCircularPerimeter;
	private SidePanelItem itemSearch;
	private FlexTable grid;
	private Image image;
	private HTML htmlNewHtml;

	private MonitoringServiceAsync monitoringService = MonitoringService.Util.getInstance();

	private UserInfo currentUser;

	public MonitoringScreen() {
		super(5);
		initComponents();
	}

	private void initComponents() {
		addEast(getFlexTable(), 180.0);
		add(getFlowPanel());
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		updateMapSize();
	}

	private void updateMapSize() {
		setSize(Window.getClientWidth() + "px", Window.getClientHeight() - 0 + "px");
		mapWidget.setSize(Window.getClientWidth() - 180 + "px", Window.getClientHeight() - 0 + "px");
	}

	private Widget getMapWidget() {
		if (mapWidget == null) {
			mapWidget = new MapWidget(LatLng.newInstance(-17.2, -57.2), 4);
			mapWidget.setSize("521px", "330px");
			mapWidget.setScrollWheelZoomEnabled(true);
			mapWidget.addControl(new MenuMapTypeControl());
			// mapWidget.addControl(new MapTypeControl());
			mapWidget.addControl(new SmallZoomControl());
			Window.addResizeHandler(new ResizeHandler() {
				@Override
				public void onResize(ResizeEvent event) {
					updateMapSize();
				}
			});
		}
		return mapWidget;
	}

	private FlexTable getFlexTable() {
		if (flexTable == null) {
			flexTable = new FlexTable();
			flexTable.setSize("100%", "180");
			flexTable.setWidget(0, 0, getGrid());

			flexTable.setWidget(1, 0, getItemConfig());
			flexTable.setWidget(2, 0, getItemDrawRoute());
			flexTable.setWidget(3, 0, getItemFixSafePoint());
			flexTable.setWidget(4, 0, getItemAlowedLocation());
			flexTable.setWidget(5, 0, getItemStatistics());
			flexTable.setWidget(6, 0, getItemSearch());
			flexTable.setWidget(7, 0, getItemSendSoundSignal());
			flexTable.setWidget(8, 0, getItemSendSms());
			flexTable.setWidget(9, 0, getItemCircularPerimeter());
		}
		return flexTable;
	}

	private FlowPanel getFlowPanel() {
		if (flowPanel == null) {
			flowPanel = new FlowPanel();
			flowPanel.add(getMapWidget());
		}
		return flowPanel;
	}

	private SidePanelItem getItemConfig() {
		if (itemConfig == null) {
			itemConfig = new SidePanelItem();
			itemConfig.setSize("100%", "100%");
			itemConfig.setLabel("Configurações");
		}
		return itemConfig;
	}

	private SidePanelItem getItemDrawRoute() {
		if (itemDrawRoute == null) {
			itemDrawRoute = new SidePanelItem();
			itemDrawRoute.setSize("100%", "100%");
		}
		return itemDrawRoute;
	}

	private SidePanelItem getItemFixSafePoint() {
		if (itemFixSafePoint == null) {
			itemFixSafePoint = new SidePanelItem();
			itemFixSafePoint.setSize("100%", "100%");
		}
		return itemFixSafePoint;
	}

	private SidePanelItem getItemAlowedLocation() {
		if (itemAlowedLocation == null) {
			itemAlowedLocation = new SidePanelItem();
			itemAlowedLocation.setSize("100%", "100%");
		}
		return itemAlowedLocation;
	}

	private SidePanelItem getItemStatistics() {
		if (itemStatistics == null) {
			itemStatistics = new SidePanelItem();
			itemStatistics.setSize("100%", "100%");
		}
		return itemStatistics;
	}

	private SidePanelItem getItemSendSoundSignal() {
		if (itemSendSoundSignal == null) {
			itemSendSoundSignal = new SidePanelItem();
			itemSendSoundSignal.setSize("100%", "100%");
		}
		return itemSendSoundSignal;
	}

	private SidePanelItem getItemSendSms() {
		if (itemSendSms == null) {
			itemSendSms = new SidePanelItem();
			itemSendSms.setSize("100%", "100%");
		}
		return itemSendSms;
	}

	private SidePanelItem getItemCircularPerimeter() {
		if (itemCircularPerimeter == null) {
			itemCircularPerimeter = new SidePanelItem();
			itemCircularPerimeter.setSize("100%", "100%");
		}
		return itemCircularPerimeter;
	}

	private SidePanelItem getItemSearch() {
		if (itemSearch == null) {
			itemSearch = new SidePanelItem();
			itemSearch.setSize("100%", "100%");
		}
		return itemSearch;
	}

	private FlexTable getGrid() {
		if (grid == null) {
			grid = new FlexTable();
			grid.setStyleName("sidepanel-header");
			grid.setSize("100%", "100%");
			grid.setWidget(0, 0, getImage());
			grid.setWidget(0, 1, getHtmlNewHtml());
			grid.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
			grid.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
			grid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
			grid.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		}
		return grid;
	}

	private Image getImage() {
		if (image == null) {
			image = new Image(Images.INSTANCE.findme());
			image.setSize("42", "42");
		}
		return image;
	}

	private HTML getHtmlNewHtml() {
		if (htmlNewHtml == null) {
			htmlNewHtml = new HTML("Find me", true);
		}
		return htmlNewHtml;
	}

	public void startMonitoringScreen(UserInfo user) {
		this.currentUser = user;
		show();
		startService(user);
	}

	private void startService(UserInfo user) {
		monitoringService.startServices(user, new AsyncCallback<UserInfoPosition>() {

			@Override
			public void onSuccess(UserInfoPosition userInfoPosition) {
				if (userInfoPosition != null) {
					plotting(userInfoPosition);
				}
			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});

	}

	protected void plotting(UserInfoPosition userInfoPosition) {
		Marker marker = createPosition(userInfoPosition);
		mapWidget.addOverlay(marker);
		mapWidget.setCenter(marker.getLatLng(), 12);
		mapWidget.checkResizeAndCenter();
	}

	private Marker createPosition(UserInfoPosition userInfoPosition) {

		LatLng newInstance = LatLng.newInstance(userInfoPosition.getLatitude(), userInfoPosition.getLongitude());
		MarkerOptions options = MarkerOptions.newInstance();
		Icon icon = Icon.newInstance(userInfoPosition.getImageSrc());
		// TODO correção da posição da imagem
		// icon.setIconAnchor(anchor)
		options.setIcon(icon);
		options.setTitle(userInfoPosition.getUserName());

		Marker m = new Marker(newInstance, options);

		return m;
	}

	public void show() {
		RootPanel rootPanel = RootPanel.get();
		if (rootPanel.getWidgetIndex(this) < 0) {
			RootPanel.get().add(this);
		}
	}

	public void initRemoteService() {
		RemoteEventService theRemoteEventService = RemoteEventServiceFactory.getInstance().getRemoteEventService();
		// add a listener to the SERVER_MESSAGE_DOMAIN
		theRemoteEventService.addListener(DomainFactory.getDomain("DOMAIN_POSITION_EVENT"), new RemoteEventListener() {
			public void apply(Event anEvent) {
				if (anEvent instanceof UserInfoPositionEvent) {
					UserInfoPositionEvent infoPositionEvent = (UserInfoPositionEvent) anEvent;
					UserInfoPosition userInfoPosition = infoPositionEvent.getUserInfoPosition();
					plotting(userInfoPosition);
				}
			}
		});
	}
}
