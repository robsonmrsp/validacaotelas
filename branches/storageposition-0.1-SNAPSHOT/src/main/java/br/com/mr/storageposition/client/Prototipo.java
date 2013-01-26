package br.com.mr.storageposition.client;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.SmallZoomControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class Prototipo extends SimplePanel {
	private FlexTable flexTable;
	private SimplePanel mapPanel;
	private FlexTable sidePanel;
	MapWidget mapWidget;
	private FocusPanel focusPanel_1;
	private FocusPanel sidePanelHeader;
	private FocusPanel focusPanel_3;
	private FocusPanel focusPanel_4;
	private FocusPanel focusPanel_5;
	private FocusPanel focusPanel_6;
	private FocusPanel focusPanel_7;
	private FocusPanel focusPanel_8;
	private FocusPanel focusPanel_9;
	private FocusPanel focusPanel_10;

	public Prototipo() {
		initComponents();
	}

	private void initComponents() {
		add(getFlexTable());
	}

	private FlexTable getFlexTable() {
		if (flexTable == null) {
			flexTable = new FlexTable();
			flexTable.setHeight("100%");
			flexTable.setWidget(0, 0, getMapPanel());
			flexTable.setWidget(0, 1, getSidePanel());
			flexTable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		}
		return flexTable;
	}

	private SimplePanel getMapPanel() {
		if (mapPanel == null) {
			mapPanel = new SimplePanel();
			mapPanel.add(getMapWidGet());
			mapPanel.setSize("100%", "100%");
		}
		return mapPanel;
	}

	private Widget getMapWidGet() {
		if (mapWidget == null) {
			mapWidget = new MapWidget(LatLng.newInstance(-17.2, -57.2), 4);
			mapWidget.setSize("100%", "100%");
			mapWidget.addControl(new SmallZoomControl());
			mapWidget.setSize(Window.getClientWidth() - 180 + "px", Window.getClientHeight() - 20 + "px");
			Window.addResizeHandler(new ResizeHandler() {
				@Override
				public void onResize(ResizeEvent event) {
					updateMapSize();
				}
			});
		}
		return mapWidget;
	}

	private void updateMapSize() {
		mapWidget.setSize(Window.getClientWidth() - 180 + "px", Window.getClientHeight() - 20 + "px");
	}

	private FlexTable getSidePanel() {
		if (sidePanel == null) {
			sidePanel = new FlexTable();
			sidePanel.setWidth("180px");
			sidePanel.setWidget(0, 0, getSidePanelHeader());
			sidePanel.setWidget(1, 0, getFocusPanel_3());
			sidePanel.setWidget(2, 0, getFocusPanel_1());
			sidePanel.setWidget(4, 0, getFocusPanel_4());
			sidePanel.setWidget(5, 0, getFocusPanel_5());
			sidePanel.setWidget(6, 0, getFocusPanel_6());
			sidePanel.setWidget(7, 0, getFocusPanel_7());
			sidePanel.setWidget(8, 0, getFocusPanel_8());
			sidePanel.setWidget(9, 0, getFocusPanel_9());
			sidePanel.setWidget(10, 0, getFocusPanel_10());
		}
		return sidePanel;
	}
	private FocusPanel getFocusPanel_1() {
		if (focusPanel_1 == null) {
			focusPanel_1 = new FocusPanel();
		}
		return focusPanel_1;
	}
	private FocusPanel getSidePanelHeader() {
		if (sidePanelHeader == null) {
			sidePanelHeader = new FocusPanel();
		}
		return sidePanelHeader;
	}
	private FocusPanel getFocusPanel_3() {
		if (focusPanel_3 == null) {
			focusPanel_3 = new SidePanelItem();
		}
		return focusPanel_3;
	}
	private FocusPanel getFocusPanel_4() {
		if (focusPanel_4 == null) {
			focusPanel_4 = new FocusPanel();
		}
		return focusPanel_4;
	}
	private FocusPanel getFocusPanel_5() {
		if (focusPanel_5 == null) {
			focusPanel_5 = new FocusPanel();
		}
		return focusPanel_5;
	}
	private FocusPanel getFocusPanel_6() {
		if (focusPanel_6 == null) {
			focusPanel_6 = new FocusPanel();
		}
		return focusPanel_6;
	}
	private FocusPanel getFocusPanel_7() {
		if (focusPanel_7 == null) {
			focusPanel_7 = new FocusPanel();
		}
		return focusPanel_7;
	}
	private FocusPanel getFocusPanel_8() {
		if (focusPanel_8 == null) {
			focusPanel_8 = new FocusPanel();
		}
		return focusPanel_8;
	}
	private FocusPanel getFocusPanel_9() {
		if (focusPanel_9 == null) {
			focusPanel_9 = new FocusPanel();
		}
		return focusPanel_9;
	}
	private FocusPanel getFocusPanel_10() {
		if (focusPanel_10 == null) {
			focusPanel_10 = new FocusPanel();
		}
		return focusPanel_10;
	}
}
