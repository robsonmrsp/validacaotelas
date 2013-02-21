package br.com.m2msolutions.client.sinotic.container;

import br.com.m2msolutions.client.images.Images;
import br.com.m2msolutions.client.sinotic.canvas.SinoticCanvas;
import br.com.mr.dock.client.SinoticWindow;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.fx.Resizable;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;

public class SinoticContainer extends SinoticWindow {
	private LeftTerminalContainer terminalContainer;
	private LayoutContainer sinoticContainer;
	private RightTerminalContainer rightTerminalContainer;
	private Image image;
	private LayoutContainer sinoticHeader;
	private SinoticCanvas sinoticCanvas;
	private LayoutContainer sinoticFooter;
	private LayoutContainer headerContainer;
	private LayoutContainer footerContainer;

	public SinoticContainer() {
		initComponents();
	}

	private void initComponents() {
		setSize("992", "172");
		setStyleName("sinotic-container");
		setLayout(new BorderLayout());
		add(getTerminalContainer(), new BorderLayoutData(LayoutRegion.WEST, 135.0f));
		add(getSinoticContainer(), new BorderLayoutData(LayoutRegion.CENTER));
		add(getRightTerminalContainer(), new BorderLayoutData(LayoutRegion.EAST, 135.0f));

	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		new Resizable(this, "e w");
	}

	private LeftTerminalContainer getTerminalContainer() {
		if (terminalContainer == null) {
			terminalContainer = new LeftTerminalContainer();
		}
		return terminalContainer;
	}

	private LayoutContainer getSinoticContainer() {
		if (sinoticContainer == null) {
			sinoticContainer = new LayoutContainer();
			sinoticContainer.setSize("724", "172");
			sinoticContainer.setBorders(false);
			sinoticContainer.setLayout(new BorderLayout());
			getSinoticHeader().setLayout(new CenterLayout());
			BorderLayoutData bld_sinoticHeader = new BorderLayoutData(LayoutRegion.NORTH, 0.0f);
			bld_sinoticHeader.setMaxSize(0);
			bld_sinoticHeader.setMinSize(0);
			sinoticContainer.add(getSinoticHeader(), bld_sinoticHeader);
			getLayoutContainer_3().setLayout(new CenterLayout());
			BorderLayoutData bld_sinoticFooter = new BorderLayoutData(LayoutRegion.SOUTH, 0.0f);
			bld_sinoticFooter.setMinSize(0);
			bld_sinoticFooter.setMaxSize(0);
			sinoticContainer.add(getLayoutContainer_3(), bld_sinoticFooter);
			sinoticContainer.add(getSinoticCanvas(), new BorderLayoutData(LayoutRegion.CENTER));
		}
		return sinoticContainer;
	}

	@Override
	public Component getHeader() {
		return getSinoticHeader();
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
	
	/*private Canvas getCanvas(){
		
		SinoticCanvas sinoticoCanvas = new SinoticCanvas();
		return sinoticoCanvas.getCanvas();
		
	}*/

	private LayoutContainer getSinoticHeader() {
		if (sinoticHeader == null) {
			sinoticHeader = new LayoutContainer();
			sinoticHeader.setHeight("0");
			sinoticHeader.setId("sinoticHeader");
			sinoticHeader.setStyleName("sinotic-header");
			// sinoticHeader.setLayout(new AbsoluteLayout());
			sinoticHeader.setBorders(false);
			sinoticHeader.add(getHeaderContainer());
		}
		return sinoticHeader;
	}

	private LayoutContainer getSinoticCanvas() {
		if (sinoticCanvas == null) {
			sinoticCanvas = new SinoticCanvas();
			sinoticCanvas.setId("sinoticCanvas");
//			sinoticCanvas.setLayout(new CenterLayout());
//			sinoticCanvas.add(getImage());
//			sinoticCanvas.add(getCanvas());
			sinoticCanvas.setBorders(false);
		}
		return sinoticCanvas;
	}

	private LayoutContainer getLayoutContainer_3() {
		if (sinoticFooter == null) {
			sinoticFooter = new LayoutContainer();
			sinoticFooter.setLayout(new AbsoluteLayout());
			sinoticFooter.setId("sinoticFooter");
			sinoticFooter.setHeight("0");

			sinoticFooter.setBorders(false);
			sinoticFooter.add(getFooterContainer());
		}
		return sinoticFooter;
	}

	private LayoutContainer getHeaderContainer() {
		if (headerContainer == null) {
			headerContainer = new Header();
			headerContainer.setId("headerContainer");
			headerContainer.setSize(450, 30);
			headerContainer.setBorders(false);
		}
		return headerContainer;
	}

	private LayoutContainer getFooterContainer() {
		if (footerContainer == null) {
			footerContainer = new Footer();
			footerContainer.setSize("700px", "27px");

			footerContainer.setId("footerContainer");
			footerContainer.setBorders(false);
		}
		return footerContainer;
	}
}
