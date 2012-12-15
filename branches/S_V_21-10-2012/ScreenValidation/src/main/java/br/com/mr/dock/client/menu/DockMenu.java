package br.com.mr.dock.client.menu;

import java.util.ArrayList;
import java.util.List;

import br.com.mr.dock.client.containers.Position;
import br.com.mr.dock.client.layout.DockComponentLayout;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
public class DockMenu extends LayoutContainer {

	private int maxWidth = 40;
	private int itemWidth = 60;
	private int proximity = 80;
	private String horizontalAlign = "center";
	private Position position = Position.BOTTOM;
//	private int top;

	private List<DockItem> dockItems = new ArrayList<DockItem>();

	public DockMenu() {
		this(Position.BOTTOM);
	}

	public DockMenu(Position position) {
		setPosition(position);
		setWidth("300px");
		setStyleName("dock-container");
		prepareMouseHandlers();
		prepareAttachHandlers();
	}

	public void setPosition(Position pos) {
		position = pos;
		setLayout(new DockComponentLayout(pos));
	}

	@Override
	protected void onShow() {
		super.onShow();
		this.el().setElementAttribute("bottom", "0px");
		positionContainer(0);
	}

	public void anime(int pointX, int pointY) {
		int toAdd = 0;
		int posx = 0;
		int posy = 0;
		int containerX = this.getAbsoluteLeft();
		int containerY = this.getAbsoluteTop();

		int offsetWidth = this.getOffsetWidth();
		int offsetHeight = this.getOffsetHeight();
		if (horizontalAlign.equals("center"))
			posx = pointX - containerX - (offsetWidth - itemWidth * dockItems.size()) / 2 - itemWidth / 2;
		else if (horizontalAlign.equals("right"))
			posx = pointX - containerX - offsetWidth + itemWidth * dockItems.size();
		else
			posx = pointX - containerX;
		posy = (int) Math.pow(pointY - containerY - offsetHeight / 2, 2);

		int numItens = dockItems.size();
		for (int nr = 0; nr < numItens; nr++) {
			Image item = dockItems.get(nr).getImage();
			HTML html = dockItems.get(nr).getText();

			int distance = (int) Math.sqrt(Math.pow(posx - nr * itemWidth, 2) + posy);
			distance -= itemWidth / 2;
			distance = distance < 0 ? 0 : distance;
			distance = distance > proximity ? proximity : distance;
			distance = proximity - distance;

			int extraWidth = maxWidth * distance / proximity;

			String newSize = itemWidth + extraWidth + "px";

			item.setWidth(newSize);
			item.setHeight(newSize);

			int newLeft = itemWidth * nr + toAdd;
			item.getElement().getStyle().setLeft(newLeft, Unit.PX);

			html.getElement().getStyle().setLeft(newLeft + (itemWidth + extraWidth - html.getOffsetWidth()) / 2, Unit.PX);

			if (position.equals(Position.BOTTOM))
				html.getElement().getStyle().setMarginBottom(itemWidth + extraWidth, Unit.PX);
			else {
				html.getElement().getStyle().setMarginTop(itemWidth + extraWidth, Unit.PX);
			}
			toAdd += extraWidth;
		}
		positionContainer(toAdd);
	}

	private void positionContainer(int toAdd) {
		int left = this.el().getLeft();
		if (horizontalAlign != null)
			if (horizontalAlign.equals("center")) {
				int leftLocal = left + (this.getOffsetWidth() - itemWidth * dockItems.size()) / 2 - toAdd / 2;
				this.setStyleAttribute("left", leftLocal + "px");
			}
		this.setWidth((itemWidth) * dockItems.size() + toAdd);
	}

	public void addItem(String imageSrc, String text) {
		addItem(imageSrc, text, null);
	}

	public void addItem(String imageSrc, String text, DockSelectionAction action) {
		DockItem dockItem = new DockItem(imageSrc, text, action);
		addItem(dockItem);
	}

	public void addItem(DockItem dockItem) {
		dockItems.add(dockItem);
		add(dockItem.getImage());
		add(dockItem.getText());
	}

	public void setMaxWidth(int maxW) {
		maxWidth = maxW;
	}

	public void setItemWidth(int itemWidth) {
		this.itemWidth = itemWidth;
	}

	public void setProximity(int proximity) {
		this.proximity = proximity;
	}

	public void setAlign(Position botton) {
		if (botton.equals(Position.BOTTOM)) {

			Window.addResizeHandler(new ResizeHandler() {
				@Override
				public void onResize(ResizeEvent event) {
					int w = DockMenu.this.getWidth();
					DockMenu.this.getElement().getStyle().setLeft((event.getWidth() - w) / 2, Unit.PX);
				}
			});
		}
	}

	private void prepareMouseHandlers() {
		MouseMoveHandler handler = new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				anime(event.getClientX(), event.getClientY());
			}
		};

		RootPanel.get().sinkEvents(Event.MOUSEEVENTS);
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				RootPanel.get().setSize((event.getWidth() - 20) + "px", (event.getHeight() - 20) + "px");
			}
		});
		RootPanel.get().setSize((Window.getClientWidth() - 20) + "px", (Window.getClientHeight() - 20) + "px");

		RootPanel.get().addHandler(handler, MouseMoveEvent.getType());
	}

	private void prepareAttachHandlers() {

		addListener(Events.Attach, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				anime(0, 0);
			}
		});
	}
}
