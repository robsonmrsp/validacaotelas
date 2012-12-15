package br.com.m2msolutions.client.container;

import java.util.ArrayList;

import br.com.m2msolutions.client.InquiryAttendanceService;
import br.com.m2msolutions.client.InquiryAttendanceServiceAsync;
import br.com.m2msolutions.client.SimpleGwtLogger;
import br.com.m2msolutions.client.images.Images;
import br.com.m2msolutions.shared.dto.DtoAboutEvent;
import br.com.m2msolutions.shared.dto.DtoContact;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoRecord;
import br.com.m2msolutions.shared.dto.DtoVehicleAndLocation;
import br.com.mr.dock.client.containers.Position;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Util;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
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
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Widget;

public class PanelCriticalEventsAttendance extends LayoutContainer {
	private Button btnNewButton;
	private Button btnResolvido;
	private ContentPanel cpHistoricoChat;
	private ContentPanelImp cpEntradaChat;
	private ListView<DtoRecord> listView;
	private ContentPanelImp cpChatEntradaLeft;
	private ContentPanelImp cpChatEntraRigth;
	private ContentPanelImp cpEntradaChatTop;
	private ContentPanelImp cpEntradaChatBottom;
	private TextArea taEntradaChat;
	private Button btRegistrar;
	private CheckBox cbParaOVeculo;
	private ToolBar toolBar;
	private LabelToolItem lbPainelLeds;
	private Button btGreenLed;
	private Button btBlueLed;
	private Button btYallowLed;
	private Button btRedLed;

	private MapWidget mapLocation;
	MapHandler mapHandler = new MapHandler();
	private ContentPanelImp aboutEventContainer;
	private ContentPanelImp vehicleLocationContainer;
	private ContentPanelImp contactContainer;
	private ContentPanelImp occurrenceRecordsContainer;

	private XTemplate aboutEventTemplate;
	private XTemplate contactTemplate;
	private XTemplate vehicleLocationTemplate;

	private Html htmlVehicleLocation;
	private Html htmlAboutEvent;
	private Html htmlContact;
	private DtoCriticalEvent event;
	private SlidePanel panel = new SlidePanel();

	InquiryAttendanceServiceAsync attendanceService = GWT.create(InquiryAttendanceService.class);

	public PanelCriticalEventsAttendance() {
		initComponents();
	}

	private void initComponents() {
		setBorders(true);
		setLayout(new BorderLayout());

		BorderLayoutData centerLayoutData = new BorderLayoutData(LayoutRegion.CENTER);
		centerLayoutData.setMargins(new Margins(5, 2, 5, 5));

		BorderLayoutData eastLayoutData = new BorderLayoutData(LayoutRegion.EAST, 410f);
		eastLayoutData.setMargins(new Margins(5, 5, 5, 2));

		add(getCenterContainer(), centerLayoutData);
		add(getEastContainer(), eastLayoutData);
		addListener(Events.Attach, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (event != null)
					findExtraInfoEvent(event);
			}
		});
		addListener(Events.Resize, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				mapLocation.checkResizeAndCenter();
			}
		});

		createTemplates();
	}

	private Widget getEastContainer() {
		LayoutContainer eastContainer = new LayoutContainer();
		eastContainer.setBorders(true);
		eastContainer.setLayout(new RowLayout(Orientation.VERTICAL));
		eastContainer.add(getContactContainer(), new RowData(Style.DEFAULT, Style.DEFAULT, new Margins(0, 0, 0, 0)));
		eastContainer.add(getOccurrenceRecordsContainer(), new RowData(Style.DEFAULT, 1.0, new Margins()));
		return eastContainer;
	}

	private Widget getCenterContainer() {
		LayoutContainer centerContainer = new LayoutContainer();
		centerContainer.setBorders(true);
		centerContainer.setLayout(new BorderLayout());
		centerContainer.add(getAboutEventContainer(), new BorderLayoutData(LayoutRegion.NORTH, 150.0f));
		centerContainer.add(getVehicleLocationContainer(), new BorderLayoutData(LayoutRegion.CENTER));
		return centerContainer;
	}

	private Widget getOccurrenceRecordsContainer() {
		if (occurrenceRecordsContainer == null) {
			occurrenceRecordsContainer = new ContentPanelImp();
			occurrenceRecordsContainer.setBodyStyle("backgroundColor: #d6e2f6;");
			occurrenceRecordsContainer.setBodyBorder(false);
			occurrenceRecordsContainer.setHeading("Registro de Ocorrencia:");

			ToolButton toolButton = new ToolButton(Images.INSTANCE.edit16());

			panel.anchorAt(occurrenceRecordsContainer, Position.LEFT);
			occurrenceRecordsContainer.addToolButton(new ToolButton(Images.INSTANCE.edit16(), new Listener<BaseEvent>() {
				@Override
				public void handleEvent(BaseEvent be) {
					if (panel.isVisible()) {
						panel.hide();
					} else {
						panel.show();
					}
				}
			}));
			occurrenceRecordsContainer.setLayout(new RowLayout(Orientation.VERTICAL));
			occurrenceRecordsContainer.add(getCpHistoricoChat(), new RowData(Style.DEFAULT, 1.0, new Margins(5, 5, 5, 5)));
			occurrenceRecordsContainer.add(getCpEntradaChat(), new RowData(Style.DEFAULT, Style.DEFAULT, new Margins(5, 5, 5, 5)));
		}
		return occurrenceRecordsContainer;
	}

	private Widget getContactContainer() {
		if (contactContainer == null) {
			contactContainer = new ContentPanelImp();
			contactContainer.setBodyBorder(false);
			contactContainer.addToolButton(new ToolButton(Images.INSTANCE.edit16(), new Listener<BaseEvent>() {
				@Override
				public void handleEvent(BaseEvent be) {
					Window.alert("Editando contato...");
				}
			}));
			contactContainer.setHeading("Contato:");
			contactContainer.setLayout(new FitLayout());
			FitData fd_htmlContact = new FitData(0);
			fd_htmlContact.setMargins(new Margins(0, 6, 6, 6));
			contactContainer.add(getHtmlContact(), fd_htmlContact);
			contactContainer.setHeight("150");
		}
		return contactContainer;
	}

	private Widget getVehicleLocationContainer() {
		if (vehicleLocationContainer == null) {
			vehicleLocationContainer = new ContentPanelImp();
			vehicleLocationContainer.setBodyBorder(false);
			vehicleLocationContainer.setHeading("Ve\u00EDculo e localiza\u00E7\u00E3o:");
			vehicleLocationContainer.setLayout(new BorderLayout());
			BorderLayoutData bld_htmlVehicleLocation = new BorderLayoutData(LayoutRegion.NORTH, 100.0f);
			bld_htmlVehicleLocation.setMinSize(100);
			bld_htmlVehicleLocation.setMaxSize(100);

			vehicleLocationContainer.add(getHtmlVehicleLocation(), bld_htmlVehicleLocation);
			vehicleLocationContainer.add(getMapContainer(), new BorderLayoutData(LayoutRegion.CENTER));

		}
		return vehicleLocationContainer;
	}

	private LayoutContainer getMapContainer() {
		LayoutContainer mapContainer = new LayoutContainer();

		mapContainer.setHeight("400");
		mapContainer.add(getMapPosition());
		mapContainer.setBorders(true);

		return mapContainer;
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

	private Widget getAboutEventContainer() {
		if (aboutEventContainer == null) {
			aboutEventContainer = new ContentPanelImp();
			aboutEventContainer.setBodyBorder(false);
			aboutEventContainer.setHeading("Sobre o Evento:");
			aboutEventContainer.setLayout(new FitLayout());
			aboutEventContainer.add(getHtmlAboutEvent());
			aboutEventContainer.addToolButton(new ToolButton(Images.INSTANCE.transferir16(), new Listener<BaseEvent>() {
				@Override
				public void handleEvent(BaseEvent be) {
					Window.alert("transferindo atendimento...");
				}
			}));
			aboutEventContainer.addToolButton(new ToolButton(Images.INSTANCE.resolvido16(), new Listener<BaseEvent>() {
				@Override
				public void handleEvent(BaseEvent be) {
					Window.alert("Atendimento resolvido/encerrado...");
				}
			}));
			aboutEventContainer.setHeight("180");
		}
		return aboutEventContainer;
	}

	private Html getHtmlVehicleLocation() {
		if (htmlVehicleLocation == null) {
			htmlVehicleLocation = new Html();
			htmlVehicleLocation.setSize("-1", "100");
			// "\t<table id=\"template-vehicle-location\", class=\"vehicle-location\">\r\n\t\t<tr>\r\n\t\t\t<td style=\"width: 200px; \">Veículo: 3050</td>\r\n\t\t\t<td style=\"width: 200px; \">Endereço: BR 123</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Linha: SantaCruz</td>\r\n\t\t\t<td>Latitude: 12,345</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Empresa: SantaCruz</td>\r\n\t\t\t<td>Longitude: 22,345</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Proximo ponto: ETUFOR</td>\r\n\t\t</tr>\r\n\t</table>");
		}
		return htmlVehicleLocation;
	}

	private void createTemplates() {
		aboutEventTemplate = XTemplate.create(AttendanceWidGetTemplates.ABOUT_EVENT);
		contactTemplate = XTemplate.create(AttendanceWidGetTemplates.CONTACTS);
		vehicleLocationTemplate = XTemplate.create(AttendanceWidGetTemplates.VEHICLE_LOCATION);

		htmlAboutEvent.setHtml(aboutEventTemplate.applyTemplate(Util.getJsObject(new BaseModelData())));
		htmlContact.setHtml(contactTemplate.applyTemplate(Util.getJsObject(new BaseModelData())));
		htmlVehicleLocation.setHtml(vehicleLocationTemplate.applyTemplate(Util.getJsObject(new BaseModelData())));

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

	private Button getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new Button("Transferir");
			btnNewButton.setSize("75px", "19px");
			btnNewButton.setIcon(AbstractImagePrototype.create(Images.INSTANCE.transferir16()));
		}
		return btnNewButton;
	}

	private Button getBtnResolvido() {
		if (btnResolvido == null) {
			btnResolvido = new Button("Resolvido");
			btnResolvido.setSize("75px", "17px");
			btnResolvido.setIcon(AbstractImagePrototype.create(Images.INSTANCE.resolvido16()));
		}
		return btnResolvido;
	}

	private ContentPanel getCpHistoricoChat() {
		if (cpHistoricoChat == null) {
			cpHistoricoChat = new ContentPanel();
			cpHistoricoChat.setLayout(new FitLayout());
			cpHistoricoChat.setBodyBorder(false);
			cpHistoricoChat.setHeaderVisible(false);
			cpHistoricoChat.setCollapsible(true);
			cpHistoricoChat.add(getListView());
		}
		return cpHistoricoChat;
	}

	private ContentPanel getCpEntradaChat() {
		if (cpEntradaChat == null) {
			cpEntradaChat = new ContentPanelImp();
			cpEntradaChat.setBodyBorder(false);
			cpEntradaChat.setSize("", "100px");
			cpEntradaChat.setHeaderVisible(false);
			cpEntradaChat.setCollapsible(true);
			cpEntradaChat.setLayout(new RowLayout(Orientation.HORIZONTAL));
			getCpChatEntradaLeft().setLayout(new RowLayout(Orientation.VERTICAL));
			cpEntradaChat.add(getCpChatEntradaLeft(), new RowData(1.0, 1.0, new Margins()));
			getCpChatEntraRigth().setLayout(new AbsoluteLayout());
			cpEntradaChat.add(getCpChatEntraRigth(), new RowData(Style.DEFAULT, 1.0, new Margins()));
		}
		return cpEntradaChat;
	}

	private ListView<DtoRecord> getListView() {
		if (listView == null) {
			listView = new ListView<DtoRecord>(new ListStore<DtoRecord>());
		}
		return listView;
	}

	private ContentPanel getCpChatEntradaLeft() {
		if (cpChatEntradaLeft == null) {
			cpChatEntradaLeft = new ContentPanelImp();
			cpChatEntradaLeft.setBodyBorder(false);
			cpChatEntradaLeft.setHeaderVisible(false);
			cpChatEntradaLeft.setCollapsible(true);
			cpChatEntradaLeft.add(getCpEntradaChatTop());
			getCpEntradaChatBottom().setLayout(new FitLayout());
			cpChatEntradaLeft.add(getCpEntradaChatBottom(), new RowData(Style.DEFAULT, 1.0, new Margins()));
		}
		return cpChatEntradaLeft;
	}

	private ContentPanel getCpChatEntraRigth() {
		if (cpChatEntraRigth == null) {
			cpChatEntraRigth = new ContentPanelImp();
			cpChatEntraRigth.setBodyStyle("backgroundColor: #d6e2f6;");
			cpChatEntraRigth.setBodyBorder(false);
			cpChatEntraRigth.setHeaderVisible(false);
			cpChatEntraRigth.setWidth("120px");
			cpChatEntraRigth.setCollapsible(true);
			cpChatEntraRigth.add(getBtRegistrar(), new AbsoluteData(6, 72));
			cpChatEntraRigth.add(getCbParaOVeculo(), new AbsoluteData(6, 44));
		}
		return cpChatEntraRigth;
	}

	private ContentPanel getCpEntradaChatTop() {
		if (cpEntradaChatTop == null) {
			cpEntradaChatTop = new ContentPanelImp();
			cpEntradaChatTop.setHeaderVisible(false);
			cpEntradaChatTop.setBodyBorder(false);
			cpEntradaChatTop.setSize("", "");
			cpEntradaChatTop.setCollapsible(true);
			cpEntradaChatTop.setLayout(new AbsoluteLayout());
			cpEntradaChatTop.setTopComponent(getToolBar());
		}
		return cpEntradaChatTop;
	}

	private ContentPanel getCpEntradaChatBottom() {
		if (cpEntradaChatBottom == null) {
			cpEntradaChatBottom = new ContentPanelImp();
			cpEntradaChatBottom.setHeaderVisible(false);
			cpEntradaChatBottom.setBodyBorder(false);
			cpEntradaChatBottom.setCollapsible(true);
			cpEntradaChatBottom.add(getTaEntradaChat());
		}
		return cpEntradaChatBottom;
	}

	private TextArea getTaEntradaChat() {
		if (taEntradaChat == null) {
			taEntradaChat = new TextArea();
			taEntradaChat.setEmptyText("Digite uma mensagem");
		}
		return taEntradaChat;
	}

	private Button getBtRegistrar() {
		if (btRegistrar == null) {
			btRegistrar = new Button("Registrar");
			btRegistrar.addSelectionListener(new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
				}
			});
			btRegistrar.setWidth("100");
		}
		return btRegistrar;
	}

	private CheckBox getCbParaOVeculo() {
		if (cbParaOVeculo == null) {
			cbParaOVeculo = new CheckBox();
			cbParaOVeculo.setBoxLabel("Para o Ve\u00EDculo");
			cbParaOVeculo.setHideLabel(true);
		}
		return cbParaOVeculo;
	}

	private ToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new ToolBar();
			toolBar.setBorders(true);
			toolBar.add(getLbPainelLeds());
			toolBar.add(getBtGreenLed());
			toolBar.add(getBtBlueLed());
			toolBar.add(getBtYallowLed());
			toolBar.add(getBtRedLed());
		}
		return toolBar;
	}

	private LabelToolItem getLbPainelLeds() {
		if (lbPainelLeds == null) {
			lbPainelLeds = new LabelToolItem("Painel de Leds :");
		}
		return lbPainelLeds;
	}

	private Button getBtGreenLed() {
		if (btGreenLed == null) {
			btGreenLed = new Button("");
			btGreenLed.setIconAlign(IconAlign.RIGHT);
			btGreenLed.setWidth("30px");
			btGreenLed.setIcon(AbstractImagePrototype.create(Images.INSTANCE.hardGreen30()));
		}
		return btGreenLed;
	}

	private Button getBtBlueLed() {
		if (btBlueLed == null) {
			btBlueLed = new Button("");
			btBlueLed.setWidth("30px");
			btBlueLed.setIconAlign(IconAlign.RIGHT);
			btBlueLed.setIcon(AbstractImagePrototype.create(Images.INSTANCE.hardBlue30()));
		}
		return btBlueLed;
	}

	private Button getBtYallowLed() {
		if (btYallowLed == null) {
			btYallowLed = new Button("");
			btYallowLed.setWidth("30px");
			btYallowLed.setIconAlign(IconAlign.RIGHT);
			btYallowLed.setIcon(AbstractImagePrototype.create(Images.INSTANCE.hardYellow30()));
		}
		return btYallowLed;
	}

	private Button getBtRedLed() {
		if (btRedLed == null) {
			btRedLed = new Button("");
			btRedLed.setWidth("30px");
			btRedLed.setIconAlign(IconAlign.RIGHT);
			btRedLed.setIcon(AbstractImagePrototype.create(Images.INSTANCE.hardRed30()));
		}
		return btRedLed;
	}

	// private Widget getMapPosition() {
	//
	// LatLng fortalCity = LatLng.newInstance(-3.736549, -38.523804);
	// mapLocation = new MapWidget();
	//
	// mapLocation.setCenter(fortalCity);
	// mapLocation.setSize("100%", "100%");
	// mapLocation.addOverlay(new Marker(fortalCity));
	// mapLocation.setZoomLevel(14);
	// mapLocation.checkResizeAndCenter();
	// mapLocation.addMapDoubleClickHandler(mapHandler);
	// mapLocation.addMapDragEndHandler(mapHandler);
	// mapLocation.addMapClickHandler(mapHandler);
	//
	// return mapLocation;
	// }

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

	public void setEvent(DtoCriticalEvent dtoEvent) {
		this.event = dtoEvent;
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

	protected void applyExtraInfo(DtoExtraInfoEvent extraInfo) {
		updateAboutContainer(extraInfo.getAbout());
		updateVehicleLocationContainer(extraInfo.getVehicleLocation());
		updateContactContainer(extraInfo.getContact());
		updateRecordsContainer(extraInfo.getRecords());
	}

	private void updateRecordsContainer(ArrayList<DtoRecord> records) {
		listView.getStore().removeAll();
		listView.getStore().add(records);
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
}
