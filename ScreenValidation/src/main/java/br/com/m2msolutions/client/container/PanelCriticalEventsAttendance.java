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
import com.google.gwt.user.client.ui.Image;
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
	private ContentPanelImp ctpVehicleLocation;
	private Html htmlAboutEvent;
	private DtoCriticalEvent event;
	private SlidePanel slidePanel = new SlidePanel();
	private DialogWindowTransfer dialogTransfer;

	private ContentPanel ctpSobreEvento;
	private Text txtNumeroProtocolo;
	private Text txtIncioDoEvento;
	private Text txtIncioDoAtendimento;
	private Text txtTempoDecorridoDo;
	private Text txtAtendenteAtual;
	private Text txtConclusaoDoAtendimento;
	private Text txtNumeroProtocoloValue;
	private Text txtIniEventoValue;
	private Text txtIniAtendimentoValue;
	private Text txtTempoAtendimentoValue;
	private Text txtAtendenteAtualValue;
	private Hyperlink hprlnkTransferir;
	private Text txtConclusaoDoAtendimentoValue;
	private Hyperlink hprlnkResolvido;

	private CriticalEventsAttendanceServiceAsync eventsAttendanceService = CriticalEventsAttendanceService.Util.getInstance();

	// TODO fazer com que a tela receba o evento ao ser chamada. Não faz sentido
	// atender a um evento que não se conhece. Talvez adicionar o parametro ao
	// construtor.

	private DtoCriticalEvent actualEvent;
	private Image imgFoto;
	private Text txtNome;
	private Text txtTelefone;
	private Text txtIdade;
	private Text txtNomeValue;
	private Text txtTelefoneValue;
	private Text txtIdadeValue;
	private Text txtVeiculo;
	private Text txtVeiculoValue;
	private Text txtLinha;
	private Text txtLinhaValue;
	private Text txtEmpresa;
	private Text txtEmpresaValue;
	private Text txtEndereco;
	private Text txtLatitude;
	private Text txtLongitude;
	private Text txtEnderecoValue;
	private Text txtLatitudeValue;
	private Text txtLongitudeValue;
	private Text txtProxPonto;
	private Text txtProxPontoValue;

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

			slidePanel.addUpdateMessageListener(new Listener<SlidePanelEvent>() {
				@Override
				public void handleEvent(SlidePanelEvent be) {
					updateMessage(be.getMessage());
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

	protected void updateMessage(DtoPredefinedMessage message) {
		eventsAttendanceService.updatePredefinedMessage(message, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
			}
		});

	}

	protected void removeMessage(DtoPredefinedMessage message) {
		eventsAttendanceService.removePredefinedMessage(message, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
			}
		});


	}

	protected void applyMessage(DtoPredefinedMessage predefinedMessage) {
		// TODO Auto-generated method stub
		// somente irá enviar a mensagem para o grid.
	}

	protected void saveNewMessage(DtoPredefinedMessage predefinedMessage) {
		// TODO Auto-generated method stub
		// deve ser criado um novo metodo na classe de servico para atender a
		// essa necessidade
		eventsAttendanceService.savePredefinedMessage(predefinedMessage, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
			}
		});

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
			contactContainer.setLayout(new AbsoluteLayout());
			contactContainer.setHeight("150");
			contactContainer.add(getImgFoto(), new AbsoluteData(6, 6));
			contactContainer.add(getTxtNome(), new AbsoluteData(97, 6));
			contactContainer.add(getTxtTelefone(), new AbsoluteData(97, 21));
			contactContainer.add(getTxtIdade(), new AbsoluteData(97, 36));
			contactContainer.add(getTxtNomeValue(), new AbsoluteData(161, 6));
			contactContainer.add(getTxtTelefoneValue(), new AbsoluteData(161, 21));
			contactContainer.add(getTxtIdadeValue(), new AbsoluteData(161, 36));
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
			getVehicleLocation().setLayout(new AbsoluteLayout());
			BorderLayoutData bldHtmlVehicleLocation = new BorderLayoutData(LayoutRegion.NORTH, 85.0f);
			bldHtmlVehicleLocation.setMinSize(60);
			bldHtmlVehicleLocation.setMaxSize(60);

			vehicleLocationContainer.add(getVehicleLocation(), bldHtmlVehicleLocation);
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

	private ContentPanelImp getVehicleLocation() {
		if(ctpVehicleLocation == null){
			ctpVehicleLocation = new ContentPanelImp();
			ctpVehicleLocation.setBodyBorder(false);
			ctpVehicleLocation.setHeaderVisible(false);
			ctpVehicleLocation.add(getTxtVeiculo(), new AbsoluteData(6, 6));
			ctpVehicleLocation.add(getTxtVeiculoValue(), new AbsoluteData(70, 6));
			ctpVehicleLocation.add(getTxtLinha(), new AbsoluteData(6, 21));
			ctpVehicleLocation.add(getTxtLinhaValue(), new AbsoluteData(70, 21));
			ctpVehicleLocation.add(getTxtEmpresa(), new AbsoluteData(6, 36));
			ctpVehicleLocation.add(getTxtEmpresaValue(), new AbsoluteData(70, 36));
			ctpVehicleLocation.add(getTxtEndereco(), new AbsoluteData(206, 6));
			ctpVehicleLocation.add(getTxtLatitude(), new AbsoluteData(206, 21));
			ctpVehicleLocation.add(getTxtLongitude(), new AbsoluteData(206, 36));
			ctpVehicleLocation.add(getTxtEnderecoValue(), new AbsoluteData(270, 6));
			ctpVehicleLocation.add(getTxtLatitudeValue(), new AbsoluteData(270, 21));
			ctpVehicleLocation.add(getTxtLongitudeValue(), new AbsoluteData(270, 36));
			ctpVehicleLocation.add(getTxtProxPonto(), new AbsoluteData(206, 52));
			ctpVehicleLocation.add(getTxtProxPontoValue(), new AbsoluteData(270, 52));
		}
		return ctpVehicleLocation;
	}

	private void createTemplates() {
		contactTemplate = XTemplate.create(AttendanceWidGetTemplates.CONTACTS);
		vehicleLocationTemplate = XTemplate.create(AttendanceWidGetTemplates.VEHICLE_LOCATION);
//		htmlVehicleLocation.setHtml(vehicleLocationTemplate.applyTemplate(Util.getJsObject(new BaseModelData())));

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
		
		this.txtNumeroProtocoloValue.setText(aboutEvent.getProtocol());
		this.txtIniEventoValue.setText(aboutEvent.getStartEvent());
		this.txtIniAtendimentoValue.setText(aboutEvent.getStartAttendance());
		this.txtTempoDecorridoDo.setText(aboutEvent.getDuration());
		this.txtAtendenteAtualValue.setText(aboutEvent.getOperator());
		this.txtConclusaoDoAtendimentoValue.setText(aboutEvent.getConclusion());
		
	}

	private void updateContactContainer(DtoContact contact) {
		
		this.txtNomeValue.setText(contact.getName());
		this.txtTelefoneValue.setText(contact.getPhone());
		this.txtIdadeValue.setText(contact.getAge());
		
	}

	private void updateVehicleLocationContainer(DtoVehicleAndLocation vehicleAndLocation) {
		
		txtVeiculoValue.setText(vehicleAndLocation.getVehicle());
		txtLinhaValue.setText(vehicleAndLocation.getBusServiceName());
		txtEmpresaValue.setText(vehicleAndLocation.getCompanyName());
		txtEnderecoValue.setText(vehicleAndLocation.getAddress());
		txtLatitudeValue.setText(String.valueOf(vehicleAndLocation.getLatitude()));
		txtLongitudeValue.setText(String.valueOf(vehicleAndLocation.getLongitude()));
		txtProxPontoValue.setText(vehicleAndLocation.getNextStop());
		
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
			ctpSobreEvento.add(getTxtNumeroProtocolo(), new AbsoluteData(6, 6));
			ctpSobreEvento.add(getTxtIncioDoEvento(), new AbsoluteData(6, 21));
			ctpSobreEvento.add(getTxtIncioDoAtendimento(), new AbsoluteData(6, 36));
			ctpSobreEvento.add(getTxtTempoDecorridoDo(), new AbsoluteData(6, 51));
			ctpSobreEvento.add(getTxtAtendenteAtual(), new AbsoluteData(6, 82));
			ctpSobreEvento.add(getTxtConclusaoDoAtendimento(), new AbsoluteData(6, 97));
			ctpSobreEvento.add(getTxtNumeroProtocoloValue(), new AbsoluteData(206, 6));
			ctpSobreEvento.add(getTxtIniEventoValue(), new AbsoluteData(206, 21));
			ctpSobreEvento.add(getTxtIniAtendimentoValue(), new AbsoluteData(206, 36));
			ctpSobreEvento.add(getTxtTempoAtendimentoValue(), new AbsoluteData(206, 51));
			ctpSobreEvento.add(getTxtAtendenteAtualValue(), new AbsoluteData(206, 82));
			ctpSobreEvento.add(getHprlnkTransferir(), new AbsoluteData(313, 82));
			ctpSobreEvento.add(getTxtConclusaoDoAtendimentoValue(), new AbsoluteData(206, 97));
			ctpSobreEvento.add(getHprlnkResolvido(), new AbsoluteData(313, 97));
		}
		return ctpSobreEvento;
	}

	private Text getTxtNumeroProtocolo() {
		if (txtNumeroProtocolo == null) {
			txtNumeroProtocolo = new Text("N\u00FAmero do Protocolo:");
			txtNumeroProtocolo.setStyleName("template-label");
			txtNumeroProtocolo.setSize("199px", "15px");
		}
		return txtNumeroProtocolo;
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

	private Text getTxtConclusaoDoAtendimento() {
		if (txtConclusaoDoAtendimento == null) {
			txtConclusaoDoAtendimento = new Text("Conclus\u00E3o do atendimento:");
			txtConclusaoDoAtendimento.setStyleName("template-label");
			txtConclusaoDoAtendimento.setSize("199px", "15px");
		}
		return txtConclusaoDoAtendimento;
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

	private Text getTxtAtendenteAtualValue() {
		if (txtAtendenteAtualValue == null) {
			txtAtendenteAtualValue = new Text("Vandercleison");
			txtAtendenteAtualValue.setSize("102px", "15px");
		}
		return txtAtendenteAtualValue;
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

	private Text getTxtConclusaoDoAtendimentoValue() {
		if (txtConclusaoDoAtendimentoValue == null) {
			txtConclusaoDoAtendimentoValue = new Text("");
			txtConclusaoDoAtendimentoValue.setSize("102px", "15px");
		}
		return txtConclusaoDoAtendimentoValue;
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
	private Image getImgFoto() {
		if (imgFoto == null) {
			imgFoto = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSleT7-NZ7I8oY8_jCTeFpK1QxJRFMr3B2xV6QCYXr-0P2KICaTcQ");
			imgFoto.setSize("85px", "105px");
		}
		return imgFoto;
	}
	private Text getTxtNome() {
		if (txtNome == null) {
			txtNome = new Text("Nome:");
			txtNome.setStyleName("template-label");
			txtNome.setSize("63px", "15px");
		}
		return txtNome;
	}
	private Text getTxtTelefone() {
		if (txtTelefone == null) {
			txtTelefone = new Text("Telefone:");
			txtTelefone.setStyleName("template-label");
			txtTelefone.setSize("63px", "15px");
		}
		return txtTelefone;
	}
	private Text getTxtIdade() {
		if (txtIdade == null) {
			txtIdade = new Text("Idade:");
			txtIdade.setStyleName("template-label");
			txtIdade.setSize("63px", "15px");
		}
		return txtIdade;
	}
	private Text getTxtNomeValue() {
		if (txtNomeValue == null) {
			txtNomeValue = new Text("Francisco Jos\u00E9");
			txtNomeValue.setSize("160px", "15px");
		}
		return txtNomeValue;
	}
	private Text getTxtTelefoneValue() {
		if (txtTelefoneValue == null) {
			txtTelefoneValue = new Text("(80) 8080 1234");
			txtTelefoneValue.setSize("160px", "15px");
		}
		return txtTelefoneValue;
	}
	private Text getTxtIdadeValue() {
		if (txtIdadeValue == null) {
			txtIdadeValue = new Text("35 Anos");
			txtIdadeValue.setSize("160px", "15px");
		}
		return txtIdadeValue;
	}
	private Text getTxtVeiculo() {
		if (txtVeiculo == null) {
			txtVeiculo = new Text("Ve\u00EDculo:");
			txtVeiculo.setStyleName("template-label");
			txtVeiculo.setSize("63px", "15px");
		}
		return txtVeiculo;
	}
	private Text getTxtVeiculoValue() {
		if (txtVeiculoValue == null) {
			txtVeiculoValue = new Text("3050");
			txtVeiculoValue.setSize("135px", "15px");
		}
		return txtVeiculoValue;
	}
	private Text getTxtLinha() {
		if (txtLinha == null) {
			txtLinha = new Text("Linha:");
			txtLinha.setStyleName("template-label");
			txtLinha.setSize("63px", "15px");
		}
		return txtLinha;
	}
	private Text getTxtLinhaValue() {
		if (txtLinhaValue == null) {
			txtLinhaValue = new Text("Santa Cruz");
			txtLinhaValue.setSize("135px", "15px");
		}
		return txtLinhaValue;
	}
	private Text getTxtEmpresa() {
		if (txtEmpresa == null) {
			txtEmpresa = new Text("Empresa:");
			txtEmpresa.setStyleName("template-label");
			txtEmpresa.setSize("63px", "15px");
		}
		return txtEmpresa;
	}
	private Text getTxtEmpresaValue() {
		if (txtEmpresaValue == null) {
			txtEmpresaValue = new Text("Expresso Pegaso");
			txtEmpresaValue.setSize("135px", "15px");
		}
		return txtEmpresaValue;
	}
	private Text getTxtEndereco() {
		if (txtEndereco == null) {
			txtEndereco = new Text("Endere\u00E7o:");
			txtEndereco.setStyleName("template-label");
			txtEndereco.setSize("63px", "15px");
		}
		return txtEndereco;
	}
	private Text getTxtLatitude() {
		if (txtLatitude == null) {
			txtLatitude = new Text("Latitude:");
			txtLatitude.setStyleName("template-label");
			txtLatitude.setSize("63px", "15px");
		}
		return txtLatitude;
	}
	private Text getTxtLongitude() {
		if (txtLongitude == null) {
			txtLongitude = new Text("Longitude:");
			txtLongitude.setStyleName("template-label");
			txtLongitude.setSize("63px", "15px");
		}
		return txtLongitude;
	}
	private Text getTxtEnderecoValue() {
		if (txtEnderecoValue == null) {
			txtEnderecoValue = new Text("3050");
			txtEnderecoValue.setSize("135px", "15px");
		}
		return txtEnderecoValue;
	}
	private Text getTxtLatitudeValue() {
		if (txtLatitudeValue == null) {
			txtLatitudeValue = new Text("-3.45659764673");
			txtLatitudeValue.setSize("135px", "15px");
		}
		return txtLatitudeValue;
	}
	private Text getTxtLongitudeValue() {
		if (txtLongitudeValue == null) {
			txtLongitudeValue = new Text("-38.9883866709");
			txtLongitudeValue.setSize("135px", "15px");
		}
		return txtLongitudeValue;
	}
	private Text getTxtProxPonto() {
		if (txtProxPonto == null) {
			txtProxPonto = new Text("Prx Ponto:");
			txtProxPonto.setStyleName("template-label");
			txtProxPonto.setSize("63px", "15px");
		}
		return txtProxPonto;
	}
	private Text getTxtProxPontoValue() {
		if (txtProxPontoValue == null) {
			txtProxPontoValue = new Text("Padaria");
			txtProxPontoValue.setSize("135px", "15px");
		}
		return txtProxPontoValue;
	}
}
