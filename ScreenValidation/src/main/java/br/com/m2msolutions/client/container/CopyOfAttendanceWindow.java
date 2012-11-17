package br.com.m2msolutions.client.container;

import java.util.ArrayList;
import java.util.List;

import br.com.m2msolutions.client.images.Images;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Util;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

public class CopyOfAttendanceWindow extends LayoutContainer {
	private ContentPanelImp queryContainer;
	private ContentPanelImp eventsContainer;
	private TextField<String> textFieldProtocol;
	private TextField<String> textFieldVehicle;
	private ComboBox<DtoOperator> comboOperator;
	// private ListView<DtoEvent> listViewEvents;
	private Grid<DtoEvent> gridEvents;
	private Grid<DtoRecord> gridRecords;
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

	// Grid<DtoEvent> grid;
	// LayoutContainer gridContainer;
	GridCellRenderer<DtoEvent> imageCellRender;
	GridCellRenderer<DtoRecord> imageRecordCellRender;

	private GridCellRenderer<DtoEvent> descriptionCellRender;
	private GridCellRenderer<DtoRecord> descriptionRecordCellRender;

	public CopyOfAttendanceWindow() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		add(getLeftContainer(), new BorderLayoutData(LayoutRegion.WEST, 305.0f));
		add(getMidleContainer(), new BorderLayoutData(LayoutRegion.CENTER));
		add(getRightContainer(), new BorderLayoutData(LayoutRegion.EAST, 280.0f));

		createTemplates();
		addListener(Events.Attach, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {

			}
		});
	}

	private void createTemplates() {
		aboutEventTemplate = XTemplate.create(AttendanceWidGetTemplates.ABOUT_EVENT);
		contactTemplate = XTemplate.create(AttendanceWidGetTemplates.CONTACTS);
		vehicleLocationTemplate = XTemplate.create(AttendanceWidGetTemplates.VEHICLE_LOCATION);
	}

	private void updateAboutEventContainer(DtoAboutEvent about) {
		aboutEventTemplate.overwrite(htmlAboutEvent.getElement(), Util.getJsObject(about));
	}

	private void updateContactContainer(DtoContact contact) {
		contactTemplate.overwrite(htmlContact.getElement(), Util.getJsObject(contact));
	}

	private void updateVehicleLocationContainer(DtoVehicleAndLocation contact) {
		vehicleLocationTemplate.overwrite(htmlVehicleLocation.getElement(), Util.getJsObject(contact));
	}

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
			dateFieldTo.setFieldLabel("De");
		}
		return dateFieldTo;
	}

	private DateField getDateFieldFrom() {
		if (dateFieldFrom == null) {
			dateFieldFrom = new DateField();
			dateFieldFrom.setSize("-1", "-1");
			dateFieldFrom.setFieldLabel("Até");
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
		}
		return buttonSearch;
	}

	private Html getHtmlVehicleLocation() {
		if (htmlVehicleLocation == null) {
			htmlVehicleLocation = new Html(
					"\t<table id=\"template-vehicle-location\", class=\"vehicle-location\">\r\n\t\t<tr>\r\n\t\t\t<td style=\"width: 200px; \">Veículo: 3050</td>\r\n\t\t\t<td style=\"width: 200px; \">Endereço: BR 123</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Linha: SantaCruz</td>\r\n\t\t\t<td>Latitude: 12,345</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Empresa: SantaCruz</td>\r\n\t\t\t<td>Longitude: 22,345</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Proximo ponto: ETUFOR</td>\r\n\t\t</tr>\r\n\t</table>");
		}
		return htmlVehicleLocation;
	}

	private Html getHtmlAboutEvent() {
		if (htmlAboutEvent == null) {
			htmlAboutEvent = new Html(
					"\t<div id=\"template-about-event\" class=\"about-event\">\r\n\t\t<br>Numero do protocolo: 12345 \r\n\t\t<br>Inicio do evento: 09/07/2012 14:30 \r\n\t\t<br>Inicio do atendimento: 09/07/2012 14:31 \r\n\t\t<br>Tempo de corrido do atendimento: 5m \r\n\t\t<br>Atendente atual: Operador 1 \r\n\t\t<br>Conclusão do atendimento: 14:35      \t\t\r\n\t</div>");
		}
		return htmlAboutEvent;
	}

	private Html getHtmlContact() {
		if (htmlContact == null) {
			htmlContact = new Html(
					"\t<div id=\"template-contact\" class=\"contact\">\r\n\t<table>\r\n\t\t<tr>\t\r\n\t\t\t<td> <img id=\"image-message\" src=\"http://cdn1.iconfinder.com/data/icons/humano2/72x72/emblems/emblem-people.png\" </td>\r\n\t\t\t<td>\r\n\t\t\t\t <div id=\"record\" class=\"record\">\t\t\t \r\n\t\t\t\t\t<br> Nome: Antonio Firmulano da Cunha Matos \r\n\t\t\t\t\t<br>Matricula:  097286358 \r\n\t\t\t\t\t<br>Telefone: 3354-7689 \r\n\t\t\t\t\t<br>Tempo de corrido do atendimento: 5m \r\n\t\t\t\t\t<br>Idade: 43 anos \r\n\t\t\t\t </div> \r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t</table>\r\n\t</div>\r\n");
		}
		return htmlContact;
	}

	private ListStore<DtoEvent> createEventListStore() {

		ListStore<DtoEvent> listStore = new ListStore<DtoEvent>();

		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "120", "22/10/2012 12:01"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "121", "22/10/2012 12:02"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "122", "22/10/2012 12:04"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "123", "22/10/2012 12:05"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "124", "22/10/2012 12:02"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "125", "22/10/2012 12:14"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "126", "22/10/2012 12:06"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "127", "22/10/2012 12:07"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "128", "22/10/2012 12:08"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "129", "22/10/2012 12:09"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "133", "22/10/2012 12:10"));

		return listStore;
	}

	private Grid<DtoRecord> getGridRecords() {
		if (gridRecords == null) {
			gridRecords = new Grid<DtoRecord>(createRecordListStory(), createRecordColumnConfig());
			gridRecords.setSize("-1", "290");
			gridRecords.setMinColumnWidth(150);
			gridRecords.setAutoExpandMin(150);
			gridRecords.setAutoExpandMax(1000);
			gridRecords.setBorders(false);
			gridRecords.setId("gridRecords");
			gridRecords.setAutoWidth(true);
			gridRecords.setColumnResize(true);
			gridRecords.setWidth(Style.DEFAULT);
			gridRecords.setHideHeaders(true);
			gridRecords.setHeight(Style.DEFAULT);
			gridRecords.setAutoExpandColumn(DtoRecord.DESCRIPTION);
		}
		return gridRecords;
	}

	private Grid<DtoEvent> getGridEvents() {
		if (gridEvents == null) {
			gridEvents = new Grid<DtoEvent>(createListStory(), createColumnConfig());
			gridEvents.setSize("-1", "290");
			gridEvents.setMinColumnWidth(150);
			gridEvents.setAutoExpandMin(150);
			gridEvents.setAutoExpandMax(1000);
			gridEvents.setBorders(false);
			gridEvents.setId("gridEvents");
			gridEvents.setAutoWidth(true);
			gridEvents.setColumnResize(true);
			gridEvents.setWidth(Style.DEFAULT);
			gridEvents.setHideHeaders(true);
			gridEvents.setHeight(Style.DEFAULT);
			gridEvents.setAutoExpandColumn(DtoEvent.DESCRIPTION);

		}
		return gridEvents;
	}

	private ContentPanelImp getEventsContainer() {
		if (eventsContainer == null) {
			eventsContainer = new ContentPanelImp();
			eventsContainer.setHeading("Eventos");
			eventsContainer.setBorders(false);
			// eventsContainer.setSize("100%", "100%");
			eventsContainer.setLayout(new FitLayout());
			eventsContainer.add(getGridEvents());
		}
		return eventsContainer;
	}

	private ContentPanelImp getAboutEventContainer() {
		if (aboutEventContainer == null) {
			aboutEventContainer = new ContentPanelImp();
			aboutEventContainer.setHeight("150");
			aboutEventContainer.setHeading("Sobre o Evento");
			aboutEventContainer.setCollapsible(true);
			aboutEventContainer.add(getHtmlAboutEvent());
		}
		return aboutEventContainer;
	}

	private ContentPanelImp getVehicleLocationContainer() {
		if (vehicleLocationContainer == null) {
			vehicleLocationContainer = new ContentPanelImp();
			vehicleLocationContainer.setHeading("Veículo e Localização");
			vehicleLocationContainer.setCollapsible(true);
			vehicleLocationContainer.add(getHtmlVehicleLocation());
			vehicleLocationContainer.add(getMapContainer());
		}
		return vehicleLocationContainer;
	}

	private ContentPanelImp getContactContainer() {
		if (contactContainer == null) {
			contactContainer = new ContentPanelImp();
			contactContainer.setHeading("Contato");
			contactContainer.setCollapsible(true);
			contactContainer.add(getHtmlContact());
		}
		return contactContainer;
	}

	private ContentPanelImp getOccurrenceRecordsContainer() {
		if (occurrenceRecordsContainer == null) {
			occurrenceRecordsContainer = new ContentPanelImp();
			occurrenceRecordsContainer.setHeading("Registro de ocorrência");
			occurrenceRecordsContainer.addToolButton(createPrinterButton());
			occurrenceRecordsContainer.addToolButton(createEditButton());
			occurrenceRecordsContainer.addToolButton(createPrinterButton());
			occurrenceRecordsContainer.setCollapsible(true);
			occurrenceRecordsContainer.setLayout(new FitLayout());
			occurrenceRecordsContainer.add(getGridRecords());
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
		LatLng cawkerCity = LatLng.newInstance(39.509, -98.434);
		mapLocation = new MapWidget(cawkerCity, 2);
		mapLocation.setSize("100%", "100%");
		mapLocation.addOverlay(new Marker(cawkerCity));
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
			midleContainer.setLayout(new RowLayout(Orientation.VERTICAL));
			midleContainer.add(getAboutEventContainer(), new RowData(-1, 150.0));
			midleContainer.add(getVehicleLocationContainer());
			// midleContainer.add(getMapContainer(), new RowData(-1, 250.0));
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

	ArrayList<DtoEvent> testeListe() {
		ArrayList<DtoEvent> listStore = new ArrayList<DtoEvent>();
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "120", "22/10/2012 12:01"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "121", "22/10/2012 12:02"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "122", "22/10/2012 12:04"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "123", "22/10/2012 12:05"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "124", "22/10/2012 12:02"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "125", "22/10/2012 12:14"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "126", "22/10/2012 12:06"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "127", "22/10/2012 12:07"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "128", "22/10/2012 12:08"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "129", "22/10/2012 12:09"));
		listStore.add(new DtoEvent("https://www.leadingtheserviceindustry.com/images/cancel_icon.png", "133", "22/10/2012 12:10"));
		return listStore;
	}

	private ListStore<DtoRecord> createRecordListStory() {
		ListStore<DtoRecord> listStore = new ListStore<DtoRecord>();

		listStore.add(new DtoRecord(Images.INSTANCE.driver14().getSafeUri().asString(), "26/10/2012 12:30", "Olá tudo bem?"));
		listStore.add(new DtoRecord(Images.INSTANCE.driver14().getSafeUri().asString(), "26/10/2012 12:31", "Posso ir almoçar?"));
		listStore.add(new DtoRecord(Images.INSTANCE.vehicle16().getSafeUri().asString(), "26/10/2012 12:32", "Claro que sim?"));
		listStore.add(new DtoRecord(Images.INSTANCE.vehicle16().getSafeUri().asString(), "26/10/2012 12:32", "Tá na hora mesmo?"));

		return listStore;
	}

	private ListStore<DtoEvent> createListStory() {
		ListStore<DtoEvent> listStore = new ListStore<DtoEvent>();

		listStore.add(new DtoEvent(Images.INSTANCE.pane24().getSafeUri().asString(), "3050 -  (CCO BRT) - 1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.alert24().getSafeUri().asString(), "3050 - (CCO BRT) - 1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.pane24().getSafeUri().asString(), "3050 - (CCO BRT) - 1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.message24().getSafeUri().asString(), "3050 - (Operador 1) 1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.pane24().getSafeUri().asString(), "3050 -  1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.alert24().getSafeUri().asString(), "3050 -  1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.pane24().getSafeUri().asString(), "3050 -  1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.message24().getSafeUri().asString(), "3050 -  1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.pane24().getSafeUri().asString(), "3050 -  1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.alert24().getSafeUri().asString(), "3050 -  1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.pane24().getSafeUri().asString(), "3050 -  1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.message24().getSafeUri().asString(), "3050 -  1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.alert24().getSafeUri().asString(), "3050 -  1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.message24().getSafeUri().asString(), "3050 -  1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.alert24().getSafeUri().asString(), "3850 -  9 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.pane24().getSafeUri().asString(), "3050 -  1 min "));
		listStore.add(new DtoEvent(Images.INSTANCE.pane24().getSafeUri().asString(), "3050 -  1 min "));
		return listStore;
	}

	private ColumnModel createRecordColumnConfig() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId(DtoRecord.IMAGE_SRC);
		column.setWidth(35);
		column.setRenderer(createRecordImageCellRender());
		configs.add(column);

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

	private GridCellRenderer<DtoEvent> createImageCellRender() {
		imageCellRender = new GridCellRenderer<DtoEvent>() {

			@Override
			public Object render(DtoEvent model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore<DtoEvent> store, Grid<DtoEvent> grid) {
				return "<span><img src=\"" + model.getImageSrc() + "\" alt=\"image\">  </span>";
			}
		};
		return imageCellRender;
	}

	@SuppressWarnings("unused")
	private GridCellRenderer<DtoEvent> createDescriptionCellRender() {
		descriptionCellRender = new GridCellRenderer<DtoEvent>() {
			@Override
			public Object render(DtoEvent model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore<DtoEvent> store, Grid<DtoEvent> grid) {
				// return "<span style=\"padding-top: 10px;\"> " +
				// model.getDescription() + "  </span>";
				return getRendered(model);
			}
		};
		return descriptionCellRender;
	}

	private GridCellRenderer<DtoRecord> createRecordDescriptionCellRender() {
		descriptionRecordCellRender = new GridCellRenderer<DtoRecord>() {
			@Override
			public Object render(DtoRecord model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore<DtoRecord> store, Grid<DtoRecord> grid) {
				return getRecordRendered(model);
			}
		};
		return descriptionRecordCellRender;
	}

	protected Object getRecordRendered(DtoRecord model) {
		StringBuffer rendered = new StringBuffer();
		rendered.append("<div id=\"template-record-messages\" class=\"record-messages\">");
		rendered.append("	<table>");
		rendered.append("		<tr>");
		rendered.append("			<td>");
		rendered.append("				 <div id=\"record\" class=\"record\">");
		rendered.append("				 	 [ " + model.getMessageDate() + " ] " + model.getMessage());
		rendered.append("				 </div> ");
		rendered.append("			</td>");
		rendered.append("		</tr>");
		rendered.append("	</table>");
		rendered.append("</div>");

		return rendered.toString();
	}

	protected String getRendered(DtoEvent model) {
		StringBuffer rendered = new StringBuffer();
		rendered.append("<div id=\"template-events\" class=\"events\">");
		rendered.append("	<table>");
		rendered.append("		<tr>");
		rendered.append("			<td> inicio: " + model.getStartTime() + " </td>");
		rendered.append("			<td> Veiculo: " + model.getVehicleCode() + "</td>");
		rendered.append("		</tr>");
		rendered.append("		<tr>");
		rendered.append("			<td> Atendente: " + model.getOperator() + "</td>");
		rendered.append("			<td> Protocolo: " + model.getProtocol() + "</td>");
		rendered.append("		</tr>");
		rendered.append("	</table>");
		rendered.append("</div>");
		return rendered.toString();
	}
}
