package br.com.m2msolutions.client.container;

import br.com.mr.dock.client.containers.Position;

import com.extjs.gxt.ui.client.Style.Direction;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.DragEvent;
import com.extjs.gxt.ui.client.event.DragListener;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.ui.Image;

public class SlidePanel extends ContentPanelImp {
	private Component anchor;
	private Window anchorMoveAndClose;
	private Position position;
	private LayoutContainer headerContainer;
	private LayoutContainer messagesContainer;
	private LayoutContainer editContainer;
	private SearchBox searchBox;
	private LayoutContainer layoutContainer;
	private LayoutContainer layoutContainer_1;
	private TextField txtfldNewTextfield;
	private Image image;

	public SlidePanel() {
		initComponents();
	}

	private void initComponents() {
		setHeaderVisible(false);
		setSize("266px", "413px");
		setLayout(new BorderLayout());
		getHeaderContainer().setLayout(new BorderLayout());
		add(getHeaderContainer(), new BorderLayoutData(LayoutRegion.NORTH, 26.0f));
		add(getMessagesContainer(), new BorderLayoutData(LayoutRegion.CENTER));
		getEditContainer().setLayout(new BorderLayout());
		add(getEditContainer(), new BorderLayoutData(LayoutRegion.SOUTH, 35.0f));
	}

	private void hide(boolean slide) {
		if (slide) {
			el().slideOut(Direction.LEFT, FxConfig.NONE);
		} else {
			super.hide();
		}
	}

	public void hide() {
		hide(true);
	}

	public void show() {
		super.show();

		setPosition(14 + anchor.getAbsoluteLeft() + anchor.getOffsetWidth(), anchor.getAbsoluteTop());
		if (!isAttached()) {
			RootPanel.get().add(this);
		}
		el().makePositionable(true);

		this.setStyleAttribute("opacity", "0.976543");
		this.el().dom.setAttribute("opacity", "0.976543");
		el().slideIn(Direction.RIGHT, FxConfig.NONE);
	}

	public void anchorAt(final ContentPanelImp anchor, Position position) {
		this.anchor = anchor;
		anchor.addListener(Events.Minimize, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
			}
		});

		anchor.addListener(Events.Close, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {

			}
		});
		anchor.addListener(Events.Resize, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {

			}
		});
		anchor.addListener(Events.Move, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (isVisible()) {
					setPosition(14 + anchor.getAbsoluteLeft() + anchor.getOffsetWidth(), anchor.getAbsoluteTop());
				}
			}
		});
		this.position = position;
	}

	private LayoutContainer getHeaderContainer() {
		if (headerContainer == null) {
			headerContainer = new LayoutContainer();
			headerContainer.setBorders(true);
			BorderLayoutData bld_searchBox = new BorderLayoutData(LayoutRegion.EAST, 150.0f);
			bld_searchBox.setMaxSize(100);
			headerContainer.add(getSearchBox(), bld_searchBox);
		}
		return headerContainer;
	}

	private LayoutContainer getMessagesContainer() {
		if (messagesContainer == null) {
			messagesContainer = new LayoutContainer();
			messagesContainer.setBorders(true);
		}
		return messagesContainer;
	}

	private LayoutContainer getEditContainer() {
		if (editContainer == null) {
			editContainer = new LayoutContainer();
			editContainer.setBorders(true);
			getLayoutContainer().setLayout(new CenterLayout());
			editContainer.add(getLayoutContainer(), new BorderLayoutData(LayoutRegion.WEST, 225.0f));
			editContainer.add(getLayoutContainer_1(), new BorderLayoutData(LayoutRegion.CENTER, 50.0f));
		}
		return editContainer;
	}

	private SearchBox getSearchBox() {
		if (searchBox == null) {
			searchBox = new SearchBox();
			searchBox.setSize("100", "24");
		}
		return searchBox;
	}

	public void setWindowAnchor(Widget parent) {

	}

	public Window getAnchorMoveAndClose() {
		return anchorMoveAndClose;
	}

	private void reposition() {
		setPosition(16 + anchor.getAbsoluteLeft() + anchor.getOffsetWidth(), anchor.getAbsoluteTop());
	}

	public void setAnchorMoveAndClose(Window anchorMoveAndClose) {
		this.anchorMoveAndClose = anchorMoveAndClose;
		anchorMoveAndClose.getDraggable().addDragListener(new DragListener() {
			@Override
			public void dragMove(DragEvent de) {
				super.dragMove(de);
				setPosition(16 + de.getComponent().getAbsoluteLeft() + de.getComponent().getOffsetWidth(), de.getComponent().getAbsoluteTop() + de.getComponent().getOffsetHeight() - SlidePanel.this.getHeight());
			}
		});

		anchorMoveAndClose.addListener(Events.Move, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				reposition();
			}
		});
		anchorMoveAndClose.addListener(Events.Resize, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				reposition();
			}

		});
		anchorMoveAndClose.addListener(Events.Hide, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				SlidePanel.this.hide(false);
			}
		});
	}
	private LayoutContainer getLayoutContainer() {
		if (layoutContainer == null) {
			layoutContainer = new LayoutContainer();
			layoutContainer.setBorders(true);
			layoutContainer.add(getTxtfldNewTextfield());
		}
		return layoutContainer;
	}
	private LayoutContainer getLayoutContainer_1() {
		if (layoutContainer_1 == null) {
			layoutContainer_1 = new LayoutContainer();
			layoutContainer_1.setSize("30", "30");
			layoutContainer_1.setBorders(true);
			layoutContainer_1.setLayout(new CenterLayout());
			layoutContainer_1.add(getImage());
		}
		return layoutContainer_1;
	}
	private TextField getTxtfldNewTextfield() {
		if (txtfldNewTextfield == null) {
			txtfldNewTextfield = new TextField();
			txtfldNewTextfield.setWidth("221px");
			txtfldNewTextfield.setFieldLabel("New TextField");
		}
		return txtfldNewTextfield;
	}
	private Image getImage() {
		if (image == null) {
			image = new Image("http://cdn1.iconfinder.com/data/icons/CrystalClear/22x22/actions/button_ok.png");
		}
		return image;
	}
}
