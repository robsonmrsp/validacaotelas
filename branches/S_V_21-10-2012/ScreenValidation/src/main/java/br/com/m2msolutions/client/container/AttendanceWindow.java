package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.Widget;

public class AttendanceWindow extends LayoutContainer {
	private LayoutContainer leftContainer;
	private LayoutContainer midleContainer;
	private LayoutContainer rightContainer;
	private FormPanel queryContainer;
	private FieldSet eventsContainer;
	private TextField txtfldNewTextfield;
	private TextField txtfldNewTextfield_1;
	private ComboBox cmbxNewCombobox;
	private Button btnNewButton;
	private ListView listView;
	private DateField fieldDateFrom;
	private DateField dtfldNewDatefield_1;
	private FieldSet aboutEventContainer;
	private FieldSet vehicleLocationContainer;
	private LayoutContainer mapContainer;
	private FieldSet contactContainer;
	private FieldSet occurrenceRecordsContainer;
	private MapWidget mapLocation;

	public AttendanceWindow() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 255.0f);
		add(getLeftContainer(), westData);
		add(getMidleContainer(), new BorderLayoutData(LayoutRegion.CENTER));
		getRightContainer().setLayout(new RowLayout(Orientation.VERTICAL));
		add(getRightContainer(), new BorderLayoutData(LayoutRegion.EAST));
	}

	private LayoutContainer getLeftContainer() {
		if (leftContainer == null) {
			leftContainer = new LayoutContainer();
			leftContainer.setBorders(true);
			leftContainer.setLayout(new RowLayout(Orientation.VERTICAL));
			leftContainer.add(getQueryContainer(), new RowData(-1, 200.0));
			leftContainer.add(getEventsContainer(), new RowData(-1, 200.0));
		}
		return leftContainer;
	}

	private LayoutContainer getMidleContainer() {
		if (midleContainer == null) {
			midleContainer = new LayoutContainer();
			midleContainer.setBorders(true);
			midleContainer.setLayout(new RowLayout(Orientation.VERTICAL));
			midleContainer.add(getAboutEventContainer(), new RowData(-1, 200));
			midleContainer.add(getVehicleLocationContainer(), new RowData(-1, 130.0));
			midleContainer.add(getMapContainer(), new RowData(-1, 100.0));
		}
		return midleContainer;
	}

	private LayoutContainer getRightContainer() {
		if (rightContainer == null) {
			rightContainer = new LayoutContainer();
			rightContainer.setBorders(true);
			rightContainer.add(getContactContainer());
			rightContainer.add(getOccurrenceRecordsContainer());
		}
		return rightContainer;
	}

	private FormPanel getQueryContainer() {
		if (queryContainer == null) {
			queryContainer = new FormPanel();

			queryContainer.setHeaderVisible(false);
			queryContainer.add(getFieldDateFrom(), new FormData("100%"));
			queryContainer.add(getDtfldNewDatefield_1(), new FormData("100%"));
			queryContainer.add(getTxtfldNewTextfield(), new FormData("100%"));
			queryContainer.add(getTxtfldNewTextfield_1(), new FormData("100%"));
			queryContainer.add(getCmbxNewCombobox(), new FormData("100%"));
			// queryContainer.setLabelWidth(55);

			queryContainer.addButton(getBtnNewButton());
		}
		return queryContainer;
	}

	private FieldSet getEventsContainer() {
		if (eventsContainer == null) {
			eventsContainer = new FieldSet();
			eventsContainer.setHeading("Eventos");
			eventsContainer.setBorders(true);
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

	private Button getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new Button("New Button");
		}
		return btnNewButton;
	}

	private ListView getListView() {
		if (listView == null) {
			listView = new ListView(new ListStore());
			listView.setSize("-1", "150");
		}
		return listView;
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

	private FieldSet getAboutEventContainer() {
		if (aboutEventContainer == null) {
			aboutEventContainer = new FieldSet();
			aboutEventContainer.setHeading("Sobre o Evento");
			aboutEventContainer.setCollapsible(true);
		}
		return aboutEventContainer;
	}

	private FieldSet getVehicleLocationContainer() {
		if (vehicleLocationContainer == null) {
			vehicleLocationContainer = new FieldSet();
			vehicleLocationContainer.setHeading("Veículo e Localização");
			vehicleLocationContainer.setCollapsible(true);
		}
		return vehicleLocationContainer;
	}

	private LayoutContainer getMapContainer() {
		if (mapContainer == null) {
			mapContainer = new LayoutContainer();
			mapContainer.add(getMapPosition());
			mapContainer.setBorders(true);
		}
		return mapContainer;
	}

	private FieldSet getContactContainer() {
		if (contactContainer == null) {
			contactContainer = new FieldSet();
			contactContainer.setHeading("Contato");
			contactContainer.setCollapsible(true);
		}
		return contactContainer;
	}

	private FieldSet getOccurrenceRecordsContainer() {
		if (occurrenceRecordsContainer == null) {
			occurrenceRecordsContainer = new FieldSet();
			occurrenceRecordsContainer.setHeading("Registro de ocorrência");
			occurrenceRecordsContainer.setCollapsible(true);
		}
		return occurrenceRecordsContainer;
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
}
