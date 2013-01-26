package br.com.mr.storageposition.client;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.SmallZoomControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class CopyOfPrototipo extends SplitLayoutPanel {
	private LayoutPanel layoutPanel;
	private FlexTable simplePanel;
	private SimplePanel simplePanel_1;
	private MapWidget mapWidget;
	private SidePanelItem sidePanelItem;
	private FlexTable flexTable;
	private FlowPanel flowPanel;
	private SidePanelItem itemConfig;

	public CopyOfPrototipo() {
		super(2);
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
			
			flexTable.setWidget(0, 0, getItemConfig());
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
			itemConfig.setLabel("Configurações");
		}
		return itemConfig;
	}
}
