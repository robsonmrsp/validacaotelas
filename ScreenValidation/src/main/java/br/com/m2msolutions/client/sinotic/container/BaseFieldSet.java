package br.com.m2msolutions.client.sinotic.container;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;

public class BaseFieldSet extends LayoutContainer {
	private LayoutContainer headerContainer;
	private LayoutContainer contentContainer;
	private Html legend;

	public BaseFieldSet() {
		initComponents();
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setStyleName("base-fieldset");
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		BorderLayoutData northBorderData = new BorderLayoutData(LayoutRegion.NORTH, 27.0f);
		northBorderData.setMinSize(27);
		northBorderData.setMaxSize(27);
		add(getHeaderContainer(), northBorderData);
		add(getContentContainer(), new BorderLayoutData(LayoutRegion.CENTER));
	}

	private LayoutContainer getHeaderContainer() {
		if (headerContainer == null) {
			headerContainer = new LayoutContainer();
			headerContainer.setHeight(27);
			headerContainer.setBorders(false);
			headerContainer.setLayout(new RowLayout(Orientation.HORIZONTAL));
			headerContainer.setId("headerContainer");
			headerContainer.setStyleName("header-fieldset");

			headerContainer.add(getLegend(), new RowData(445.0, 24, new Margins()));
		}
		return headerContainer;
	}

	private LayoutContainer getContentContainer() {
		if (contentContainer == null) {
			contentContainer = new LayoutContainer();
			contentContainer.setStyleName("base-fieldset-content");

			contentContainer.setBorders(false);
		}
		return contentContainer;
	}

	private Html getLegend() {
		if (legend == null) {
			legend = new Html("legend");
			legend.setSize("-1", "400");
			legend.setId("legend");
		}
		return legend;
	}

	public void setLegend(String legendValue) {
		legend.setHtml(legendValue);
	}

	public void setContent(Component component) {
		contentContainer.add(component);
	}
}
