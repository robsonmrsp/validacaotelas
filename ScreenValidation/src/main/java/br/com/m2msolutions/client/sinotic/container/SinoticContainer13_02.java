package br.com.m2msolutions.client.sinotic.container;

import br.com.m2msolutions.client.images.Images;
import br.com.mr.dock.client.SinoticWindow;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.ui.Image;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class SinoticContainer13_02 extends SinoticWindow {
	private LeftTerminalContainer terminalContainer;
	private LayoutContainer layoutContainer_1;
	private RightTerminalContainer rightTerminalContainer;
	private Image image;

	public SinoticContainer13_02() {
		initComponents();
	}

	private void initComponents() {
		setSize("992", "172");
		setStyleName("sinotic-container");
		setLayout(new BorderLayout());
		add(getTerminalContainer(), new BorderLayoutData(LayoutRegion.WEST, 135.0f));
		getLayoutContainer_1().setLayout(new FitLayout());
		add(getLayoutContainer_1(), new BorderLayoutData(LayoutRegion.CENTER));
		add(getRightTerminalContainer(), new BorderLayoutData(LayoutRegion.EAST, 135.0f));
	}

	private LeftTerminalContainer getTerminalContainer() {
		if (terminalContainer == null) {
			terminalContainer = new LeftTerminalContainer();
		}
		return terminalContainer;
	}

	private LayoutContainer getLayoutContainer_1() {
		if (layoutContainer_1 == null) {
			layoutContainer_1 = new LayoutContainer();
			layoutContainer_1.setSize("724", "172");
			layoutContainer_1.setBorders(false);
			layoutContainer_1.add(getImage());
		}
		return layoutContainer_1;
	}

	@Override
	public Component getHeader() {
		return this;
	}
	private RightTerminalContainer getRightTerminalContainer() {
		if (rightTerminalContainer == null) {
			rightTerminalContainer = new RightTerminalContainer();
		}
		return rightTerminalContainer;
	}
	private Image getImage() {
		if (image == null) {
			image = new Image(Images.INSTANCE.canvas().getSafeUri());
			image.setSize("725px", "172px");
		}
		return image;
	}
}
