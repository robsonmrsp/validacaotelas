package br.com.m2msolutions.client.container;

import br.com.m2msolutions.client.images.Images;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.widget.form.HiddenField;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Util;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.Html;

public class CopyOfAttendanceWindow extends LayoutContainer {
	private LayoutContainer leftContainer;
	private LayoutContainer midleContainer;
	private LayoutContainer rightContainer;
	private FieldSet queryContainer;
	private ContentPanelImp eventsContainer;
	private TextField txtfldNewTextfield;
	private TextField txtfldNewTextfield_1;
	private ComboBox cmbxNewCombobox;
	private ListView<DtoEvent> listView;
	private DateField fieldDateFrom;
	private DateField dtfldNewDatefield_1;
	private ContentPanelImp aboutEventContainer;
	private ContentPanelImp vehicleLocationContainer;
	private LayoutContainer mapContainer;
	private ContentPanelImp contactContainer;
	private ContentPanelImp occurrenceRecordsContainer;
	private MapWidget mapLocation;
	private LayoutContainer layoutContainer;
	private LayoutContainer layoutContainer_1;
	private LayoutContainer layoutContainer_2;
	private LayoutContainer layoutContainer_3;
	private Button btnNewButton;

	private XTemplate aboutEventTemplate;
	private XTemplate contactTemplate;
	private XTemplate locationTemplate;

	private Html htmlVehicleLocation;
	private Html htmlAboutEvent;

	public CopyOfAttendanceWindow() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		add(getLeftContainer(), new BorderLayoutData(LayoutRegion.WEST, 305.0f));
		add(getMidleContainer(), new BorderLayoutData(LayoutRegion.CENTER));
		add(getRightContainer(), new BorderLayoutData(LayoutRegion.EAST, 280.0f));

		createTemplates();

	}

	private void createTemplates() {
		aboutEventTemplate = XTemplate.create(createTemplateAboutEvent());
	}

	private void updateAboutEventContainer(DtoAboutEvent aboutEent) {
		aboutEventTemplate.overwrite(htmlAboutEvent.getElement(), Util.getJsObject(aboutEent));
	}

	private LayoutContainer getLeftContainer() {
		if (leftContainer == null) {
			leftContainer = new LayoutContainer();
			leftContainer.setBorders(false);
			leftContainer.setLayout(new RowLayout(Orientation.VERTICAL));
			leftContainer.add(getQueryContainer(), new RowData(-1, 185.0));
			leftContainer.add(getEventsContainer(), new RowData(-1, 285.0));
		}
		return leftContainer;
	}

	private LayoutContainer getMidleContainer() {
		if (midleContainer == null) {
			midleContainer = new LayoutContainer();
			midleContainer.setBorders(false);
			midleContainer.setLayout(new RowLayout(Orientation.VERTICAL));
			midleContainer.add(getAboutEventContainer(), new RowData(-1, 145.0));
			midleContainer.add(getVehicleLocationContainer(), new RowData(-1, 130.0));
			midleContainer.add(getMapContainer(), new RowData(-1, 100.0));
		}
		return midleContainer;
	}

	private LayoutContainer getRightContainer() {
		if (rightContainer == null) {
			rightContainer = new LayoutContainer();
			rightContainer.setBorders(false);
			RowLayout rl_rightContainer = new RowLayout(Orientation.VERTICAL);
			rl_rightContainer.setExtraStyle("border-layout");
			rightContainer.setLayout(rl_rightContainer);
			rightContainer.add(getContactContainer());
			rightContainer.add(getOccurrenceRecordsContainer());
		}
		return rightContainer;
	}

	private FieldSet getQueryContainer() {
		if (queryContainer == null) {
			queryContainer = new FieldSet();
			queryContainer.setHeading("Periodo de inicio do evento");
			queryContainer.setLayout(new RowLayout(Orientation.VERTICAL));
			queryContainer.add(getLayoutContainer());
			queryContainer.add(getLayoutContainer_1());
		}
		return queryContainer;
	}

	private ContentPanelImp getEventsContainer() {
		if (eventsContainer == null) {
			eventsContainer = new ContentPanelImp();
			eventsContainer.setHeading("Eventos");
			eventsContainer.setBorders(false);
			eventsContainer.add(getListView());
		}
		return eventsContainer;
	}

	private TextField getTxtfldNewTextfield() {
		if (txtfldNewTextfield == null) {
			txtfldNewTextfield = new TextField();
			txtfldNewTextfield.setFieldLabel("Protocolo");
		}
		return txtfldNewTextfield;
	}

	private TextField getTxtfldNewTextfield_1() {
		if (txtfldNewTextfield_1 == null) {
			txtfldNewTextfield_1 = new TextField();
			txtfldNewTextfield_1.setFieldLabel("Veículo");
		}
		return txtfldNewTextfield_1;
	}

	private ComboBox getCmbxNewCombobox() {
		if (cmbxNewCombobox == null) {
			cmbxNewCombobox = new ComboBox();
			cmbxNewCombobox.setStore(new ListStore());
			cmbxNewCombobox.setFieldLabel("Operador");
		}
		return cmbxNewCombobox;
	}

	private ListView<DtoEvent> getListView() {
		if (listView == null) {
			listView = new ListView<DtoEvent>(createEventListStore());

			listView.setTemplate(createEventTemplate());

			listView.setSize("-1", "150");
		}
		return listView;
	}

	private String createEventTemplate() {
		StringBuffer template = new StringBuffer();

		template.append("<p>Inicio: {" + DtoEvent.START_TIME + "}</p>");
		template.append("<p>Veículo: {" + DtoEvent.VEHICLE_CODE + "}</p>");
		template.append("<p>Atendente: {" + DtoEvent.OPERATOR + "}</p>");
		template.append("<p>Protocolo: {" + DtoEvent.PROTOCOL + "}</p>");

		return template.toString();
	}

	private ListStore<DtoEvent> createEventListStore() {

		ListStore<DtoEvent> listStore = new ListStore<DtoEvent>();

		return listStore;
	}

	private DateField getFieldDateFrom() {
		if (fieldDateFrom == null) {
			fieldDateFrom = new DateField();
			fieldDateFrom.setFieldLabel("De");
		}
		return fieldDateFrom;
	}

	private DateField getDtfldNewDatefield_1() {
		if (dtfldNewDatefield_1 == null) {
			dtfldNewDatefield_1 = new DateField();
			dtfldNewDatefield_1.setSize("-1", "-1");
			dtfldNewDatefield_1.setFieldLabel("Até");
		}
		return dtfldNewDatefield_1;
	}

	private ContentPanelImp getAboutEventContainer() {
		if (aboutEventContainer == null) {
			aboutEventContainer = new ContentPanelImp();
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
		}
		return vehicleLocationContainer;
	}

	private LayoutContainer getMapContainer() {
		if (mapContainer == null) {
			mapContainer = new LayoutContainer();
			mapContainer.setHeight("200");
			mapContainer.add(getMapPosition());
			mapContainer.setBorders(false);
		}
		return mapContainer;
	}

	private ContentPanelImp getContactContainer() {
		if (contactContainer == null) {
			contactContainer = new ContentPanelImp();
			contactContainer.setHeading("Contato");
			contactContainer.setCollapsible(true);
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
		}
		return occurrenceRecordsContainer;
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

	private Widget getMapPosition() {
		Maps.loadMapsApi("", "2", false, new Runnable() {
			public void run() {

			}
		});
		LatLng cawkerCity = LatLng.newInstance(39.509, -98.434);
		mapLocation = new MapWidget(cawkerCity, 2);
		mapLocation.setSize("100%", "100%");
		mapLocation.addOverlay(new Marker(cawkerCity));
		return mapLocation;

	}

	private LayoutContainer getLayoutContainer() {
		if (layoutContainer == null) {
			layoutContainer = new LayoutContainer();
			layoutContainer.setLayout(new FormLayout());
			layoutContainer.add(getDtfldNewDatefield_1(), new FormData("100%"));
			layoutContainer.add(getFieldDateFrom(), new FormData("100%"));
		}
		return layoutContainer;
	}

	private LayoutContainer getLayoutContainer_1() {
		if (layoutContainer_1 == null) {
			layoutContainer_1 = new LayoutContainer();
			layoutContainer_1.setLayout(new ColumnLayout());
			layoutContainer_1.add(getLayoutContainer_2(), new ColumnData(.5));
			layoutContainer_1.add(getLayoutContainer_3(), new ColumnData(.5));

		}
		return layoutContainer_1;
	}

	private LayoutContainer getLayoutContainer_2() {
		if (layoutContainer_2 == null) {
			layoutContainer_2 = new LayoutContainer();
			FormLayout fl_layoutContainer_2 = new FormLayout();
			fl_layoutContainer_2.setLabelAlign(LabelAlign.TOP);
			layoutContainer_2.setLayout(fl_layoutContainer_2);
			FormData fd_txtfldNewTextfield_1 = new FormData("100%");
			fd_txtfldNewTextfield_1.setMargins(new Margins(0, 5, 0, 0));
			layoutContainer_2.add(getTxtfldNewTextfield_1(), fd_txtfldNewTextfield_1);
			FormData fd_cmbxNewCombobox = new FormData("100%");
			fd_cmbxNewCombobox.setMargins(new Margins(0, 5, 0, 0));
			layoutContainer_2.add(getCmbxNewCombobox(), fd_cmbxNewCombobox);
		}
		return layoutContainer_2;
	}

	private LayoutContainer getLayoutContainer_3() {
		if (layoutContainer_3 == null) {
			layoutContainer_3 = new LayoutContainer();
			FormLayout fl_layoutContainer_3 = new FormLayout();
			fl_layoutContainer_3.setLabelAlign(LabelAlign.TOP);
			layoutContainer_3.setLayout(fl_layoutContainer_3);
			// layoutContainer_3.add(getCmbxNewCombobox(), new FormData("0"));
			FormData fd_txtfldNewTextfield = new FormData("0");
			fd_txtfldNewTextfield.setMargins(new Margins(0, 0, 0, 5));
			layoutContainer_3.add(getTxtfldNewTextfield(), fd_txtfldNewTextfield);
			FormData fd_btnNewButton = new FormData("100%");
			fd_btnNewButton.setMargins(new Margins(20, 0, 0, 60));
			layoutContainer_3.add(getBtnNewButton(), fd_btnNewButton);
		}
		return layoutContainer_3;
	}

	private Button getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new Button("New Button");
		}
		return btnNewButton;
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
					"\t<div id=\"template-about-event\" class=\"about-event\">\r\n\t\t<br> Numero do protocolo: 12345 \r\n\t\t<br>Inicio do evento: 09/07/2012 14:30 \r\n\t\t<br>Inicio do atendimento: 09/07/2012 14:31 \r\n\t\t<br>Tempo de corrido do atendimento: 5m \r\n\t\t<br>Atendente atual: Operador 1 \r\n\t\t<br>Conclusão do atendimento: 14:35\r\n\t</div>");
		}
		return htmlAboutEvent;
	}

	String createTemplateAboutEvent() {
		StringBuffer template = new StringBuffer();
		template.append("<div id=\"template-about-event\" class=\"about-event\">");
		template.append("<br> Numero do protocolo: {" + DtoAboutEvent.PROTOCOL + "} ");
		template.append("<br>Inicio do atendimento: {" + DtoAboutEvent.START_TIME + "} ");
		template.append("<br>Tempo de corrido do atendimento: {" + DtoAboutEvent.DURATION + "} ");
		template.append("<br>Atendente atual: Operador {" + DtoAboutEvent.OPERATOR + "} ");
		template.append("<br>Conclusão do atendimento: {" + DtoAboutEvent.CONSLUSION + "}");
		template.append("</div>");
		return template.toString();
	}
}
