package br.com.m2msolutions.client.container;

import java.util.ArrayList;
import java.util.List;

import br.com.mr.dock.client.containers.Position;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Direction;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.DragEvent;
import com.extjs.gxt.ui.client.event.DragListener;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class SlidePanel extends ContentPanelImp {
	
	private static final EventType OnSelectedMessage = new EventType();
	private static final EventType OnRemoveMessage = new EventType();
	private static final EventType OnSaveNewMessage = new EventType();
	private static final EventType OnEditMessage = new EventType();
	
	private Component anchor;
	private Window anchorMoveAndClose;
	private Position position;
	private LayoutContainer headerContainer;
	private LayoutContainer messagesContainer;
	private LayoutContainer editContainer;
	private SearchBox searchBox;
	private LayoutContainer layoutContainer;
	private LayoutContainer layoutContainer_1;
	private TextField<String> newMessage;
	private Image saveImage;
	private Grid<DtoPredefinedMessage> gridPredefinedMessage;

	private DtoPredefinedMessage selectedMessage;

	public SlidePanel() {
		initComponents();
	}

	private void initComponents() {
		setHeaderVisible(false);
		setSize("266px", "413px");
		setLayout(new BorderLayout());
		getHeaderContainer().setLayout(new BorderLayout());
		add(getHeaderContainer(), new BorderLayoutData(LayoutRegion.NORTH, 26.0f));
		getMessagesContainer().setLayout(new FitLayout());
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
			BorderLayoutData bld_searchBox = new BorderLayoutData(LayoutRegion.EAST, 150.0f);
			bld_searchBox.setMaxSize(100);
			headerContainer.add(getSearchBox(), bld_searchBox);
		}
		return headerContainer;
	}

	private LayoutContainer getMessagesContainer() {
		if (messagesContainer == null) {
			messagesContainer = new LayoutContainer();
			messagesContainer.add(getGridPredefinedMessages());

		}
		return messagesContainer;
	}

	private Grid<DtoPredefinedMessage> getGridPredefinedMessages() {
		if (gridPredefinedMessage == null) {
			gridPredefinedMessage = new Grid<DtoPredefinedMessage>(createListStorePredefinedMessage(), createColumnConfig());
//			gridPredefinedMessage.setSize("-1", "290");
			gridPredefinedMessage.setSelectionModel(new GridSelectionModel<DtoPredefinedMessage>());
			gridPredefinedMessage.setBorders(false);
			gridPredefinedMessage.setStripeRows(true);
			gridPredefinedMessage.setId("gridPredefinedMessages");
			gridPredefinedMessage.setAutoWidth(false);
			gridPredefinedMessage.setColumnResize(false);
			gridPredefinedMessage.setHideHeaders(true);
			gridPredefinedMessage.setHeight(Style.DEFAULT);
			gridPredefinedMessage.setAutoExpandColumn(DtoPredefinedMessage.TEXT);
			gridPredefinedMessage.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<DtoPredefinedMessage>() {
				@Override
				public void selectionChanged(SelectionChangedEvent<DtoPredefinedMessage> se) {
					DtoPredefinedMessage selectedItem = se.getSelectedItem();
					if (selectedItem != null) {
						fireEvent(OnSelectedMessage, new SlidePanelEvent(SlidePanel.this, selectedItem));
					}
				}
			});
		}
		return gridPredefinedMessage;
	}

	private ListStore<DtoPredefinedMessage> createListStorePredefinedMessage() {

		ListStore<DtoPredefinedMessage> listStore = new ListStore<DtoPredefinedMessage>();

		listStore.add(new DtoPredefinedMessage("Acionada policia (190)"));
		listStore.add(new DtoPredefinedMessage("Despachado embarque!"));

		return listStore;
	}

	private ColumnModel createColumnConfig() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig columnApply = new ColumnConfig();
		columnApply.setId("applyMessage");
		columnApply.setWidth(35);
		columnApply.setRenderer(createApplyImageCellRender());
		configs.add(columnApply);

		ColumnConfig columnMessage = new ColumnConfig();
		columnMessage.setId("text");
		configs.add(columnMessage);

		ColumnConfig columnEdit = new ColumnConfig();
		columnEdit.setId("editMessage");
		columnEdit.setWidth(35);
		columnEdit.setRenderer(createEditImageCellRender());
		configs.add(columnEdit);

		ColumnConfig columnRemove = new ColumnConfig();
		columnRemove.setId("removeMessage");
		columnRemove.setWidth(35);
		columnRemove.setRenderer(createRemoveImageCellRender());
		configs.add(columnRemove);

		// column.setWidth(35);

		// configs.add(columnMessage);
		//
		// ColumnConfig columnRemove = new ColumnConfig();
		// columnRemove.setId("applyMessage");
		// columnRemove.setWidth(35);
		// columnRemove.setRenderer(createRemoveImageCellRender());
		//
		// configs.add(column);
		//
		//
		// ColumnConfig column2 = new ColumnConfig();
		// column2.setId("description");
		// column2.setRenderer(createDescriptionCellRender());
		// // column2.ser
		//
		// column2.setResizable(true);
		//
		// configs.add(column2);

		ColumnModel columnModel = new ColumnModel(configs);

		return columnModel;
	}

	private GridCellRenderer<DtoPredefinedMessage> createRemoveImageCellRender() {
		GridCellRenderer<DtoPredefinedMessage> render = new GridCellRenderer<DtoPredefinedMessage>() {
			@Override
			public Object render(final DtoPredefinedMessage model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<DtoPredefinedMessage> store, Grid<DtoPredefinedMessage> grid) {

				Image image = new Image("http://cdn1.iconfinder.com/data/icons/basicset/trash_16.png");
				image.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						
						fireEvent(OnRemoveMessage, new SlidePanelEvent(SlidePanel.this, model));
					}
				});
				return image;
			}
		};
		return render;
	}

	private GridCellRenderer<DtoPredefinedMessage> createEditImageCellRender() {
		GridCellRenderer<DtoPredefinedMessage> render = new GridCellRenderer<DtoPredefinedMessage>() {
			@Override
			public Object render(final DtoPredefinedMessage model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<DtoPredefinedMessage> store, Grid<DtoPredefinedMessage> grid) {

				Image image = new Image("http://cdn1.iconfinder.com/data/icons/gnomeicontheme/16x16/actions/gtk-edit.png");
				image.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						com.google.gwt.user.client.Window.alert("Editando a mensagem: " + model.getText());
					}
				});
				return image;
			}
		};
		return render;
	}

	private GridCellRenderer<DtoPredefinedMessage> createApplyImageCellRender() {
		GridCellRenderer<DtoPredefinedMessage> render = new GridCellRenderer<DtoPredefinedMessage>() {
			@Override
			public Object render(final DtoPredefinedMessage model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<DtoPredefinedMessage> store, Grid<DtoPredefinedMessage> grid) {

				Image image = new Image("http://cdn1.iconfinder.com/data/icons/crystalproject/16x16/apps/clean.png");
				image.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						com.google.gwt.user.client.Window.alert("Usando a mensagem: " + model.getText());
					}
				});
				return image;
			}
		};
		return render;
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
			layoutContainer.add(getNewMessage());
		}
		return layoutContainer;
	}

	private LayoutContainer getLayoutContainer_1() {
		if (layoutContainer_1 == null) {
			layoutContainer_1 = new LayoutContainer();
			layoutContainer_1.setSize("30", "30");
			layoutContainer_1.setLayout(new CenterLayout());
			layoutContainer_1.add(getImage());
		}
		return layoutContainer_1;
	}

	private TextField<String> getNewMessage() {
		if (newMessage == null) {
			newMessage = new TextField<String>();
			newMessage.setWidth("221px");
			newMessage.setFieldLabel("New TextField");
		}
		return newMessage;
	}

	private Image getImage() {
		if (saveImage == null) {
			saveImage = new Image("http://cdn1.iconfinder.com/data/icons/CrystalClear/22x22/actions/button_ok.png");
			saveImage.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (newMessage.getValue() != null) {
						DtoPredefinedMessage message = new DtoPredefinedMessage(newMessage.getValue());
						addMessageToGrid(message);
						fireEvent(OnSaveNewMessage, new SlidePanelEvent(SlidePanel.this, message));
					}
				}
			});
		}
		return saveImage;
	}

	protected void removeMessageFromGrid(DtoPredefinedMessage message) {
		gridPredefinedMessage.getStore().remove(message);
	}
	protected void addMessageToGrid(DtoPredefinedMessage message) {
		gridPredefinedMessage.getStore().add(message);
	}

	protected void saveMessage() {

	}

	public void addSelectMessageListener(Listener<SlidePanelEvent> listener) {
		addListener(OnSelectedMessage, listener);
	}

	public void addSaveNewMessageListener(Listener<SlidePanelEvent> listener) {
		addListener(OnSaveNewMessage, listener);
	}

	public void addRemoveMessageListener(Listener<SlidePanelEvent> listener) {
		addListener(OnRemoveMessage, listener);
	}

	public void addEditMessageListener(Listener<SlidePanelEvent> listener) {
		addListener(OnEditMessage, listener);
	}
}
