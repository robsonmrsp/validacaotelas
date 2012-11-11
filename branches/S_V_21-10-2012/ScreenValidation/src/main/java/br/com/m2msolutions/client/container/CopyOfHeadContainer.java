package br.com.m2msolutions.client.container;

import br.com.m2msolutions.client.images.Images;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Direction;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FxEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CopyOfHeadContainer extends LayoutContainer {

	private Html heading;
	private LayoutContainer header;
	private LayoutContainer principal;
	private Image expandButton;
	static boolean disableImage = false;
	private LayoutContainer headingContainer;
	private LayoutContainer imageContainer;
	private ButtonBar buttonBar;
	private FillToolItem fillToolItem;
	private Button btnNewButton;

	public CopyOfHeadContainer() {
		super();
		setSize(360, Style.DEFAULT);
		setId("AreaInspector");
		setStyleName("widget-window");
		setLayout(new RowLayout(Orientation.VERTICAL));
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		add(getHeader(), new RowData(1, 22.0, new Margins()));
		add(getPrincipal(), new RowData(Style.DEFAULT, Style.DEFAULT, new Margins()));
	}

	public LayoutContainer getHeader() {
		if (header == null) {
			header = createHeader();
		}
		return header;
	}

	protected LayoutContainer createHeader() {
		LayoutContainer header = new LayoutContainer();
		header.setBorders(false);
		header.setStyleName("widget-window-heading");
		header.setLayout(new BorderLayout());
		header.add(getHeadingContainer(), new BorderLayoutData(LayoutRegion.CENTER));
		header.add(getImageContainer(), new BorderLayoutData(LayoutRegion.EAST, 20.0F));
		header.setStyleAttribute("cursor", "move");
		return header;
	}

	protected void addWidget(Widget component) {
		principal.add(component);
	}

	protected LayoutContainer getPrincipal() {
		if (principal == null) {
			principal = createPrincipal();
		}
		return principal;
	}

	protected LayoutContainer createPrincipal() {
		LayoutContainer principal = new LayoutContainer();
		principal.setId("principal");
		principal.setLayout(new RowLayout(Orientation.VERTICAL));
		principal.setBorders(false);
		return principal;
	}

	private Image getExpandButton() {
		final FxConfig config = createFxConfig();

		if (expandButton == null) {
			expandButton = new Image(getCollapseImageUrl());
			expandButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (disableImage)
						return;
					if (principal.isVisible()) {
						principal.el().slideOut(Direction.UP, config);
						expandButton.setUrl(getExpandImageUrl());
					} else {
						principal.el().slideIn(Direction.DOWN, config);
						expandButton.setUrl(getCollapseImageUrl());
					}
				}
			});
		}
		return expandButton;
	}

	protected String getCollapseImageUrl() {
		return Images.INSTANCE.contract16().getURL();
	}

	protected String getExpandImageUrl() {
		return Images.INSTANCE.expand16().getURL();
	}

	private FxConfig createFxConfig() {
		final FxConfig config = new FxConfig();
		config.setEffectStartListener(new Listener<FxEvent>() {
			@Override
			public void handleEvent(FxEvent be) {
				disableImage = true;
			}
		});

		config.setEffectCompleteListener(new Listener<FxEvent>() {
			@Override
			public void handleEvent(FxEvent be) {
				disableImage = false;
			}
		});
		return config;
	}

	public void setHeading(String linhas) {
		getHeading().setHtml(linhas);
	}

	private LayoutContainer getHeadingContainer() {
		if (headingContainer == null) {
			headingContainer = new LayoutContainer();

			headingContainer.setBorders(false);
			headingContainer.setLayout(new BorderLayout());
			headingContainer.add(getHeading(), new BorderLayoutData(LayoutRegion.CENTER));
			headingContainer.add(getButtonBar(), new BorderLayoutData(LayoutRegion.EAST, 160.0f));
		}
		return headingContainer;
	}

	private LayoutContainer getImageContainer() {
		if (imageContainer == null) {
			imageContainer = new LayoutContainer();
			imageContainer.setLayout(new AbsoluteLayout());
			imageContainer.setSize("20", "20");
			imageContainer.setBorders(false);
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

			imageContainer.add(getExpandButton(), new AbsoluteData(5, 2));
		}
		return imageContainer;
	}

	private Html getHeading() {
		if (heading == null) {
			heading = new Html();
			heading.setHtml("Cadastro:");
			heading.setHeight("18");
		}
		return heading;
	}

	@Override
	public void show() {
		super.show();
		this.setStyleAttribute("opacity", "0.976543");
		this.el().dom.setAttribute("opacity", "0.976543");
	}

	@Override
	public void setHeight(int height) {
		if (height < 230)
			return;
		super.setHeight(height);
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
	}

	public void addToolBox(ImageResource imageResource, SelectionListener clickListener) {
//		getToolBar().add(createToolboxButton(AbstractImagePrototype.create(imageResource), clickListener));
	}

	private Component createToolboxButton(AbstractImagePrototype icon, SelectionListener clickListener) {
		Button button = new Button();
		button.setIcon(icon);
		if (clickListener != null)
			button.addSelectionListener(clickListener);
		button.setSize("20", "20");
		return button;
	}

	@Override
	protected boolean add(Component item) {
		return getPrincipal().add(item);
	}
	private ButtonBar getButtonBar() {
		if (buttonBar == null) {
			buttonBar = new ButtonBar();
			buttonBar.add(getFillToolItem());
			buttonBar.add(getBtnNewButton());
		}
		return buttonBar;
	}
	private FillToolItem getFillToolItem() {
		if (fillToolItem == null) {
			fillToolItem = new FillToolItem();
		}
		return fillToolItem;
	}
	private Button getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new Button("New Button");
			btnNewButton.setSize("20", "20");
		}
		return btnNewButton;
	}
}
