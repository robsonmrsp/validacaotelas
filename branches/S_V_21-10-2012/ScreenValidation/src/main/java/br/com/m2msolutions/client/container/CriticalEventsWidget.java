package br.com.m2msolutions.client.container;

import java.util.ArrayList;
import java.util.List;

import br.com.m2msolutions.client.CriticalEventMessage;
import br.com.m2msolutions.client.CriticalEventsService;
import br.com.m2msolutions.client.CriticalEventsServiceAsync;
import br.com.m2msolutions.client.DtoCriticalEventsInfo;
import br.com.m2msolutions.client.SimpleGwtLogger;
import br.com.m2msolutions.client.images.Images;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.mr.dock.client.DockWindow;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Direction;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FxEvent;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

import de.novanic.eventservice.client.event.Event;
import de.novanic.eventservice.client.event.RemoteEventService;
import de.novanic.eventservice.client.event.RemoteEventServiceFactory;
import de.novanic.eventservice.client.event.listener.RemoteEventListener;

public class CriticalEventsWidget extends DockWindow {

	// TODO Verificar se será filtrado SOMENTE os que estão no grid
	private Html heading;
	private LayoutContainer header;
	private LayoutContainer principal;
	private Image expandButton;
	static boolean disableImage = false;
	private LayoutContainer headingContainer;
	private LayoutContainer imageContainer;
	private LabeledImage alertIcon;
	private LabeledImage paneIcon;
	private LabeledImage otherIcon;
	private Grid<DtoCriticalEvent> gridEvents;
	private GridCellRenderer<DtoCriticalEvent> imageCellRender;
	private GridCellRenderer<DtoCriticalEvent> descriptionCellRender;

	private LayoutContainer footer;
	private SearchBox searchBox;
	private LayoutContainer buttonAttContainer;

	private LayoutContainer gridContainer;
	private Image image_1;

	CriticalEventsServiceAsync criticalEventsService = CriticalEventsService.Util.getInstance();

	public CriticalEventsWidget() {
		super();
		setSize(350, Style.DEFAULT);
		setId("areaInspector");
		setStyleName("widget-window");
		setLayout(new RowLayout(Orientation.VERTICAL));
		add(getHeader(), new RowData(1, 22.0, new Margins()));
		add(getPrincipal(), new RowData(Style.DEFAULT, Style.DEFAULT, new Margins()));
		hide();
		disableHorizontalScroll();
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		getCriticalEventsInfo();
	}

	private void getCriticalEventsInfo() {
		criticalEventsService.getCriticalEventsInfo(new AsyncCallback<DtoCriticalEventsInfo>() {
			@Override
			public void onSuccess(DtoCriticalEventsInfo criticalEventsInfo) {
				gridEvents.getStore().add(criticalEventsInfo.getEvents());
				initDataPush();
			}

			@Override
			public void onFailure(Throwable caught) {
				SimpleGwtLogger.error("Get CriticalEventsInfo: " + caught.getMessage());
			}
		});
	}

//	TODO ficará dento do onReceive do sistema de atores remotos
	protected void initDataPush() {
		
		RemoteEventService criticalEventsRemoteEventService = RemoteEventServiceFactory.getInstance().getRemoteEventService();
		criticalEventsRemoteEventService.addListener(CriticalEventMessage.SERVER_MESSAGE_DOMAIN, new RemoteEventListener() {
			public void apply(Event anEvent) {
				if (anEvent instanceof CriticalEventMessage) {
					CriticalEventMessage eventMessage = (CriticalEventMessage) anEvent;
					DtoCriticalEvent criticalEvent = eventMessage.getCriticalEvent();
					// TODO criar um local/metodo que centralize a atualização do grid;
					gridEvents.getStore().add(criticalEvent);
				}
			}
		});

	}

	public LayoutContainer getHeader() {
		if (header == null) {
			header = new LayoutContainer();
			header.setBorders(false);
			header.setStyleName("widget-window-heading");
			header.setLayout(new BorderLayout());
			header.add(getHeadingContainer(), new BorderLayoutData(LayoutRegion.CENTER));
			header.add(getImageContainer(), new BorderLayoutData(LayoutRegion.EAST, 20.0F));
			header.setStyleAttribute("cursor", "move");
		}
		return header;
	}

	private LayoutContainer getFooter() {
		if (footer == null) {
			footer = new LayoutContainer();
			footer.setId("footer");
			footer.setHeight(25);
			footer.setBorders(false);
			footer.setStyleName("widget-window-footer");
			footer.setLayout(new BorderLayout());
			footer.add(getSearchBox(), new BorderLayoutData(LayoutRegion.CENTER));
			footer.add(getButtonAttContainer(), new BorderLayoutData(LayoutRegion.EAST, 25.0f));
		}
		return footer;
	}

	protected LayoutContainer getPrincipal() {
		if (principal == null) {
			principal = new LayoutContainer();
			principal.setId("principal");
			principal.setLayout(new RowLayout(Orientation.VERTICAL));
			principal.setBorders(false);
			// principal.add(getGridContainer(), new RowData(-1, 200.0, new
			// Margins()));
			principal.add(getGrid());
			principal.add(getFooter(), new RowData(1, 25.0, new Margins()));
		}
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
			headingContainer.setLayout(new RowLayout(Orientation.HORIZONTAL));
			headingContainer.setBorders(false);
			headingContainer.add(getAlertIcon());
			headingContainer.add(getPaneIcon());
			headingContainer.add(getOtherIcon());
			// headingContainer.add(getImageAlert());
			// headingContainer.add(getHeading());
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
			heading.setHeight("18");
		}
		return heading;
	}

	@Override
	public void show() {
		super.show();

		setPosition(300, 100);
		if (!isAttached()) {
			RootPanel.get().add(this);
		}
		el().makePositionable(true);

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

	public void addLabeledImage(LabeledImage image) {
		headingContainer.add(image);
	}

	private LabeledImage getAlertIcon() {
		if (alertIcon == null) {
			alertIcon = new LabeledImage();
			alertIcon.setImage(Images.INSTANCE.alert16());
			alertIcon.setEventQtd(2);
		}
		return alertIcon;
	}

	private LabeledImage getPaneIcon() {
		if (paneIcon == null) {
			paneIcon = new LabeledImage();
			paneIcon.setImage(Images.INSTANCE.pane16());
			paneIcon.setEventQtd(4);

		}
		return paneIcon;
	}

	private LabeledImage getOtherIcon() {
		if (otherIcon == null) {
			otherIcon = new LabeledImage();
			otherIcon.setImage(Images.INSTANCE.message16());
			otherIcon.setEventQtd(1);
		}
		return otherIcon;
	}

	private Grid<DtoCriticalEvent> getGrid() {
		if (gridEvents == null) {
			gridEvents = new Grid<DtoCriticalEvent>(createListStory(), createColumnConfig());
			// gridEvents.setAutoExpandMax(500);
			// gridEvents.setWidth(400);
			//
			// gridEvents.setBorders(false);
			// gridEvents.setId("gridEvents");
			//
			// gridEvents.setAutoWidth(true);
			// // gridEvents.setWidth(335);
			// gridEvents.setColumnResize(true);
			// // gridEvents.setWidth(Style.DEFAULT);
			// gridEvents.setLoadMask(true);
			// gridEvents.setHideHeaders(true);
			// gridEvents.getView().setEmptyText("Sem eventos para exibir...");
			// gridEvents.setAutoExpandColumn(DtoEvent.DESCRIPTION);
			gridEvents.setSize(360, 190);
			// gridEvents.setAutoExpandMax(500);
			gridEvents.setSelectionModel(new GridSelectionModel<DtoCriticalEvent>());
			gridEvents.setBorders(false);
			gridEvents.setStripeRows(true);
			gridEvents.setId("gridEvents");
			gridEvents.setAutoWidth(false);
			gridEvents.setColumnResize(false);
			gridEvents.setHideHeaders(true);
			// gridEvents.setHeight(Style.DEFAULT);
			gridEvents.setAutoExpandColumn(DtoCriticalEvent.DESCRIPTION);
		}

		return gridEvents;
	}

	public void addActionOnDobleClick(Listener<GridEvent<DtoCriticalEvent>> listener) {
		gridEvents.addListener(Events.OnDoubleClick, listener);
	}

	private void disableHorizontalScroll() {
		addListener(Events.Attach, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (gridEvents.getView().getBody() != null)
					gridEvents.getView().getBody().setStyleAttribute("overflowX", "hidden");
			}
		});
	}

	private GridCellRenderer<DtoCriticalEvent> createImageCellRender() {
		imageCellRender = new GridCellRenderer<DtoCriticalEvent>() {
			public String render(DtoCriticalEvent model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<DtoCriticalEvent> store, Grid<DtoCriticalEvent> grid) {
				return "<span><img src=\"" + model.getImageSrc() + "\" alt=\"image\">  </span>";
			}
		};
		return imageCellRender;
	}

	private ColumnModel createColumnConfig() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId(DtoCriticalEvent.IMAGE_SRC);
		column.setWidth(35);
		column.setRenderer(createImageCellRender());
		configs.add(column);

		ColumnConfig column2 = new ColumnConfig();
		// column2.setWidth(400);
		column2.setId(DtoCriticalEvent.DESCRIPTION);
		column2.setRenderer(createDescriptionCellRender());

		configs.add(column2);

		ColumnModel columnModel = new ColumnModel(configs);

		return columnModel;
	}

	private GridCellRenderer<DtoCriticalEvent> createDescriptionCellRender() {
		descriptionCellRender = new GridCellRenderer<DtoCriticalEvent>() {
			public String render(DtoCriticalEvent model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<DtoCriticalEvent> store, Grid<DtoCriticalEvent> grid) {
				return createDescription(model);
			}
		};
		return descriptionCellRender;
	}

	private String createDescription(DtoCriticalEvent model) {
		if (model.getStatus().equals("IN_ATTENDANCE")) {
			return "<span style=\"padding-top: 10px;\"> " + model.getVehicleCode() + " -  (" + model.getOperator() + ") - " + model.getTimeAttendance() + "   </span>";
		} else {
			return "<span style=\"padding-top: 10px;\"> " + model.getVehicleCode() + " - " + model.getTimeInEvent() + "   </span>";
		}
		// return "<span style=\"padding-top: 10px;\"> " +
		// model.getVehicleCode() + "   </span>";
	}

	private ListStore<DtoCriticalEvent> createListStory() {
		ListStore<DtoCriticalEvent> listStore = new ListStore<DtoCriticalEvent>();
		listStore.add(ClienteDataUtil.ALL_EVENTS);
		return listStore;
	}

	private LayoutContainer getSearchBox() {
		if (searchBox == null) {
			searchBox = new SearchBox();
			searchBox.setId("searchBoxContainer");
			searchBox.setTextWhenEmpty("Buscar");
			searchBox.setChangeListener(new Listener<BaseEvent>() {
				@Override
				public void handleEvent(BaseEvent be) {
					SearchBox sb = (SearchBox) be.getSource();

					if (sb.length() > 2 || sb.length() == 0) {
						runSearch(sb.getValue());
					}
				}
			});
		}
		return searchBox;
	}

	protected void runSearch(String regex) {
		gridEvents.getStore().removeAll();
		if (regex.isEmpty())
			gridEvents.getStore().add(ClienteDataUtil.ALL_EVENTS);
		else
			gridEvents.getStore().add(ClienteDataUtil.applyEventCISearch(regex.split(" ")));
	}

	private LayoutContainer getButtonAttContainer() {
		if (buttonAttContainer == null) {
			buttonAttContainer = new LayoutContainer();
			buttonAttContainer.setStyleName("button-att");
			buttonAttContainer.setId("buttonAttContainer");
			buttonAttContainer.setBorders(false);
			buttonAttContainer.setLayout(new CenterLayout());
			buttonAttContainer.add(getImage_1());
			buttonAttContainer.setToolTip("Ver consulta de atendimento");
			buttonAttContainer.setStyleAttribute("cursor", "pointer");

		}
		return buttonAttContainer;
	}

	// private TextBox getSearchBox() {
	// if (searchBox == null) {
	// searchBox = new TextBox();
	// // searchBox.setHeight("16px");
	// searchBox.getElement().setId("search");
	// searchBox.getElement().setAttribute("placeholder", "Buscar");
	// searchBox.setStyleName("search-box");
	// }
	// return searchBox;
	// }

	private LayoutContainer getGridContainer() {
		if (gridContainer == null) {
			gridContainer = new LayoutContainer();
			gridContainer.setBorders(true);
			gridContainer.setId("gridContainer");
			gridContainer.setLayout(new FitLayout());
			gridContainer.setHeight(200);
			gridContainer.add(getGrid());
		}
		return gridContainer;
	}

	private Image getImage_1() {
		if (image_1 == null) {
			image_1 = new Image(Images.INSTANCE.attendance22());
			image_1.setSize("24px", "24px");
			image_1.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					queryAttendanceWindow().show();
				}
			});
		}
		return image_1;
	}

	private Window queryAttendanceWindow() {
		CopyOfAttendanceWindow attendanceWindow = new CopyOfAttendanceWindow();
		attendanceWindow.setAutoWidth(true);

		Window window = new Window();
		window.setBodyBorder(false);
		window.setBorders(false);

		window.setMaximizable(true);
		window.setMinimizable(true);
		window.setLayout(new FitLayout());
		window.setSize(1015, 550);

		window.add(attendanceWindow);
		return window;
	}

}
