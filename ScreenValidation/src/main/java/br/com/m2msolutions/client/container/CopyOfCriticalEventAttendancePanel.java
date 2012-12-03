package br.com.m2msolutions.client.container;

import br.com.m2msolutions.client.images.Images;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class CopyOfCriticalEventAttendancePanel extends LayoutContainer {
	
	private LabelField lbNumeroProtocolo;
	private LabelField lbInicioEvento;
	private LabelField lbInicioAtendimento;
	private LabelField lbNumerProtocoloValue;
	private LabelField lbInicioEventoValue;
	private LabelField lblfldAs;
	private LabelField lbTDecorridoAtendimentoValue;
	private LabelField lbAtendenteAtualValue;
	private LabelField lbConclusaoAtendimentoValue;
	private Button btnNewButton;
	private Button btnResolvido;
	private ContentPanelImp cpVehicleLocationDesc;
	private ContentPanelImp cpVehicleLocationMap;
	private ContentPanelImp cpVehicleLocationLeft;
	private ContentPanelImp cpVehicleLocationRight;
	private LabelField lbVeiculo;
	private LabelField lbLinha;
	private LabelField lbEmpresa;
	private LabelField lbEndereco;
	private LabelField lbLatitude;
	private LabelField lbLongitude;
	private LabelField lbProximoPonto;
	private LabelField lbVeiculoValue;
	private LabelField lbLinhaValue;
	private LabelField lbEmpresaVeiculo;
	private LabelField lbEnderecoValue;
	private LabelField lbLatitudeValue;
	private LabelField lbLongitudeValue;
	private LabelField lbProximoPontoValue;
	private LayoutContainer lcImgContato;
	private Image imgContato;
	private LabelField lbNome;
	private LabelField lbMatricula;
	private LabelField lbTelefone;
	private LabelField lblfldIdade;
	private LabelField lbNomeValue;
	private LabelField lbMatriculaValue;
	private LabelField lbTelefoneValue;
	private LabelField lbIdadeValue;
	private ContentPanel cpHistoricoChat;
	private ContentPanelImp cpEntradaChat;
	private ListView listView;
	private ContentPanelImp cpChatEntradaLeft;
	private ContentPanelImp cpChatEntraRigth;
	private ContentPanelImp cpEntradaChatTop;
	private ContentPanelImp cpEntradaChatBottom;
	private TextArea taEntradaChat;
	private LabelField lbPainelLeds;
	private Button btRegistrar;
	private CheckBox cbParaOVeculo;

	private MapWidget mapLocation;

	public CopyOfCriticalEventAttendancePanel() {

		setBorders(true);
		setLayout(new BorderLayout());

		LayoutContainer lcCenter = new LayoutContainer();
		lcCenter.setBorders(true);
		lcCenter.setLayout(new RowLayout(Orientation.VERTICAL));

		ContentPanelImp cpHeaderCenter = new ContentPanelImp();
		cpHeaderCenter.setBodyBorder(false);
		cpHeaderCenter.setHeading("Sobre o Evento:");
		cpHeaderCenter.setLayout(new AbsoluteLayout());
		cpHeaderCenter.add(getLbNumeroProtocolo(), new AbsoluteData(6, 6));
		cpHeaderCenter.add(getLbInicioEvento(), new AbsoluteData(6, 22));
		cpHeaderCenter.add(getLbInicioAtendimento(), new AbsoluteData(6, 38));

		LabelField lbTDecorridoAtendimento = new LabelField("Tempo decorrido do Atendimento:");
		lbTDecorridoAtendimento.setStyleName("titulo-label");
		lbTDecorridoAtendimento.setHeight(15);
		cpHeaderCenter.add(lbTDecorridoAtendimento, new AbsoluteData(6, 56));
		lbTDecorridoAtendimento.setSize("200px", "20px");

		LabelField lbAtendenteAtual = new LabelField("Atendente Atual:");
		lbAtendenteAtual.setStyleName("titulo-label");
		lbAtendenteAtual.setHeight(15);
		cpHeaderCenter.add(lbAtendenteAtual, new AbsoluteData(6, 72));
		lbAtendenteAtual.setSize("100px", "20px");

		LabelField lbConclusaoAtendimento = new LabelField("Conclus\u00E3o do Atendimento:");
		lbConclusaoAtendimento.setStyleName("titulo-label");
		lbConclusaoAtendimento.setHeight(15);
		cpHeaderCenter.add(lbConclusaoAtendimento, new AbsoluteData(6, 90));
		lbConclusaoAtendimento.setSize("160px", "20px");
		cpHeaderCenter.add(getLbNumerProtocoloValue(), new AbsoluteData(138, 6));
		cpHeaderCenter.add(getLbInicioEventoValue(), new AbsoluteData(110, 22));
		cpHeaderCenter.add(getLblfldAs(), new AbsoluteData(142, 38));
		cpHeaderCenter.add(getLbTDecorridoAtendimentoValue(), new AbsoluteData(206, 56));
		cpHeaderCenter.add(getLbAtendenteAtualValue(), new AbsoluteData(110, 72));
		cpHeaderCenter.add(getLbConclusaoAtendimentoValue(), new AbsoluteData(170, 90));
		cpHeaderCenter.add(getBtnNewButton(), new AbsoluteData(280, 72));
		cpHeaderCenter.add(getBtnResolvido(), new AbsoluteData(280, 90));
		lcCenter.add(cpHeaderCenter, new RowData(Style.DEFAULT, -1.0, new Margins()));
		cpHeaderCenter.setHeight("180");

		ContentPanelImp cpBottomCenter = new ContentPanelImp();
		cpBottomCenter.setBodyBorder(false);
		cpBottomCenter.setHeading("Ve\u00EDculo e localiza\u00E7\u00E3o:");
		cpBottomCenter.setLayout(new RowLayout(Orientation.VERTICAL));
		getCpVehicleLocationDesc().setLayout(new RowLayout(Orientation.HORIZONTAL));
		cpBottomCenter.add(getCpVehicleLocationDesc(), new RowData(1.0, Style.DEFAULT, new Margins()));
		getCpVehicleLocationMap().setLayout(new FitLayout());
		cpBottomCenter.add(getCpVehicleLocationMap(), new RowData(Style.DEFAULT, 1.0, new Margins()));
		lcCenter.add(cpBottomCenter, new RowData(Style.DEFAULT, 1.0, new Margins()));
		BorderLayoutData bld_lcCenter = new BorderLayoutData(LayoutRegion.CENTER);
		bld_lcCenter.setMargins(new Margins(5, 2, 5, 5));
		add(lcCenter, bld_lcCenter);
		
		getCpVehicleLocationMap().add(getMapPosition());

		LayoutContainer lcEast = new LayoutContainer();
		lcEast.setBorders(true);
		lcEast.setLayout(new RowLayout(Orientation.VERTICAL));

		ContentPanelImp cpHeaderEast = new ContentPanelImp();
		cpHeaderEast.setBodyBorder(false);
		cpHeaderEast.setHeading("Contato:");
		cpHeaderEast.setLayout(new AbsoluteLayout());
		getLcImgContato().setLayout(new CenterLayout());
		cpHeaderEast.add(getLcImgContato(), new AbsoluteData(6, 6));
		cpHeaderEast.add(getLbNome(), new AbsoluteData(97, 6));
		cpHeaderEast.add(getLbMatricula(), new AbsoluteData(97, 23));
		cpHeaderEast.add(getLbTelefone(), new AbsoluteData(97, 42));
		cpHeaderEast.add(getLblfldIdade(), new AbsoluteData(97, 61));
		cpHeaderEast.add(getLbNomeValue(), new AbsoluteData(146, 6));
		cpHeaderEast.add(getLbMatriculaValue(), new AbsoluteData(156, 23));
		cpHeaderEast.add(getLbTelefoneValue(), new AbsoluteData(161, 42));
		cpHeaderEast.add(getLbIdadeValue(), new AbsoluteData(143, 61));
		lcEast.add(cpHeaderEast, new RowData(Style.DEFAULT, Style.DEFAULT, new Margins(0, 0, 0, 0)));
		cpHeaderEast.setHeight("150");

		ContentPanelImp cpBottomEast = new ContentPanelImp();
		cpBottomEast.setBodyBorder(false);
		cpBottomEast.setHeading("Registro de Ocorrencia:");
		cpBottomEast.setLayout(new RowLayout(Orientation.VERTICAL));
		getCpHistoricoChat().setLayout(new FitLayout());
		cpBottomEast.add(getCpHistoricoChat(), new RowData(Style.DEFAULT, 1.0, new Margins(5, 5, 5, 5)));
		cpBottomEast.add(getCpEntradaChat(), new RowData(Style.DEFAULT, Style.DEFAULT, new Margins(5, 5, 5, 5)));
		lcEast.add(cpBottomEast, new RowData(Style.DEFAULT, 1.0, new Margins()));
		BorderLayoutData bld_lcEast = new BorderLayoutData(LayoutRegion.EAST,410f);
		bld_lcEast.setMargins(new Margins(5, 5, 5, 2));
		add(lcEast, bld_lcEast);
	}
	private LabelField getLbNumeroProtocolo() {
		if (lbNumeroProtocolo == null) {
			lbNumeroProtocolo = new LabelField("N\u00FAmero do Protocolo:");
			lbNumeroProtocolo.setStyleName("titulo-label");
			lbNumeroProtocolo.setHeight(15);
			lbNumeroProtocolo.setSize("130px", "20px");
		}
		return lbNumeroProtocolo;
	}
	private LabelField getLbInicioEvento() {
		if (lbInicioEvento == null) {
			lbInicioEvento = new LabelField("In\u00EDcio do Evento:");
			lbInicioEvento.setStyleName("titulo-label");
			lbInicioEvento.setHeight(15);
			lbInicioEvento.setSize("100px", "20px");
		}
		return lbInicioEvento;
	}
	private LabelField getLbInicioAtendimento() {
		if (lbInicioAtendimento == null) {
			lbInicioAtendimento = new LabelField("In\u00EDcio do Atendimento:");
			lbInicioAtendimento.setStyleName("titulo-label");
			lbInicioAtendimento.setHeight(15);
			lbInicioAtendimento.setSize("130px", "20px");
		}
		return lbInicioAtendimento;
	}
	private LabelField getLbNumerProtocoloValue() {
		if (lbNumerProtocoloValue == null) {
			lbNumerProtocoloValue = new LabelField("123456");
			lbNumerProtocoloValue.setWidth("100px");
		}
		return lbNumerProtocoloValue;
	}
	private LabelField getLbInicioEventoValue() {
		if (lbInicioEventoValue == null) {
			lbInicioEventoValue = new LabelField("09/07/2012 as 15:31");
			lbInicioEventoValue.setWidth("150px");
		}
		return lbInicioEventoValue;
	}
	private LabelField getLblfldAs() {
		if (lblfldAs == null) {
			lblfldAs = new LabelField("09/07/2012 as 15:32");
			lblfldAs.setSize("150px", "");
		}
		return lblfldAs;
	}
	private LabelField getLbTDecorridoAtendimentoValue() {
		if (lbTDecorridoAtendimentoValue == null) {
			lbTDecorridoAtendimentoValue = new LabelField("4min");
			lbTDecorridoAtendimentoValue.setSize("85px", "20px");
		}
		return lbTDecorridoAtendimentoValue;
	}
	private LabelField getLbAtendenteAtualValue() {
		if (lbAtendenteAtualValue == null) {
			lbAtendenteAtualValue = new LabelField("Joao Silva");
			lbAtendenteAtualValue.setSize("180px", "20px");
		}
		return lbAtendenteAtualValue;
	}
	private LabelField getLbConclusaoAtendimentoValue() {
		if (lbConclusaoAtendimentoValue == null) {
			lbConclusaoAtendimentoValue = new LabelField("");
			lbConclusaoAtendimentoValue.setSize("121px", "20px");
		}
		return lbConclusaoAtendimentoValue;
	}
	private Button getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new Button("Transferir");
			btnNewButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
				}
			});
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
	private ContentPanel getCpVehicleLocationDesc() {
		if (cpVehicleLocationDesc == null) {
			cpVehicleLocationDesc = new ContentPanelImp();
			cpVehicleLocationDesc.setBodyBorder(false);
			cpVehicleLocationDesc.setHeight("100");
			cpVehicleLocationDesc.setHeaderVisible(false);
			cpVehicleLocationDesc.setHeading("New ContentPanel");
			cpVehicleLocationDesc.setCollapsible(true);
			getCpVehicleLocationLeft().setLayout(new AbsoluteLayout());
			cpVehicleLocationDesc.add(getCpVehicleLocationLeft(), new RowData(0.5, 1.0, new Margins()));
			getCpVehicleLocationRight().setLayout(new AbsoluteLayout());
			cpVehicleLocationDesc.add(getCpVehicleLocationRight(), new RowData(0.5, 1.0, new Margins()));
		}
		return cpVehicleLocationDesc;
	}
	private ContentPanel getCpVehicleLocationMap() {
		if (cpVehicleLocationMap == null) {
			cpVehicleLocationMap = new ContentPanelImp();
			cpVehicleLocationMap.setBodyBorder(false);
			cpVehicleLocationMap.setHeaderVisible(false);
			cpVehicleLocationMap.setHeading("New ContentPanel");
			cpVehicleLocationMap.setCollapsible(true);
		}
		return cpVehicleLocationMap;
	}
	private ContentPanel getCpVehicleLocationLeft() {
		if (cpVehicleLocationLeft == null) {
			cpVehicleLocationLeft = new ContentPanelImp();
			cpVehicleLocationLeft.setBodyBorder(false);
			cpVehicleLocationLeft.setHeaderVisible(false);
			cpVehicleLocationLeft.setHeading("New ContentPanel");
			cpVehicleLocationLeft.setCollapsible(true);
			cpVehicleLocationLeft.add(getLbVeiculo(), new AbsoluteData(6, 6));
			cpVehicleLocationLeft.add(getLbLinha(), new AbsoluteData(6, 24));
			cpVehicleLocationLeft.add(getLbEmpresa(), new AbsoluteData(6, 43));
			cpVehicleLocationLeft.add(getLabelField_3(), new AbsoluteData(60, 6));
			cpVehicleLocationLeft.add(getLabelField_4(), new AbsoluteData(50, 24));
			cpVehicleLocationLeft.add(getLabelField_5(), new AbsoluteData(70, 43));
		}
		return cpVehicleLocationLeft;
	}
	private ContentPanel getCpVehicleLocationRight() {
		if (cpVehicleLocationRight == null) {
			cpVehicleLocationRight = new ContentPanelImp();
			cpVehicleLocationRight.setBodyBorder(false);
			cpVehicleLocationRight.setHeaderVisible(false);
			cpVehicleLocationRight.setHeading("New ContentPanel");
			cpVehicleLocationRight.setCollapsible(true);
			cpVehicleLocationRight.add(getLabelField_6(), new AbsoluteData(6, 6));
			cpVehicleLocationRight.add(getLabelField_1_1(), new AbsoluteData(6, 24));
			cpVehicleLocationRight.add(getLabelField_2_1(), new AbsoluteData(6, 43));
			cpVehicleLocationRight.add(getLabelField_6_1(), new AbsoluteData(6, 63));
			cpVehicleLocationRight.add(getLabelField_7(), new AbsoluteData(71, 6));
			cpVehicleLocationRight.add(getLabelField_8(), new AbsoluteData(62, 24));
			cpVehicleLocationRight.add(getLabelField_9(), new AbsoluteData(73, 43));
			cpVehicleLocationRight.add(getLabelField_10(), new AbsoluteData(80, 63));
		}
		return cpVehicleLocationRight;
	}
	private LabelField getLbVeiculo() {
		if (lbVeiculo == null) {
			lbVeiculo = new LabelField("Ve\u00EDculo:");
			lbVeiculo.setStyleName("titulo-label");
			lbVeiculo.setSize("45px", "20px");
		}
		return lbVeiculo;
	}
	private LabelField getLbLinha() {
		if (lbLinha == null) {
			lbLinha = new LabelField("Linha:");
			lbLinha.setStyleName("titulo-label");
			lbLinha.setSize("40px", "20px");
		}
		return lbLinha;
	}
	private LabelField getLbEmpresa() {
		if (lbEmpresa == null) {
			lbEmpresa = new LabelField("Empresa:");
			lbEmpresa.setStyleName("titulo-label");
			lbEmpresa.setSize("53px", "20px");
		}
		return lbEmpresa;
	}
	private LabelField getLabelField_6() {
		if (lbEndereco == null) {
			lbEndereco = new LabelField("Endere\u00E7o:");
			lbEndereco.setStyleName("titulo-label");
			lbEndereco.setSize("58px", "20px");
		}
		return lbEndereco;
	}
	private LabelField getLabelField_1_1() {
		if (lbLatitude == null) {
			lbLatitude = new LabelField("Latitude:");
			lbLatitude.setStyleName("titulo-label");
			lbLatitude.setSize("50px", "20px");
		}
		return lbLatitude;
	}
	private LabelField getLabelField_2_1() {
		if (lbLongitude == null) {
			lbLongitude = new LabelField("Longitude:");
			lbLongitude.setStyleName("titulo-label");
			lbLongitude.setSize("60px", "20px");
		}
		return lbLongitude;
	}
	private LabelField getLabelField_6_1() {
		if (lbProximoPonto == null) {
			lbProximoPonto = new LabelField("Pr\u00F3x. Ponto:");
			lbProximoPonto.setStyleName("titulo-label");
			lbProximoPonto.setSize("75px", "20px");
		}
		return lbProximoPonto;
	}
	private LabelField getLabelField_3() {
		if (lbVeiculoValue == null) {
			lbVeiculoValue = new LabelField("3050");
			lbVeiculoValue.setWidth("125px");
		}
		return lbVeiculoValue;
	}
	private LabelField getLabelField_4() {
		if (lbLinhaValue == null) {
			lbLinhaValue = new LabelField("Santa Cruz");
			lbLinhaValue.setSize("250px", "20px");
		}
		return lbLinhaValue;
	}
	private LabelField getLabelField_5() {
		if (lbEmpresaVeiculo == null) {
			lbEmpresaVeiculo = new LabelField("Globo");
			lbEmpresaVeiculo.setWidth("200");
		}
		return lbEmpresaVeiculo;
	}
	private LabelField getLabelField_7() {
		if (lbEnderecoValue == null) {
			lbEnderecoValue = new LabelField("BR-116");
			lbEnderecoValue.setWidth("250px");
		}
		return lbEnderecoValue;
	}
	private LabelField getLabelField_8() {
		if (lbLatitudeValue == null) {
			lbLatitudeValue = new LabelField("12,56895");
			lbLatitudeValue.setWidth("120px");
		}
		return lbLatitudeValue;
	}
	private LabelField getLabelField_9() {
		if (lbLongitudeValue == null) {
			lbLongitudeValue = new LabelField("13,76345");
			lbLongitudeValue.setWidth("90px");
		}
		return lbLongitudeValue;
	}
	private LabelField getLabelField_10() {
		if (lbProximoPontoValue == null) {
			lbProximoPontoValue = new LabelField("Padaria da Esq.");
			lbProximoPontoValue.setWidth("250px");
		}
		return lbProximoPontoValue;
	}
	private LayoutContainer getLcImgContato() {
		if (lcImgContato == null) {
			lcImgContato = new LayoutContainer();
			lcImgContato.setSize("85px", "103px");
			lcImgContato.setBorders(true);
			lcImgContato.add(getImgContato());
		}
		return lcImgContato;
	}
	private Image getImgContato() {
		if (imgContato == null) {
			imgContato = new Image("");
		}
		return imgContato;
	}
	private LabelField getLbNome() {
		if (lbNome == null) {
			lbNome = new LabelField("Nome :");
			lbNome.setStyleName("titulo-label");
		}
		return lbNome;
	}
	private LabelField getLbMatricula() {
		if (lbMatricula == null) {
			lbMatricula = new LabelField("Matr\u00EDcula :");
			lbMatricula.setStyleName("titulo-label");
		}
		return lbMatricula;
	}
	private LabelField getLbTelefone() {
		if (lbTelefone == null) {
			lbTelefone = new LabelField("Telefone :");
			lbTelefone.setStyleName("titulo-label");
		}
		return lbTelefone;
	}
	private LabelField getLblfldIdade() {
		if (lblfldIdade == null) {
			lblfldIdade = new LabelField("Idade :");
			lblfldIdade.setStyleName("titulo-label");
		}
		return lblfldIdade;
	}
	private LabelField getLbNomeValue() {
		if (lbNomeValue == null) {
			lbNomeValue = new LabelField("Francisco Jos\u00E9 da Silva");
			lbNomeValue.setWidth("250px");
		}
		return lbNomeValue;
	}
	private LabelField getLbMatriculaValue() {
		if (lbMatriculaValue == null) {
			lbMatriculaValue = new LabelField("123456");
			lbMatriculaValue.setWidth("150px");
		}
		return lbMatriculaValue;
	}
	private LabelField getLbTelefoneValue() {
		if (lbTelefoneValue == null) {
			lbTelefoneValue = new LabelField("(85) 8888 8888");
		}
		return lbTelefoneValue;
	}
	private LabelField getLbIdadeValue() {
		if (lbIdadeValue == null) {
			lbIdadeValue = new LabelField("35 anos");
		}
		return lbIdadeValue;
	}
	private ContentPanel getCpHistoricoChat() {
		if (cpHistoricoChat == null) {
			cpHistoricoChat = new ContentPanel();
			cpHistoricoChat.setBodyBorder(false);
			cpHistoricoChat.setHeaderVisible(false);
			cpHistoricoChat.setHeading("New ContentPanel");
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
			cpEntradaChat.setHeading("New ContentPanel");
			cpEntradaChat.setCollapsible(true);
			cpEntradaChat.setLayout(new RowLayout(Orientation.HORIZONTAL));
			getCpChatEntradaLeft().setLayout(new RowLayout(Orientation.VERTICAL));
			cpEntradaChat.add(getCpChatEntradaLeft(), new RowData(1.0, 1.0, new Margins()));
			getCpChatEntraRigth().setLayout(new AbsoluteLayout());
			cpEntradaChat.add(getCpChatEntraRigth(), new RowData(Style.DEFAULT, 1.0, new Margins()));
		}
		return cpEntradaChat;
	}
	private ListView getListView() {
		if (listView == null) {
			listView = new ListView(new ListStore());
		}
		return listView;
	}
	private ContentPanel getCpChatEntradaLeft() {
		if (cpChatEntradaLeft == null) {
			cpChatEntradaLeft = new ContentPanelImp();
			cpChatEntradaLeft.setBodyBorder(false);
			cpChatEntradaLeft.setHeaderVisible(false);
			cpChatEntradaLeft.setHeading("New ContentPanel");
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
			cpChatEntraRigth.setBodyBorder(false);
			cpChatEntraRigth.setHeaderVisible(false);
			cpChatEntraRigth.setWidth("120px");
			cpChatEntraRigth.setHeading("New ContentPanel");
			cpChatEntraRigth.setCollapsible(true);
			cpChatEntraRigth.add(getBtRegistrar(), new AbsoluteData(10, 76));
			cpChatEntraRigth.add(getCbParaOVeculo(), new AbsoluteData(10, 48));
		}
		return cpChatEntraRigth;
	}
	private ContentPanel getCpEntradaChatTop() {
		if (cpEntradaChatTop == null) {
			cpEntradaChatTop = new ContentPanelImp();
			cpEntradaChatTop.setHeaderVisible(false);
			cpEntradaChatTop.setBodyBorder(false);
			cpEntradaChatTop.setSize("", "22px");
			cpEntradaChatTop.setHeading("New ContentPanel");
			cpEntradaChatTop.setCollapsible(true);
			cpEntradaChatTop.setLayout(new AbsoluteLayout());
			cpEntradaChatTop.setTopComponent(getLbPainelLeds());
		}
		return cpEntradaChatTop;
	}
	private ContentPanel getCpEntradaChatBottom() {
		if (cpEntradaChatBottom == null) {
			cpEntradaChatBottom = new ContentPanelImp();
			cpEntradaChatBottom.setHeaderVisible(false);
			cpEntradaChatBottom.setBodyBorder(false);
			cpEntradaChatBottom.setHeading("New ContentPanel");
			cpEntradaChatBottom.setCollapsible(true);
			cpEntradaChatBottom.add(getTaEntradaChat());
		}
		return cpEntradaChatBottom;
	}
	private TextArea getTaEntradaChat() {
		if (taEntradaChat == null) {
			taEntradaChat = new TextArea();
			taEntradaChat.setFieldLabel("New TextArea");
		}
		return taEntradaChat;
	}
	private LabelField getLbPainelLeds() {
		if (lbPainelLeds == null) {
			lbPainelLeds = new LabelField("Painel de Leds: ");
		}
		return lbPainelLeds;
	}
	private Button getBtRegistrar() {
		if (btRegistrar == null) {
			btRegistrar = new Button("Registrar");
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


	private Widget getMapPosition() {

		LatLng fortalCity = LatLng.newInstance(-3.736549, -38.523804);
		mapLocation = new MapWidget();

		mapLocation.setCenter(fortalCity);
		mapLocation.checkResizeAndCenter();
		mapLocation.setSize("100%", "100%");

		return mapLocation;

	}

}

