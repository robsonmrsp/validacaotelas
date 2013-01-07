package br.com.m2msolutions.client.container;

import java.util.ArrayList;
import java.util.List;

import br.com.m2msolutions.client.CriticalEventsAttendanceService;
import br.com.m2msolutions.client.CriticalEventsAttendanceServiceAsync;
import br.com.m2msolutions.client.DialogWindowTransfer;
import br.com.m2msolutions.client.SimpleGwtLogger;
import br.com.m2msolutions.client.WindowContactResearch;
import br.com.m2msolutions.client.images.Images;
import br.com.m2msolutions.shared.dto.DtoAboutEvent;
import br.com.m2msolutions.shared.dto.DtoContact;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoOperator;
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
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapDoubleClickHandler;
import com.google.gwt.maps.client.event.MapDragEndHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Hyperlink;
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
	private MapHandler mapHandler = new MapHandler();
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
	private SlidePanel slidePanel = new SlidePanel();
	private DialogWindowTransfer dialogTransfer;

	private ContentPanel ctpSobreEvento;
	private Text txtNewText;
	private Text txtIncioDoEvento;
	private Text txtIncioDoAtendimento;
	private Text txtTempoDecorridoDo;
	private Text txtAtendenteAtual;
	private Text txtConclusoDoAtendimento;
	private Text txtNumeroProtocoloValue;
	private Text txtIniEventoValue;
	private Text txtIniAtendimentoValue;
	private Text txtTempoAtendimentoValue;
	private Text txtAtendenteValue;
	private Hyperlink hprlnkTransferir;
	private Text txtConclusaoValue;
	private Hyperlink hprlnkResolvido;

	private CriticalEventsAttendanceServiceAsync eventsAttendanceService = CriticalEventsAttendanceService.Util.getInstance();

	// TODO fazer com que a tela receba o evento ao ser chamada. Não faz sentido
	// atender a um evento que não se conhece. Talvez adicionar o parametro ao
	// construtor.

	private DtoCriticalEvent actualEvent;

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

			slidePanel.anchorAt(occurrenceRecordsContainer, Position.LEFT);
			occurrenceRecordsContainer.addToolButton(new ToolButton(Images.INSTANCE.editar16(), new Listener<BaseEvent>() {
				@Override
				public void handleEvent(BaseEvent be) {
					if (slidePanel.isVisible()) {
						slidePanel.hide();
					} else {
						slidePanel.show();
					}
				}
			}));
			slidePanel.addSaveNewMessageListener(new Listener<SlidePanelEvent>() {
				@Override
				public void handleEvent(SlidePanelEvent be) {
					saveNewMessage(be.getMessage());
				}
			});

			slidePanel.addRemoveMessageListener(new Listener<SlidePanelEvent>() {
				@Override
				public void handleEvent(SlidePanelEvent be) {
					removeMessage(be.getMessage());
				}
			});

			slidePanel.addRemoveMessageListener(new Listener<SlidePanelEvent>() {
				@Override
				public void handleEvent(SlidePanelEvent be) {
					removeMessage(be.getMessage());
				}
			});

			slidePanel.addEditMessageListener(new Listener<SlidePanelEvent>() {
				@Override
				public void handleEvent(SlidePanelEvent be) {
					removeMessage(be.getMessage());
				}
			});

			slidePanel.addRemoveMessageListener(new Listener<SlidePanelEvent>() {
				@Override
				public void handleEvent(SlidePanelEvent be) {
					removeMessage(be.getMessage());
				}
			});

			slidePanel.addSelectMessageListener(new Listener<SlidePanelEvent>() {
				@Override
				public void handleEvent(SlidePanelEvent be) {
					applyMessage(be.getMessage());
				}
			});
			occurrenceRecordsContainer.setLayout(new RowLayout(Orientation.VERTICAL));
			occurrenceRecordsContainer.add(getCpHistoricoChat(), new RowData(Style.DEFAULT, 1.0, new Margins(5, 5, 5, 5)));
			occurrenceRecordsContainer.add(getCpEntradaChat(), new RowData(Style.DEFAULT, Style.DEFAULT, new Margins(5, 5, 5, 5)));
		}
		return occurrenceRecordsContainer;
	}

	protected void removeMessage(DtoPredefinedMessage message) {
		// TODO Auto-generated method stub

	}

	protected void applyMessage(DtoPredefinedMessage predefinedMessage) {
		// TODO Auto-generated method stub
		// somente irá enviar a mensagem para o grid.

	}

	protected void saveNewMessage(DtoPredefinedMessage predefinedMessage) {
		// TODO Auto-generated method stub
		// deve ser criado um novo metodo na classe de servico para atender a
		// essa necessidade

	}

	public void setAnchorSlidePanel(Window window) {
		slidePanel.setAnchorMoveAndClose(window);
	}

	private Widget getContactContainer() {
		if (contactContainer == null) {
			contactContainer = new ContentPanelImp();
			contactContainer.setBodyBorder(false);
			contactContainer.addToolButton(new ToolButton(Images.INSTANCE.find16(), new Listener<BaseEvent>() {
				@Override
				public void handleEvent(BaseEvent be) {
					WindowContactResearch wContact = new WindowContactResearch();
					wContact.show();
				}
			}));
			contactContainer.addToolButton(new ToolButton(Images.INSTANCE.editar16(), new Listener<BaseEvent>() {
				@Override
				public void handleEvent(BaseEvent be) {
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

			vehicleLocationContainer.addToolButton(new ToolButton(Images.INSTANCE.camera16(), new Listener<BaseEvent>() {
				@Override
				public void handleEvent(BaseEvent be) {
				}
			}));

			vehicleLocationContainer.setHeading("Ve\u00EDculo e localiza\u00E7\u00E3o:");
			vehicleLocationContainer.setLayout(new BorderLayout());
			BorderLayoutData bld_htmlVehicleLocation = new BorderLayoutData(LayoutRegion.NORTH, 100.0f);
			bld_htmlVehicleLocation.setMinSize(100);
			bld_htmlVehicleLocation.setMaxSize(100);

			vehicleLocationContainer.add(getHtmlVehicleLocation(), bld_htmlVehicleLocation);
			BorderLayoutData bld_mapContainer = new BorderLayoutData(LayoutRegion.CENTER);
			bld_mapContainer.setMargins(new Margins(5, 5, 5, 5));
			vehicleLocationContainer.add(getMapContainer(), bld_mapContainer);

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

			aboutEventContainer.addToolButton(new ToolButton(Images.INSTANCE.impressora16(), new Listener<BaseEvent>() {
				@Override
				public void handleEvent(BaseEvent be) {
					// Window.alert("Imprimindo atendimento...");
				}
			}));

			aboutEventContainer.setHeight("180");
			aboutEventContainer.add(getCtpSobreEvento());
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
		contactTemplate = XTemplate.create(AttendanceWidGetTemplates.CONTACTS);
		vehicleLocationTemplate = XTemplate.create(AttendanceWidGetTemplates.VEHICLE_LOCATION);

		htmlContact.setHtml(contactTemplate.applyTemplate(Util.getJsObject(new BaseModelData())));
		htmlVehicleLocation.setHtml(vehicleLocationTemplate.applyTemplate(Util.getJsObject(new BaseModelData())));

	}


	private Html getHtmlContact() {
		if (htmlContact == null) {
			htmlContact = new Html();
			// "\t<div id=\"template-contact\" class=\"contact\">\r\n\t<table>\r\n\t\t<tr>\t\r\n\t\t\t<td> <img id=\"image-message\" src=\"http://cdn1.iconfinder.com/data/icons/humano2/72x72/emblems/emblem-people.png\" </td>\r\n\t\t\t<td>\r\n\t\t\t\t <div id=\"record\" class=\"record\">\t\t\t \r\n\t\t\t\t\t<br> Nome: Antonio Firmulano da Cunha Matos \r\n\t\t\t\t\t<br>Matricula:  097286358 \r\n\t\t\t\t\t<br>Telefone: 3354-7689 \r\n\t\t\t\t\t<br>Tempo de corrido do atendimento: 5m \r\n\t\t\t\t\t<br>Idade: 43 anos \r\n\t\t\t\t </div> \r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t</table>\r\n\t</div>\r\n");
		}
		return htmlContact;
	}

	/*
	 * private Button getBtnNewButton() { if (btnNewButton == null) {
	 * btnNewButton = new Button("Transferir"); btnNewButton.setSize("75px",
	 * "19px");
	 * btnNewButton.setIcon(AbstractImagePrototype.create(Images.INSTANCE
	 * .transferir16())); } return btnNewButton; }
	 * 
	 * private Button getBtnResolvido() { if (btnResolvido == null) {
	 * btnResolvido = new Button("Resolvido"); btnResolvido.setSize("75px",
	 * "17px");
	 * btnResolvido.setIcon(AbstractImagePrototype.create(Images.INSTANCE
	 * .resolvido16())); } return btnResolvido; }
	 */

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
			btGreenLed.setIcon(AbstractImagePrototype.create(Images.INSTANCE.darkGreen30()));
		}
		return btGreenLed;
	}

	private Button getBtBlueLed() {
		if (btBlueLed == null) {
			btBlueLed = new Button("");
			btBlueLed.setWidth("30px");
			btBlueLed.setIconAlign(IconAlign.RIGHT);
			btBlueLed.setIcon(AbstractImagePrototype.create(Images.INSTANCE.darkBlue30()));
		}
		return btBlueLed;
	}

	private Button getBtYallowLed() {
		if (btYallowLed == null) {
			btYallowLed = new Button("");
			btYallowLed.setWidth("30px");
			btYallowLed.setIconAlign(IconAlign.RIGHT);
			btYallowLed.setIcon(AbstractImagePrototype.create(Images.INSTANCE.darkYellow30()));
		}
		return btYallowLed;
	}

	private Button getBtRedLed() {
		if (btRedLed == null) {
			btRedLed = new Button("");
			btRedLed.setWidth("30px");
			btRedLed.setIconAlign(IconAlign.RIGHT);
			btRedLed.setIcon(AbstractImagePrototype.create(Images.INSTANCE.darkRed30()));
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

	// TODO provavelmente esse metodo será chamado recebendo como parametro o
	// "actualEvent"
	private void findExtraInfoEvent(DtoCriticalEvent selectedItem) {
		eventsAttendanceService.findExtraInfoEvent(selectedItem, new AsyncCallback<DtoExtraInfoEvent>() {
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
//		updateAboutContainer(extraInfo.getAbout());
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

	private ContentPanel getCtpSobreEvento() {
		if (ctpSobreEvento == null) {
			ctpSobreEvento = new ContentPanel();
			ctpSobreEvento.setBodyBorder(false);
			ctpSobreEvento.setHeaderVisible(false);
			ctpSobreEvento.setHeading("New ContentPanel");
			ctpSobreEvento.setCollapsible(true);
			ctpSobreEvento.setLayout(new AbsoluteLayout());
			ctpSobreEvento.add(getTxtNewText(), new AbsoluteData(6, 6));
			ctpSobreEvento.add(getTxtIncioDoEvento(), new AbsoluteData(6, 21));
			ctpSobreEvento.add(getTxtIncioDoAtendimento(), new AbsoluteData(6, 36));
			ctpSobreEvento.add(getTxtTempoDecorridoDo(), new AbsoluteData(6, 51));
			ctpSobreEvento.add(getTxtAtendenteAtual(), new AbsoluteData(6, 82));
			ctpSobreEvento.add(getTxtConclusoDoAtendimento(), new AbsoluteData(6, 97));
			ctpSobreEvento.add(getTxtNumeroProtocoloValue(), new AbsoluteData(206, 6));
			ctpSobreEvento.add(getTxtIniEventoValue(), new AbsoluteData(206, 21));
			ctpSobreEvento.add(getTxtIniAtendimentoValue(), new AbsoluteData(206, 36));
			ctpSobreEvento.add(getTxtTempoAtendimentoValue(), new AbsoluteData(206, 51));
			ctpSobreEvento.add(getTxtAtendenteValue(), new AbsoluteData(206, 82));
			ctpSobreEvento.add(getHprlnkTransferir(), new AbsoluteData(313, 82));
			ctpSobreEvento.add(getTxtConclusaoValue(), new AbsoluteData(206, 97));
			ctpSobreEvento.add(getHprlnkResolvido(), new AbsoluteData(313, 97));
		}
		return ctpSobreEvento;
	}

	private Text getTxtNewText() {
		if (txtNewText == null) {
			txtNewText = new Text("N\u00FAmero do Protocolo:");
			txtNewText.setStyleName("template-label");
			txtNewText.setSize("199px", "15px");
		}
		return txtNewText;
	}

	private Text getTxtIncioDoEvento() {
		if (txtIncioDoEvento == null) {
			txtIncioDoEvento = new Text("In\u00EDcio do evento:");
			txtIncioDoEvento.setStyleName("template-label");
			txtIncioDoEvento.setSize("199px", "15px");
		}
		return txtIncioDoEvento;
	}

	private Text getTxtIncioDoAtendimento() {
		if (txtIncioDoAtendimento == null) {
			txtIncioDoAtendimento = new Text("In\u00EDcio do atendimento:");
			txtIncioDoAtendimento.setStyleName("template-label");
			txtIncioDoAtendimento.setSize("199px", "15px");
		}
		return txtIncioDoAtendimento;
	}

	private Text getTxtTempoDecorridoDo() {
		if (txtTempoDecorridoDo == null) {
			txtTempoDecorridoDo = new Text("Tempo decorrido do atendimento:");
			txtTempoDecorridoDo.setStyleName("template-label");
			txtTempoDecorridoDo.setSize("199px", "15px");
		}
		return txtTempoDecorridoDo;
	}

	private Text getTxtAtendenteAtual() {
		if (txtAtendenteAtual == null) {
			txtAtendenteAtual = new Text("Atendente atual:");
			txtAtendenteAtual.setStyleName("template-label");
			txtAtendenteAtual.setSize("199px", "15px");
		}
		return txtAtendenteAtual;
	}

	private Text getTxtConclusoDoAtendimento() {
		if (txtConclusoDoAtendimento == null) {
			txtConclusoDoAtendimento = new Text("Conclus\u00E3o do atendimento:");
			txtConclusoDoAtendimento.setStyleName("template-label");
			txtConclusoDoAtendimento.setSize("199px", "15px");
		}
		return txtConclusoDoAtendimento;
	}

	private Text getTxtNumeroProtocoloValue() {
		if (txtNumeroProtocoloValue == null) {
			txtNumeroProtocoloValue = new Text("123456");
			txtNumeroProtocoloValue.setSize("160px", "15px");
		}
		return txtNumeroProtocoloValue;
	}

	private Text getTxtIniEventoValue() {
		if (txtIniEventoValue == null) {
			txtIniEventoValue = new Text("16/12/2012 as 13:20");
			txtIniEventoValue.setSize("160px", "15px");
		}
		return txtIniEventoValue;
	}

	private Text getTxtIniAtendimentoValue() {
		if (txtIniAtendimentoValue == null) {
			txtIniAtendimentoValue = new Text("16/12/2012 as 13:32");
			txtIniAtendimentoValue.setSize("160px", "15px");
		}
		return txtIniAtendimentoValue;
	}

	private Text getTxtTempoAtendimentoValue() {
		if (txtTempoAtendimentoValue == null) {
			txtTempoAtendimentoValue = new Text("12min");
			txtTempoAtendimentoValue.setSize("160px", "15px");
		}
		return txtTempoAtendimentoValue;
	}

	private Text getTxtAtendenteValue() {
		if (txtAtendenteValue == null) {
			txtAtendenteValue = new Text("Vandercleison");
			txtAtendenteValue.setSize("102px", "15px");
		}
		return txtAtendenteValue;
	}

	@SuppressWarnings("deprecation")
	private Hyperlink getHprlnkTransferir() {
		if (hprlnkTransferir == null) {
			hprlnkTransferir = new Hyperlink("Transferir", false, "newHistoryToken");
			hprlnkTransferir.addClickListener(new ClickListener() {

				@Override
				public void onClick(Widget sender) {
					dialogTransfer.show();
				}
			});
			initDialogTransfer();
		}
		return hprlnkTransferir;
	}

	private void initDialogTransfer() {
		dialogTransfer = new DialogWindowTransfer();
		dialogTransfer.addClickOkListener(new Listener<TransferEvent>() {
			@Override
			public void handleEvent(TransferEvent be) {
				DtoOperator operator = be.getOperator();
				transferAttendance(operator);
			}
		});
		eventsAttendanceService.getOperators(new AsyncCallback<List<DtoOperator>>() {
			@Override
			public void onSuccess(List<DtoOperator> operators) {
				dialogTransfer.updateOperators(operators);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO
			}
		});
	}

	protected void transferAttendance(DtoOperator operator) {
		eventsAttendanceService.transferAttendance(actualEvent, operator, new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO o que fazer com esse tru ou false.
			}
		});
	}

	private Text getTxtConclusaoValue() {
		if (txtConclusaoValue == null) {
			txtConclusaoValue = new Text("");
			txtConclusaoValue.setSize("102px", "15px");
		}
		return txtConclusaoValue;
	}

	@SuppressWarnings("deprecation")
	private Hyperlink getHprlnkResolvido() {
		if (hprlnkResolvido == null) {
			hprlnkResolvido = new Hyperlink("Resolvido", false, "newHistoryToken");
			// TODO este dialogBox deve seguir os principios do dislog box de
			// transferencia. Será necessário executar um servico quando o
			// usuário clicar em OK. NEsse momento será chamado o método para
			// fechar o atendimento. chamar o método closeAttendance()
			hprlnkResolvido.addClickListener(new ClickListener() {
				@Override
				public void onClick(Widget sender) {
					Text txt = new Text("Deseja realmente concluir esse atendimento?");
					Dialog box = new Dialog();
					box.setSize("290px", "163px");
					box.setButtons(Dialog.YESNO);
					box.setResizable(false);
					box.setModal(true);
					box.setHeading("Confirme");
					box.setLayout(new CenterLayout());
					box.add(txt);
					box.show();
				}
			});
			hprlnkResolvido.setSize("53px", "15px");
		}
		return hprlnkResolvido;
	}

	private void closeAttendance() {
		eventsAttendanceService.closeAttencance(actualEvent, new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO
			}

			@Override
			public void onSuccess(Boolean result) {

				// TODO
			}
		});
	}
}
