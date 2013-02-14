package br.com.m2msolutions.client.sinotic.container;

import br.com.m2msolutions.client.images.Images;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.ui.Image;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;

public class Footer extends LayoutContainer {
	private LayoutContainer expandContainer;
	private LayoutContainer textContainer;
	private LayoutContainer alertContainer;
	private Html textHtml;
	private Image alertImage;
	private Image expandImage;

	public Footer() {
		initComponents();
	}

	private void initComponents() {
		setStyleName("sinotic-footer");
		setLayout(new BorderLayout());
		add(getExpandContainer(), new BorderLayoutData(LayoutRegion.EAST, 25.0f));
		getTextContainer().setLayout(new RowLayout(Orientation.HORIZONTAL));
		add(getTextContainer(), new BorderLayoutData(LayoutRegion.CENTER));
	}

	private LayoutContainer getExpandContainer() {
		if (expandContainer == null) {
			expandContainer = new LayoutContainer();
			expandContainer.setBorders(false);
			expandContainer.setLayout(new AbsoluteLayout());
			expandContainer.add(getExpandImage(), new AbsoluteData(0, 1));
		}
		return expandContainer;
	}

	private LayoutContainer getTextContainer() {
		if (textContainer == null) {
			textContainer = new LayoutContainer();
			textContainer.setBorders(false);
			textContainer.add(getAlertContainer());
			textContainer.add(getTextHtml(), new RowData(Style.DEFAULT, Style.DEFAULT, new Margins()));
		}
		return textContainer;
	}

	private LayoutContainer getAlertContainer() {
		if (alertContainer == null) {
			alertContainer = new LayoutContainer();
			alertContainer.setSize("27", "27");
			alertContainer.setBorders(false);
			alertContainer.setLayout(new AbsoluteLayout());
			alertContainer.add(getAlertImage(), new AbsoluteData(2, 2));
		}
		return alertContainer;
	}

	private Html getTextHtml() {
		if (textHtml == null) {
			textHtml = new Html("Linha 045 com sentido conjunto ceará com trechos lentos");
			textHtml.setStyleName("footer-text");
		}
		return textHtml;
	}

	private Image getAlertImage() {
		if (alertImage == null) {
			alertImage = new Image(Images.INSTANCE.imageLabel().getSafeUri());
			alertImage.setSize("24px", "24px");
		}
		return alertImage;
	}

	private Image getExpandImage() {
		if (expandImage == null) {
			expandImage = new Image(Images.INSTANCE.setaBaixo().getSafeUri());
			expandImage.setSize("25px", "25px");
		}
		return expandImage;
	}
}
