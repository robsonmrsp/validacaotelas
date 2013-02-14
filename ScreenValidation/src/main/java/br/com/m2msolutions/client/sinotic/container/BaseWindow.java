package br.com.m2msolutions.client.sinotic.container;

import br.com.m2msolutions.client.images.Images;
import br.com.mr.dock.client.SinoticPanel;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.ResizeEvent;
import com.extjs.gxt.ui.client.event.ResizeListener;
import com.extjs.gxt.ui.client.fx.Resizable;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class BaseWindow extends SinoticPanel {

	private LayoutContainer headerContainer;
	private LayoutContainer principal;
	private LayoutContainer headingContainer;
	private LayoutContainer imageContainer;

	private LayoutContainer imageHeadingContainer;
	private Html heading;
	private LayoutContainer restoreContainer;
	private LayoutContainer closeContainer;
	private LayoutContainer bottonContainer;
	private Image closeImage;
	private Image restoreImage;
	private Image headerImage;

	private Resizable resizer;
	private LayoutContainer principalElement;

	public BaseWindow() {
		super();
		setId("baseWindow");
		setStyleName("base-window");
		setLayout(new BorderLayout());
		BorderLayoutData bld_headerContainer = new BorderLayoutData(LayoutRegion.NORTH, 32.0f);
		bld_headerContainer.setMinSize(32);
		add(getHeaderContainer(), bld_headerContainer);
		BorderLayoutData bld_principal = new BorderLayoutData(LayoutRegion.CENTER);
		bld_principal.setMinSize(250);
		bld_principal.setMaxSize(250);
		add(getPrincipal(), bld_principal);
		add(getBottonContainer(), new BorderLayoutData(LayoutRegion.SOUTH, 0.0f));
		hide();
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
	}

	public LayoutContainer getHeaderContainer() {
		if (headerContainer == null) {
			headerContainer = new LayoutContainer();
			headerContainer.setId("headerContainer");
			headerContainer.setSize("340", "32");
			headerContainer.setBorders(false);
			headerContainer.setStyleName("base-window-header");
			headerContainer.setLayout(new BorderLayout());
			headerContainer.add(getHeadingContainer(), new BorderLayoutData(LayoutRegion.WEST, 255.0f));
			headerContainer.add(getImageContainer(), new BorderLayoutData(LayoutRegion.EAST, 60.0f));
			headerContainer.setStyleAttribute("cursor", "move");
		}
		return headerContainer;
	}

	protected LayoutContainer getPrincipal() {
		if (principal == null) {
			principal = new LayoutContainer();
			principal.setId("principal");
			principal.setHeight(200);
			principal.setStyleName("base-window-content");
			principal.setBorders(false);
			principal.setLayout(new FitLayout());
		}
		return principal;
	}

	private LayoutContainer getHeadingContainer() {
		if (headingContainer == null) {
			headingContainer = new LayoutContainer();
			headingContainer.setBorders(false);
			headingContainer.setLayout(new RowLayout(Orientation.HORIZONTAL));
			headingContainer.add(getImageHeadingContainer());
			headingContainer.add(getHeading());
		}
		return headingContainer;
	}

	private LayoutContainer getImageContainer() {
		if (imageContainer == null) {
			imageContainer = new LayoutContainer();
			imageContainer.setBorders(false);
			imageContainer.setLayout(new RowLayout(Orientation.HORIZONTAL));
			imageContainer.add(getRestoreContainer());
			imageContainer.add(getCloseContainer());
			imageContainer.addListener(Events.OnMouseOver, new Listener<BaseEvent>() {
				public void handleEvent(BaseEvent be) {
					imageContainer.setStyleAttribute("cursor", "pointer");
				};
			});

			imageContainer.addListener(Events.OnMouseOut, new Listener<BaseEvent>() {
				public void handleEvent(BaseEvent be) {
					imageContainer.setStyleAttribute("cursor", "default");
				};
			});

		}
		return imageContainer;
	}

	@Override
	public void show() {
		super.show();

		setPosition(100, 20);
		if (!isAttached()) {
			RootPanel.get().add(this);
		}
		el().makePositionable(true);

		this.setStyleAttribute("opacity", "0.976543");
		this.el().dom.setAttribute("opacity", "0.976543");
	}

	private LayoutContainer getImageHeadingContainer() {
		if (imageHeadingContainer == null) {
			imageHeadingContainer = new LayoutContainer();
			imageHeadingContainer.setSize("28", "32");
			imageHeadingContainer.setBorders(false);
			imageHeadingContainer.setLayout(new CenterLayout());
			imageHeadingContainer.add(getHeaderImage());
		}
		return imageHeadingContainer;
	}

	private Html getHeading() {
		if (heading == null) {
			heading = new Html("10534");
			heading.setId("heading");
		}
		return heading;
	}

	private LayoutContainer getRestoreContainer() {
		if (restoreContainer == null) {
			restoreContainer = new LayoutContainer();
			restoreContainer.setSize("30", "32");
			restoreContainer.setBorders(false);
			restoreContainer.setLayout(new CenterLayout());
			restoreContainer.add(getRestoreImage());
		}
		return restoreContainer;
	}

	private LayoutContainer getCloseContainer() {
		if (closeContainer == null) {
			closeContainer = new LayoutContainer();
			closeContainer.setSize("30", "32");
			closeContainer.setBorders(false);
			closeContainer.setLayout(new CenterLayout());
			closeContainer.add(getCloseImage());
		}
		return closeContainer;
	}

	private LayoutContainer getBottonContainer() {
		if (bottonContainer == null) {
			bottonContainer = new LayoutContainer();
			bottonContainer.setBorders(false);
		}
		return bottonContainer;
	}

	@Override
	public Component getHeader() {
		return getHeaderContainer();
	}

	private Image getCloseImage() {
		if (closeImage == null) {
			closeImage = new Image(Images.INSTANCE.closeButton().getSafeUri());
		}
		return closeImage;
	}

	private Image getRestoreImage() {
		if (restoreImage == null) {
			restoreImage = new Image(Images.INSTANCE.restoreButton().getSafeUri());
		}
		return restoreImage;
	}

	private Image getHeaderImage() {
		if (headerImage == null) {
			headerImage = new Image(Images.INSTANCE.frontBus().getSafeUri());
		}
		return headerImage;
	}

	public void setResizable(boolean resizable) {
		if (resizable) {
			resizer = new Resizable(this);
			resizer.addResizeListener(createResizeListener());
		} else if (resizer != null) {
			resizer.release();
		}
	}

	private ResizeListener createResizeListener() {
		return new ResizeListener() {
			@Override
			public void handleEvent(ResizeEvent e) {
				super.handleEvent(e);
				principalElement.layout(true);
				principalElement.setWidth(e.getComponent().getOffsetWidth() - 2);
			}
		};
	}

	public void setBodyComponent(LayoutContainer component) {
		principalElement = component;
		principal.add(component);
		scrollIntoView(principal);
	}

}
