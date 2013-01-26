package br.com.mr.storageposition.client;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class SidePanelItem extends FocusPanel {
	private FlexTable flexTable;
	private Image icon;
	private HTML label;

	public SidePanelItem() {
		initComponents();
	}

	private void initComponents() {
		setWidget(getFlexTable());
		this.addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				getElement().getStyle().setCursor(Cursor.AUTO);
			}
		});
		this.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				getElement().getStyle().setCursor(Cursor.POINTER);
			}
		});
	}

	private FlexTable getFlexTable() {
		if (flexTable == null) {
			flexTable = new FlexTable();
			flexTable.setSize("100%", "100%");
			flexTable.setWidget(0, 0, getIcon());
			flexTable.getCellFormatter().setHeight(0, 0, "32");
			flexTable.getCellFormatter().setWidth(0, 0, "52");
			flexTable.setWidget(0, 1, getLabel());
			flexTable.getCellFormatter().setHeight(0, 1, "");
			flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
			flexTable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
			flexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
			flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		}
		return flexTable;
	}

	private Image getIcon() {
		if (icon == null) {
			icon = new Image("http://cdn1.iconfinder.com/data/icons/fatcow/32/cog.png");
		}
		return icon;
	}

	private HTML getLabel() {
		if (label == null) {
			label = new HTML("Configurações", true);
		}
		return label;
	}

	public void setIcon(String url) {
		icon.setUrl(url);
	}

	public void setLabel(String text) {
		label.setText(text);
	}
}
