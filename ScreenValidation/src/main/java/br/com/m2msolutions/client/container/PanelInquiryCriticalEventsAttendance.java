package br.com.m2msolutions.client.container;

import java.util.ArrayList;
import java.util.List;

import br.com.m2msolutions.client.InquiryAttendanceService;
import br.com.m2msolutions.client.InquiryAttendanceServiceAsync;
import br.com.m2msolutions.client.SimpleGwtLogger;
import br.com.m2msolutions.client.images.Images;
import br.com.m2msolutions.shared.dto.DtoAboutEvent;
import br.com.m2msolutions.shared.dto.DtoContact;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoOperator;
import br.com.m2msolutions.shared.dto.DtoRecord;
import br.com.m2msolutions.shared.dto.DtoSearchParameters;
import br.com.m2msolutions.shared.dto.DtoVehicleAndLocation;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Util;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapDoubleClickHandler;
import com.google.gwt.maps.client.event.MapDragEndHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.extjs.gxt.ui.client.widget.Slider;
import com.google.gwt.user.client.ui.Image;

public class PanelInquiryCriticalEventsAttendance extends LayoutContainer {

	private ContentPanelImp queryContainer;
	private ContentPanelImp eventsContainer;
	private TextField<String> textFieldProtocol;
	private TextField<String> textFieldVehicle;
	private ComboBox<DtoOperator> comboOperator;
	private Grid<DtoCriticalEvent> gridEvents;
	private Grid<DtoRecord> gridRecords;
	private ListView<DtoRecord> listViewRecords;
	private DateField dateFieldTo;
	private DateField dateFieldFrom;
	private ContentPanelImp aboutEventContainer;
	private ContentPanelImp vehicleLocationContainer;
	private LayoutContainer mapContainer;
	private ContentPanelImp contactContainer;
	private ContentPanelImp occurrenceRecordsContainer;
	private MapWidget mapLocation;
	private Button buttonSearch;

	private XTemplate aboutEventTemplate;
	private XTemplate contactTemplate;
	private XTemplate vehicleLocationTemplate;

	private Html htmlVehicleLocation;
	private Html htmlAboutEvent;
	private Html htmlContact;

	private LayoutContainer leftContainer;
	private LayoutContainer midleContainer;
	private LayoutContainer rightContainer;
	private LayoutContainer toFromContainer;
	private LayoutContainer protocolContainer;

	MapHandler mapHandler = new MapHandler();

	// Grid<DtoEvent> grid;
	// LayoutContainer gridContainer;
	GridCellRenderer<DtoCriticalEvent> imageCellRender;
	GridCellRenderer<DtoRecord> imageRecordCellRender;

	private GridCellRenderer<DtoCriticalEvent> descriptionCellRender;
	private GridCellRenderer<DtoRecord> descriptionRecordCellRender;

	InquiryAttendanceServiceAsync attendanceService = GWT.create(InquiryAttendanceService.class);

	private MapNavigationToolBox mapNavigationToolBox;

	public PanelInquiryCriticalEventsAttendance() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		BorderLayoutData leftLayoutData = new BorderLayoutData(LayoutRegion.WEST, 305.0f);
		leftLayoutData.setMargins(new Margins(5, 2, 5, 5));
		add(getLeftContainer(), leftLayoutData);

		BorderLayoutData midleLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
		midleLayoutData.setMargins(new Margins(5, 2, 5, 2));
		add(getMidleContainer(), midleLayoutData);

		BorderLayoutData rightLayoutData = new BorderLayoutData(LayoutRegion.EAST, 280.0f);
		rightLayoutData.setMargins(new Margins(5, 5, 5, 2));
		add(getRightContainer(), rightLayoutData);

		createTemplates();

		disableOrizontalScroll();
	}

	private void createTemplates() {
		aboutEventTemplate = XTemplate.create(AttendanceWidGetTemplates.ABOUT_EVENT);
		contactTemplate = XTemplate.create(AttendanceWidGetTemplates.CONTACTS);
		vehicleLocationTemplate = XTemplate.create(AttendanceWidGetTemplates.VEHICLE_LOCATION);

		htmlAboutEvent.setHtml(aboutEventTemplate.applyTemplate(Util.getJsObject(new BaseModelData())));
		htmlContact.setHtml(contactTemplate.applyTemplate(Util.getJsObject(new BaseModelData())));
		htmlVehicleLocation.setHtml(vehicleLocationTemplate.applyTemplate(Util.getJsObject(new BaseModelData())));

	}

	// private void updateAboutEventContainer(DtoAboutEvent about) {
	// aboutEventTemplate.overwrite(htmlAboutEvent.getElement(),
	// Util.getJsObject(about));
	// }
	//
	// private void updateContactContainer(DtoContact contact) {
	// contactTemplate.overwrite(htmlContact.getElement(),
	// Util.getJsObject(contact));
	// }
	//
	// private void updateVehicleLocationContainer(DtoVehicleAndLocation
	// contact) {
	// vehicleLocationTemplate.overwrite(htmlVehicleLocation.getElement(),
	// Util.getJsObject(contact));
	// }

	private TextField<String> getTextFieldProtocol() {
		if (textFieldProtocol == null) {
			textFieldProtocol = new TextField<String>();
			textFieldProtocol.setFieldLabel("Protocolo");
		}
		return textFieldProtocol;
	}

	private TextField<String> getTextFieldVehicle() {
		if (textFieldVehicle == null) {
			textFieldVehicle = new TextField<String>();
			textFieldVehicle.setFieldLabel("Veículo");
		}
		return textFieldVehicle;
	}

	private ComboBox<DtoOperator> getComboOperator() {
		if (comboOperator == null) {
			comboOperator = new ComboBox<DtoOperator>();
			comboOperator.setStore(new ListStore<DtoOperator>());
			comboOperator.setFieldLabel("Operador");
			comboOperator.setId("comboOperator");
			comboOperator.setValueField(DtoOperator.ID);
			comboOperator.setDisplayField(DtoOperator.NAME);
			comboOperator.setTypeAhead(true);
			comboOperator.setTriggerAction(TriggerAction.ALL);
		}
		return comboOperator;
	}

	private DateField getDateFieldTo() {
		if (dateFieldTo == null) {
			dateFieldTo = new DateField();
			dateFieldTo.setFieldLabel("Até");
			dateFieldTo.setPropertyEditor(new DateTimePropertyEditor("dd/MM/yyyy"));
		}
		return dateFieldTo;
	}

	private DateField getDateFieldFrom() {
		if (dateFieldFrom == null) {
			dateFieldFrom = new DateField();
			dateFieldFrom.setSize("-1", "-1");
			dateFieldFrom.setFieldLabel("De");
			dateFieldFrom.setPropertyEditor(new DateTimePropertyEditor("dd/MM/yyyy"));
		}
		return dateFieldFrom;
	}

	private ToolButton createEditButton() {
		return new ToolButton(Images.INSTANCE.edit16(), new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				Window.alert("editando");
			}
		});
	}

	private ToolButton createPrinterButton() {

		return new ToolButton(Images.INSTANCE.printer16(), new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				Window.alert("imprimindo");
			}
		});
	}

	private Button getButtonSearch() {
		if (buttonSearch == null) {
			buttonSearch = new Button("Pesquisar");
			buttonSearch.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					findEvents();
				}
			});

		}
		return buttonSearch;
	}

	protected void findEvents() {
		attendanceService.findEventsByParameter(createSearchParamenter(), new AsyncCallback<ArrayList<DtoCriticalEvent>>() {
			@Override
			public void onFailure(Throwable caught) {
				SimpleGwtLogger.error("Find EventsByParameter failure", caught);
			}

			@Override
			public void onSuccess(ArrayList<DtoCriticalEvent> events) {
				gridEvents.getStore().removeAll();
				gridEvents.getStore().add(events);
			}
		});
	}

	private DtoSearchParameters createSearchParamenter() {
		DtoSearchParameters searchParameters = new DtoSearchParameters(getValue(dateFieldFrom), getValue(dateFieldTo), getValue(comboOperator), getValue(textFieldVehicle), getValue(textFieldVehicle));
		return searchParameters;
	}

	private String getValue(Field field) {

		Object value = field.getRawValue();

		String result = value.toString();
		return result;
	}

	private Html getHtmlVehicleLocation() {
		if (htmlVehicleLocation == null) {
			htmlVehicleLocation = new Html();
			htmlVehicleLocation.setSize("-1", "100");
			// "\t<table id=\"template-vehicle-location\", class=\"vehicle-location\">\r\n\t\t<tr>\r\n\t\t\t<td style=\"width: 200px; \">Veículo: 3050</td>\r\n\t\t\t<td style=\"width: 200px; \">Endereço: BR 123</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Linha: SantaCruz</td>\r\n\t\t\t<td>Latitude: 12,345</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Empresa: SantaCruz</td>\r\n\t\t\t<td>Longitude: 22,345</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Proximo ponto: ETUFOR</td>\r\n\t\t</tr>\r\n\t</table>");
		}
		return htmlVehicleLocation;
	}

	private Html getHtmlAboutEvent() {
		if (htmlAboutEvent == null) {
			htmlAboutEvent = new Html();
			// "\t<div id=\"template-about-event\" class=\"about-event\">\r\n\t\t<br>Numero do protocolo: 12345 \r\n\t\t<br>Inicio do evento: 09/07/2012 14:30 \r\n\t\t<br>Inicio do atendimento: 09/07/2012 14:31 \r\n\t\t<br>Tempo de corrido do atendimento: 5m \r\n\t\t<br>Atendente atual: Operador 1 \r\n\t\t<br>Conclusão do atendimento: 14:35      \t\t\r\n\t</div>");
		}
		return htmlAboutEvent;
	}

	private Html getHtmlContact() {
		if (htmlContact == null) {
			htmlContact = new Html();
			// "\t<div id=\"template-contact\" class=\"contact\">\r\n\t<table>\r\n\t\t<tr>\t\r\n\t\t\t<td> <img id=\"image-message\" src=\"http://cdn1.iconfinder.com/data/icons/humano2/72x72/emblems/emblem-people.png\" </td>\r\n\t\t\t<td>\r\n\t\t\t\t <div id=\"record\" class=\"record\">\t\t\t \r\n\t\t\t\t\t<br> Nome: Antonio Firmulano da Cunha Matos \r\n\t\t\t\t\t<br>Matricula:  097286358 \r\n\t\t\t\t\t<br>Telefone: 3354-7689 \r\n\t\t\t\t\t<br>Tempo de corrido do atendimento: 5m \r\n\t\t\t\t\t<br>Idade: 43 anos \r\n\t\t\t\t </div> \r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t</table>\r\n\t</div>\r\n");
		}
		return htmlContact;
	}

	private Grid<DtoRecord> getGridRecords() {
		if (gridRecords == null) {
			gridRecords = new Grid<DtoRecord>(createRecordListStory(), createRecordColumnConfig());
			gridRecords.setSize("-1", "290");
			gridRecords.setBorders(false);
			gridRecords.disableEvents(true);
			gridRecords.setTrackMouseOver(false);
			gridRecords.setId("gridRecords");
			gridRecords.setWidth(Style.DEFAULT);
			gridRecords.setColumnLines(false);
			gridRecords.setHideHeaders(true);
			gridRecords.setStripeRows(true);
			gridRecords.setAutoExpandColumn(DtoRecord.DESCRIPTION);
		}
		return gridRecords;
	}

	private ListView<DtoRecord> getListView() {
		listViewRecords = new ListView<DtoRecord>(new ListStore<DtoRecord>());
		// listViewRecords.setOverStyle("");
		// listViewRecords.addStyleName("msg");
		// listViewRecords.setTemplate(TEMPLATE_MSG);

		return listViewRecords;

	}

	private Grid<DtoCriticalEvent> getGridEvents() {
		if (gridEvents == null) {
			gridEvents = new Grid<DtoCriticalEvent>(createListStory(), createColumnConfig());
			gridEvents.setSize("-1", "290");
			// gridEvents.setAutoExpandMax(500);
			gridEvents.setSelectionModel(new GridSelectionModel<DtoCriticalEvent>());
			gridEvents.setBorders(false);
			gridEvents.setStripeRows(true);
			gridEvents.setId("gridEvents");
			gridEvents.setAutoWidth(false);
			gridEvents.setColumnResize(false);
			gridEvents.setHideHeaders(true);
			gridEvents.setHeight(Style.DEFAULT);
			gridEvents.setAutoExpandColumn(DtoCriticalEvent.DESCRIPTION);
			gridEvents.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<DtoCriticalEvent>() {

				@Override
				public void selectionChanged(SelectionChangedEvent<DtoCriticalEvent> se) {
					DtoCriticalEvent selectedItem = se.getSelectedItem();
					if (selectedItem != null) {
						onSelectionEventChange(selectedItem);
					}
				}
			});
		}
		return gridEvents;
	}

	protected void onSelectionEventChange(DtoCriticalEvent selectedItem) {
		findExtraInfoEvent(selectedItem);
	}

	private void findExtraInfoEvent(DtoCriticalEvent selectedItem) {
		attendanceService.findExtraInfoEvent(selectedItem, new AsyncCallback<DtoExtraInfoEvent>() {
			@Override
			public void onSuccess(DtoExtraInfoEvent extraInfo) {
				applyExtraInfo(extraInfo);
			}

			@Override
			public void onFailure(Throwable caught) {
				SimpleGwtLogger.error("Find ExtraInfoEvent Failure ", caught);
			}
		});
	}

	private void disableOrizontalScroll() {
		addListener(Events.Attach, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (gridEvents.getView().getBody() != null)
					gridEvents.getView().getBody().setStyleAttribute("overflowX", "hidden");
				if (gridRecords.getView().getBody() != null)
					gridRecords.getView().getBody().setStyleAttribute("overflowX", "hidden");
			}
		});
	}

	private ContentPanelImp getEventsContainer() {
		if (eventsContainer == null) {
			eventsContainer = new ContentPanelImp();
			eventsContainer.setHeading("Eventos");
			eventsContainer.setBodyBorder(false);
			eventsContainer.setBorders(false);
			eventsContainer.setLayout(new FitLayout());
			eventsContainer.add(getGridEvents());
			// eventsContainer.add(getListView());
		}
		return eventsContainer;
	}

	private ContentPanelImp getAboutEventContainer() {
		if (aboutEventContainer == null) {
			aboutEventContainer = new ContentPanelImp();
			aboutEventContainer.setHeight("150");
			aboutEventContainer.setHeading("Sobre o Evento");
			aboutEventContainer.setCollapsible(true);
			aboutEventContainer.addToolButton(createPrinterButton());

			aboutEventContainer.setLayout(new FitLayout());

			FitData fd_htmlAboutEvent = new FitData(0);
			fd_htmlAboutEvent.setMargins(new Margins(6, 6, 6, 6));
			aboutEventContainer.add(getHtmlAboutEvent(), fd_htmlAboutEvent);
		}
		return aboutEventContainer;
	}

	private ContentPanelImp getVehicleLocationContainer() {
		if (vehicleLocationContainer == null) {
			vehicleLocationContainer = new ContentPanelImp();
			vehicleLocationContainer.setHeading("Veículo e Localização");
			vehicleLocationContainer.setCollapsible(true);
			vehicleLocationContainer.setLayout(new BorderLayout());
			BorderLayoutData bld_htmlVehicleLocation = new BorderLayoutData(LayoutRegion.NORTH, 100.0f);
			bld_htmlVehicleLocation.setMinSize(100);
			bld_htmlVehicleLocation.setMaxSize(100);
			vehicleLocationContainer.add(getHtmlVehicleLocation(), bld_htmlVehicleLocation);
			BorderLayoutData bld_mapContainer = new BorderLayoutData(LayoutRegion.CENTER, 270.0f);
			bld_mapContainer.setMinSize(270);
//			bld_mapContainer.setMargins(new Margins(5, 5, 5, 5));
			vehicleLocationContainer.add(getMapContainer(), bld_mapContainer);
			vehicleLocationContainer.setBottomComponent(getMapTolboxContainer());
		}
		return vehicleLocationContainer;
	}

	private ContentPanelImp getContactContainer() {
		if (contactContainer == null) {
			contactContainer = new ContentPanelImp();

			contactContainer.setHeading("Contato");
			contactContainer.setCollapsible(true);
			contactContainer.setLayout(new FitLayout());
			FitData fd_htmlContact = new FitData(0);
			fd_htmlContact.setMargins(new Margins(0, 6, 6, 6));
			contactContainer.add(getHtmlContact(), fd_htmlContact);
		}
		return contactContainer;
	}

	private ContentPanelImp getOccurrenceRecordsContainer() {
		if (occurrenceRecordsContainer == null) {
			occurrenceRecordsContainer = new ContentPanelImp();
			occurrenceRecordsContainer.setHeading("Registro de ocorrência");
			occurrenceRecordsContainer.setCollapsible(true);
			occurrenceRecordsContainer.setLayout(new FitLayout());
			occurrenceRecordsContainer.add(getGridRecords());
			// occurrenceRecordsContainer.add(getListView());
		}
		return occurrenceRecordsContainer;
	}

	private ContentPanelImp getQueryContainer() {
		if (queryContainer == null) {
			queryContainer = new ContentPanelImp();
			queryContainer.setHeight("200");
			queryContainer.setHeading("Periodo de inicio do evento");
			queryContainer.setLayout(new RowLayout(Orientation.VERTICAL));
			queryContainer.add(getToFromContainer(), new RowData(Style.DEFAULT, Style.DEFAULT, new Margins(5, 5, 5, 5)));
			queryContainer.add(getAditionalQueryParanetersContainer(), new RowData(Style.DEFAULT, Style.DEFAULT, new Margins(5, 5, 5, 5)));
		}
		return queryContainer;
	}

	private Widget getMapPosition() {

		LatLng fortalCity = LatLng.newInstance(-3.736549, -38.523804);
		mapLocation = new MapWidget();

		mapLocation.setCenter(fortalCity);
		mapLocation.checkResizeAndCenter();
		mapLocation.setSize("100%", "100%");

		mapLocation.addMapClickHandler(mapHandler);
		mapLocation.addMapDoubleClickHandler(mapHandler);
		mapLocation.addMapDragEndHandler(mapHandler);

		return mapLocation;

	}

	private LayoutContainer getAditionalQueryParanetersContainer() {
		LayoutContainer aditionalQueryParanetersContainer = new LayoutContainer();
		aditionalQueryParanetersContainer.setLayout(new ColumnLayout());
		aditionalQueryParanetersContainer.add(getVehicleContainer(), new ColumnData(.5));
		aditionalQueryParanetersContainer.add(getProtocolContainer(), new ColumnData(.5));
		return aditionalQueryParanetersContainer;
	}

	private LayoutContainer getLeftContainer() {
		if (leftContainer == null) {
			leftContainer = new LayoutContainer();
			leftContainer.setBorders(false);
			leftContainer.setLayout(new BorderLayout());
			BorderLayoutData bld_queryContainer = new BorderLayoutData(LayoutRegion.NORTH, 180.0f);
			bld_queryContainer.setMinSize(180);
			bld_queryContainer.setMaxSize(180);
			leftContainer.add(getQueryContainer(), bld_queryContainer);
			BorderLayoutData bld_eventsContainer = new BorderLayoutData(LayoutRegion.CENTER, 315.0f);
			bld_eventsContainer.setMinSize(315);
			bld_eventsContainer.setMaxSize(550);

			leftContainer.add(getEventsContainer(), bld_eventsContainer);
		}
		return leftContainer;
	}

	private LayoutContainer getMidleContainer() {
		if (midleContainer == null) {
			midleContainer = new LayoutContainer();
			midleContainer.setBorders(false);
			midleContainer.setLayout(new BorderLayout());
			midleContainer.add(getAboutEventContainer(), new BorderLayoutData(LayoutRegion.NORTH, 150.0f));
			midleContainer.add(getVehicleLocationContainer(), new BorderLayoutData(LayoutRegion.CENTER));
		}
		return midleContainer;
	}

	private LayoutContainer getRightContainer() {
		if (rightContainer == null) {
			rightContainer = new LayoutContainer();
			rightContainer.setBorders(false);
			rightContainer.setLayout(new BorderLayout());
			BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 150.0f);
			northData.setMinSize(150);
			northData.setMaxSize(150);
			rightContainer.add(getContactContainer(), northData);

			BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER, 315.0f);
			centerData.setMinSize(315);
			centerData.setMaxSize(550);

			rightContainer.add(getOccurrenceRecordsContainer(), centerData);
		}
		return rightContainer;
	}

	private LayoutContainer getMapContainer() {
		if (mapContainer == null) {
			mapContainer = new LayoutContainer();
			mapContainer.setHeight("400");
			mapContainer.add(getMapPosition());
			mapContainer.setBorders(false);
		}
		return mapContainer;
	}

	private LayoutContainer getToFromContainer() {
		if (toFromContainer == null) {
			toFromContainer = new LayoutContainer();
			toFromContainer.setLayout(new FormLayout());
			toFromContainer.add(getDateFieldFrom(), new FormData("100%"));
			toFromContainer.add(getDateFieldTo(), new FormData("100%"));
		}
		return toFromContainer;
	}

	private LayoutContainer getVehicleContainer() {
		LayoutContainer vehicleContainer = new LayoutContainer();

		FormLayout fl_vehicleContainer = new FormLayout();
		FormData fd_textFieldVehicle = new FormData("100%");
		FormData fd_comboOperator = new FormData("100%");

		fl_vehicleContainer.setLabelAlign(LabelAlign.TOP);

		fd_textFieldVehicle.setMargins(new Margins(0, 5, 0, 0));
		fd_comboOperator.setMargins(new Margins(0, 5, 0, 0));

		vehicleContainer.setLayout(fl_vehicleContainer);
		vehicleContainer.add(getComboOperator(), fd_comboOperator);
		vehicleContainer.add(getTextFieldVehicle(), fd_textFieldVehicle);
		return vehicleContainer;
	}

	private LayoutContainer getProtocolContainer() {
		if (protocolContainer == null) {
			protocolContainer = new LayoutContainer();
			FormLayout fl_protocolContainer = new FormLayout();
			fl_protocolContainer.setLabelAlign(LabelAlign.TOP);
			protocolContainer.setLayout(fl_protocolContainer);
			FormData fd_textFieldProtocol = new FormData("0");
			fd_textFieldProtocol.setMargins(new Margins(0, 0, 0, 5));
			protocolContainer.add(getTextFieldProtocol(), fd_textFieldProtocol);
			FormData fd_buttonSearch = new FormData("100%");
			fd_buttonSearch.setMargins(new Margins(20, 0, 0, 60));
			protocolContainer.add(getButtonSearch(), fd_buttonSearch);
		}
		return protocolContainer;
	}

	ArrayList<DtoCriticalEvent> testeListe() {
		ArrayList<DtoCriticalEvent> listStore = new ArrayList<DtoCriticalEvent>();
		// listStore.add(new
		// DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png",
		// "120", "22/10/2012 12:01"));
		// listStore.add(new
		// DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png",
		// "121", "22/10/2012 12:02"));
		// listStore.add(new
		// DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png",
		// "122", "22/10/2012 12:04"));
		// listStore.add(new
		// DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png",
		// "123", "22/10/2012 12:05"));
		// listStore.add(new
		// DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png",
		// "124", "22/10/2012 12:02"));
		// listStore.add(new
		// DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png",
		// "125", "22/10/2012 12:14"));
		// listStore.add(new
		// DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png",
		// "126", "22/10/2012 12:06"));
		// listStore.add(new
		// DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png",
		// "127", "22/10/2012 12:07"));
		// listStore.add(new
		// DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png",
		// "128", "22/10/2012 12:08"));
		// listStore.add(new
		// DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png",
		// "129", "22/10/2012 12:09"));
		// listStore.add(new
		// DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png",
		// "133", "22/10/2012 12:10"));
		return listStore;
	}

	private ListStore<DtoRecord> createRecordListStory() {
		ListStore<DtoRecord> listStore = new ListStore<DtoRecord>();

		return listStore;
	}

	private ListStore<DtoCriticalEvent> createListStory() {
		ListStore<DtoCriticalEvent> listStore = new ListStore<DtoCriticalEvent>();
		// listStore.add(UtilData.ALL_EVENTS);
		return listStore;
	}

	private ColumnModel createRecordColumnConfig() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// ColumnConfig column = new ColumnConfig();
		// column.setId(DtoRecord.IMAGE_SRC);
		// column.setWidth(35);
		// column.setRenderer(createRecordImageCellRender());
		// configs.add(column);

		ColumnConfig column2 = new ColumnConfig();
		column2.setId(DtoRecord.DESCRIPTION);
		column2.setRenderer(createRecordDescriptionCellRender());
		configs.add(column2);
		ColumnModel columnModel = new ColumnModel(configs);
		return columnModel;
	}

	private ColumnModel createColumnConfig() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("imageSrc");
		column.setWidth(35);
		column.setRenderer(createImageCellRender());
		configs.add(column);

		ColumnConfig column2 = new ColumnConfig();
		column2.setId("description");
		column2.setRenderer(createDescriptionCellRender());
		// column2.ser

		column2.setResizable(true);

		configs.add(column2);

		ColumnModel columnModel = new ColumnModel(configs);

		return columnModel;
	}

	private GridCellRenderer<DtoRecord> createRecordImageCellRender() {
		imageRecordCellRender = new GridCellRenderer<DtoRecord>() {
			@Override
			public Object render(DtoRecord model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore<DtoRecord> store, Grid<DtoRecord> grid) {
				return "<span><img src=\"" + model.getImageSrc() + "\" alt=\"image\">  </span>";

			}
		};
		return imageRecordCellRender;
	}

	private GridCellRenderer<DtoCriticalEvent> createImageCellRender() {
		imageCellRender = new GridCellRenderer<DtoCriticalEvent>() {

			@Override
			public Object render(DtoCriticalEvent model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore<DtoCriticalEvent> store, Grid<DtoCriticalEvent> grid) {
				return "<span><img src=\"" + model.getImageSrc() + "\" alt=\"image\">  </span>";
			}
		};
		return imageCellRender;
	}

	private GridCellRenderer<DtoCriticalEvent> createDescriptionCellRender() {
		descriptionCellRender = new GridCellRenderer<DtoCriticalEvent>() {
			@Override
			public Object render(DtoCriticalEvent model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore<DtoCriticalEvent> store, Grid<DtoCriticalEvent> grid) {
				return getRendered(model);
			}
		};
		return descriptionCellRender;
	}

	private GridCellRenderer<DtoRecord> createRecordDescriptionCellRender() {
		descriptionRecordCellRender = new GridCellRenderer<DtoRecord>() {
			@Override
			public Object render(DtoRecord model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore<DtoRecord> store, Grid<DtoRecord> grid) {
				return getNewRecordRendered(model);
			}
		};
		return descriptionRecordCellRender;
	}

	protected String getNewRecordRendered(DtoRecord model) {
		StringBuffer rendered = new StringBuffer();
		// TODO Melhorar o CSS para que fique mais com a cara da tela!
		// rendered.append("<div class=\"userMsg\"> ");
		rendered.append("<div> ");
		rendered.append("<table style=\"width: 100%; CELLSPACING:0px; \">");
		rendered.append("	<tr>");
		rendered.append("		<td rowspan='2' style=\"width: 5%\">");
		rendered.append("		 <img src=\"" + model.getImageSrc() + "\"/>");
		rendered.append("			<td>");
		rendered.append("				<div> " + model.getMessageDate() + "  </div>");
		rendered.append("			</td>");
		rendered.append("		</td>");
		rendered.append("	</tr>");
		rendered.append("	<tr>");
		rendered.append("	<td style=\" width: 90% \">");
		rendered.append("		<div style=\"width: 100%; height: 100%; margin: 5px; oveflow: hidden; word-wrap:break-word;\">");
		rendered.append("			<span align=\"justify\">" + model.getMessage() + "</span>");
		rendered.append("		</div>");
		rendered.append("	</td>");
		rendered.append("</table>");
		rendered.append("");
		return rendered.toString();

	}

	protected String getRecordRendered(DtoRecord model) {
		StringBuffer rendered = new StringBuffer();
		// rendered.append("<div>");
		rendered.append("	<table>");
		rendered.append("		<tr>");
		rendered.append("			<td>");
		rendered.append("				 <span>");
		rendered.append("				 	 [ " + model.getMessageDate() + " ] ");
		rendered.append("				 </span> ");
		rendered.append(model.getMessage());
		rendered.append("			</td>");
		rendered.append("		</tr>");
		rendered.append("	</table>");
		// rendered.append("</div>");

		return rendered.toString();
	}

	protected String getRendered(DtoCriticalEvent model) {
		StringBuffer rendered = new StringBuffer();
		// rendered.append("<div>");
		rendered.append("	<table>");
		rendered.append("		<tr>");
		rendered.append("			<td> inicio: " + model.getStartDateTimeAsText() + " </td>");
		rendered.append("			<td> Veiculo: " + model.getVehicleCode() + "</td>");
		rendered.append("		</tr>");
		rendered.append("		<tr>");
		rendered.append("			<td> Atendente: " + model.getOperator() + "</td>");
		rendered.append("			<td> Protocolo: " + model.getProtocol() + "</td>");
		rendered.append("		</tr>");
		rendered.append("	</table>");
		// rendered.append("</div>");
		return rendered.toString();
	}

	// FIXME por algum motivo ao formatarmos os textos nas celulas da tabela
	// como
	// abaixo o evento mouseover e click não funcionam.
	// para testar basta trocar a chamada ao metodo getRendered por get_rendered
	// abaixo
	protected String get_Rendered(DtoCriticalEvent model) {
		StringBuffer rendered = new StringBuffer();
		rendered.append("<div>");
		rendered.append("	<table>");
		rendered.append("		<tr>");
		rendered.append("			<td> <span style='color:red'> inicio</span>: <span class=\"template-content\">" + model.getStartDateTimeAsText() + " &nbsp &nbsp</span> </td>");
		rendered.append("			<td> <span class=\"template-label\">Veiculo</span>: <span class=\"template-content\">" + model.getVehicleCode() + "</span> </td>");
		rendered.append("		</tr>");
		rendered.append("		<tr>");
		rendered.append("			<td> <span class=\"template-label\">Atendente</span>: <span class=\"template-content\">" + model.getOperator() + "</span> </td>");
		rendered.append("			<td> <span class=\"template-label\">Protocolo</span>: <span class=\"template-content\">" + model.getProtocol() + "</span> </td>");
		rendered.append("		</tr>");
		rendered.append("	</table>");
		rendered.append("</div>");
		return rendered.toString();
	}

	private void updateRecordsContainer(ArrayList<DtoRecord> records) {
		gridRecords.getStore().removeAll();
		gridRecords.getStore().add(records);
	}

	private void updateAboutContainer(DtoAboutEvent aboutEvent) {
		htmlAboutEvent.setHtml(aboutEventTemplate.applyTemplate(Util.getJsObject(aboutEvent)));
	}

	private void updateContactContainer(DtoContact contact) {
		htmlContact.setHtml(contactTemplate.applyTemplate(Util.getJsObject(contact)));
	}

	private void updateVehicleLocationContainer(DtoVehicleAndLocation vehicleAndLocation) {
		htmlVehicleLocation.setHtml(vehicleLocationTemplate.applyTemplate(Util.getJsObject(vehicleAndLocation)));
		centerVehicleInMap(vehicleAndLocation);
	}

	private void centerVehicleInMap(DtoVehicleAndLocation vehicleAndLocation) {
		MarkerOptions options = MarkerOptions.newInstance();
		LatLng center = LatLng.newInstance(vehicleAndLocation.getLatitude(), vehicleAndLocation.getLongitude());

		options.setIcon(Icon.newInstance("http://cdn1.iconfinder.com/data/icons/STROKE/accounting/png/24/bus.png"));
		options.setTitle(vehicleAndLocation.getVehicle());

		Marker vehicle = new Marker(center, options);
		mapLocation.clearOverlays();
		mapLocation.addOverlay(vehicle);
		mapLocation.setCenter(center);
		mapLocation.setZoomLevel(15);
		mapLocation.checkResizeAndCenter();
	}

	protected void applyExtraInfo(DtoExtraInfoEvent extraInfo) {
		updateAboutContainer(extraInfo.getAbout());
		updateVehicleLocationContainer(extraInfo.getVehicleLocation());
		updateContactContainer(extraInfo.getContact());
		updateRecordsContainer(extraInfo.getRecords());
	}

	class MapHandler implements MapDragEndHandler, MapDoubleClickHandler, MapClickHandler {

		@Override
		public void onClick(MapClickEvent event) {
			event.getSender().checkResizeAndCenter();
		}

		@Override
		public void onDoubleClick(MapDoubleClickEvent event) {
			event.getSender().checkResizeAndCenter();
		}

		@Override
		public void onDragEnd(MapDragEndEvent event) {
			event.getSender().checkResizeAndCenter();
		}
	}

	private LayoutContainer getMapTolboxContainer() {
		if (mapNavigationToolBox == null) {
			mapNavigationToolBox = new MapNavigationToolBox();
			mapNavigationToolBox.setSize("318", "20");
			mapNavigationToolBox.addClickFirstListener(new Listener<MapNavigationEvent>() {
				@Override
				public void handleEvent(MapNavigationEvent be) {
				}
			});
			mapNavigationToolBox.addClickNextListener(new Listener<MapNavigationEvent>() {
				@Override
				public void handleEvent(MapNavigationEvent be) {
				}
			});
			mapNavigationToolBox.addClickPreviousListener(new Listener<MapNavigationEvent>() {
				@Override
				public void handleEvent(MapNavigationEvent be) {
				}
			});
			mapNavigationToolBox.addClickLastListener(new Listener<MapNavigationEvent>() {
				@Override
				public void handleEvent(MapNavigationEvent be) {
				}
			});
			mapNavigationToolBox.addSlideChangeListener(new Listener<MapNavigationEvent>() {
				@Override
				public void handleEvent(MapNavigationEvent be) {
				}
			});
		}
		return mapNavigationToolBox;
	}
	//TODO
	//ao clicar no botão:
	//Pergunta?
	// serao exibidos todos os pontos?
	// 
	// Adicionar um marker no mapa.
	//
	// gerar um poligono com os pontos que foram escolhidos.
	
 }
