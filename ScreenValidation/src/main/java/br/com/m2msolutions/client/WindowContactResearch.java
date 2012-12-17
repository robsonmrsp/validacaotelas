package br.com.m2msolutions.client;

import java.util.Collections;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.google.gwt.user.client.ui.TextBox;

public class WindowContactResearch extends Window {
	private Grid gridResultado;
	private Text txtCodigo;
	private TextBox txtbCodigo;
	private Text txtNome;
	private ComboBox cmbNome;
	private Text txtCargo;
	private ComboBox cmbCargo;
	private Text txtFuncao;
	private ComboBox cmbFuncao;
	private Button btnPesquisar;
	private Button btnAbrirCadastro;
	private Button btnOk;
	private Button btnCancelar;

	public WindowContactResearch() {
		initComponents();
	}
	private void initComponents() {
		setSize("381px", "413px");
		setModal(true);
		setHeading("Pesquisa de Contato");
		setLayout(new AbsoluteLayout());
		add(getGridResultado(), new AbsoluteData(6, 147));
		add(getTxtCodigo(), new AbsoluteData(6, 7));
		add(getTxtbCodigo(), new AbsoluteData(6, 26));
		add(getTxtNome(), new AbsoluteData(129, 6));
		add(getCmbNome(), new AbsoluteData(129, 24));
		add(getTxtCargo(), new AbsoluteData(6, 52));
		add(getCmbCargo(), new AbsoluteData(6, 71));
		add(getTxtFuncao(), new AbsoluteData(212, 52));
		add(getCmbFuncao(), new AbsoluteData(203, 71));
		add(getBtnPesquisar(), new AbsoluteData(303, 109));
		add(getBtnAbrirCadastro(), new AbsoluteData(6, 353));
		add(getBtnOk(), new AbsoluteData(308, 353));
		add(getBtnCancelar(), new AbsoluteData(245, 353));
	}
	private Grid getGridResultado() {
		if (gridResultado == null) {
			gridResultado = new Grid(new ListStore(), new ColumnModel(Collections.<ColumnConfig>emptyList()));
			gridResultado.setSize("357px", "196px");
			gridResultado.setBorders(true);
		}
		return gridResultado;
	}
	private Text getTxtCodigo() {
		if (txtCodigo == null) {
			txtCodigo = new Text("C\u00F3digo:");
		}
		return txtCodigo;
	}
	private TextBox getTxtbCodigo() {
		if (txtbCodigo == null) {
			txtbCodigo = new TextBox();
			txtbCodigo.setSize("113px", "16px");
		}
		return txtbCodigo;
	}
	private Text getTxtNome() {
		if (txtNome == null) {
			txtNome = new Text("Nome:");
		}
		return txtNome;
	}
	private ComboBox getCmbNome() {
		if (cmbNome == null) {
			cmbNome = new ComboBox();
			cmbNome.setSize("236px", "22px");
			cmbNome.setStore(new ListStore());
			cmbNome.setFieldLabel("New ComboBox");
		}
		return cmbNome;
	}
	private Text getTxtCargo() {
		if (txtCargo == null) {
			txtCargo = new Text("Cargo:");
			txtCargo.setSize("31px", "13px");
		}
		return txtCargo;
	}
	private ComboBox getCmbCargo() {
		if (cmbCargo == null) {
			cmbCargo = new ComboBox();
			cmbCargo.setSize("190px", "22px");
			cmbCargo.setStore(new ListStore());
			cmbCargo.setFieldLabel("New ComboBox");
		}
		return cmbCargo;
	}
	private Text getTxtFuncao() {
		if (txtFuncao == null) {
			txtFuncao = new Text("Fun\u00E7\u00E3o:");
			txtFuncao.setSize("31px", "13px");
		}
		return txtFuncao;
	}
	private ComboBox getCmbFuncao() {
		if (cmbFuncao == null) {
			cmbFuncao = new ComboBox();
			cmbFuncao.setSize("162px", "22px");
			cmbFuncao.setStore(new ListStore());
			cmbFuncao.setFieldLabel("New ComboBox");
		}
		return cmbFuncao;
	}
	private Button getBtnPesquisar() {
		if (btnPesquisar == null) {
			btnPesquisar = new Button("Pesquisar");
		}
		return btnPesquisar;
	}
	private Button getBtnAbrirCadastro() {
		if (btnAbrirCadastro == null) {
			btnAbrirCadastro = new Button("Abrir Cadastro");
		}
		return btnAbrirCadastro;
	}
	private Button getBtnOk() {
		if (btnOk == null) {
			btnOk = new Button("Ok");
			btnOk.setSize("55px", "22px");
		}
		return btnOk;
	}
	private Button getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new Button("Cancelar");
		}
		return btnCancelar;
	}
}
