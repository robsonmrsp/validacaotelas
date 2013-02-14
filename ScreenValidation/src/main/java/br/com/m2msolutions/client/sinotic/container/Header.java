package br.com.m2msolutions.client.sinotic.container;

import br.com.m2msolutions.client.images.Images;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.ui.Image;

public class Header extends LayoutContainer {
	private LayoutContainer leftContainer;
	private LayoutContainer closeContainer;
	private LayoutContainer layoutContainer_2;
	private Html htmlName;
	private Image image;

	public Header() {
		initComponents();
	}

	private void initComponents() {
		setSize("500", "30px");
		setLayout(new BorderLayout());
		add(getLeftContainer(), new BorderLayoutData(LayoutRegion.WEST, 30.0f));
		getCloseContainer().setLayout(new AbsoluteLayout());
		add(getCloseContainer(), new BorderLayoutData(LayoutRegion.EAST, 35.0f));
		getLayoutContainer_2().setLayout(new CenterLayout());
		add(getLayoutContainer_2(), new BorderLayoutData(LayoutRegion.CENTER));
	}

	private LayoutContainer getLeftContainer() {
		if (leftContainer == null) {
			leftContainer = new LayoutContainer();
			leftContainer.setBorders(false);
		}
		return leftContainer;
	}

	private LayoutContainer getCloseContainer() {
		if (closeContainer == null) {
			closeContainer = new LayoutContainer();
			closeContainer.setBorders(false);
			closeContainer.add(getImage(), new AbsoluteData(25, -15));
		}
		return closeContainer;
	}

	private LayoutContainer getLayoutContainer_2() {
		if (layoutContainer_2 == null) {
			layoutContainer_2 = new LayoutContainer();
			layoutContainer_2.setHeight("30");
			layoutContainer_2.setBorders(false);
			layoutContainer_2.add(getHtmlName());
		}
		return layoutContainer_2;
	}

	private Html getHtmlName() {
		if (htmlName == null) {
			htmlName = new Html("<b>045</b> - Conjunto ceará / Papicú/Montese");
		}
		return htmlName;
	}

	private Image getImage() {
		if (image == null) {
			image = new Image(Images.INSTANCE.closeSinotic().getSafeUri());
			
		}
		return image;
	}
}
