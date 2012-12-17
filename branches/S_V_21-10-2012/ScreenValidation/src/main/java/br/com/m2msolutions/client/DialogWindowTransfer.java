package br.com.m2msolutions.client;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;

public class DialogWindowTransfer extends Dialog {
	private Text txtNewText;
	private ComboBox cmbOperador;

	public DialogWindowTransfer() {
		initComponents();
	}
	private void initComponents() {
		setSize("290px", "163px");
		setButtons(Dialog.OKCANCEL);
		setResizable(false);
		setModal(true);
		setHeading("Transferencia de Atendimento");
		setLayout(new AbsoluteLayout());
		add(getTxtNewText(), new AbsoluteData(6, 24));
		add(getCmbOperador(), new AbsoluteData(6, 56));
	}
	private Text getTxtNewText() {
		if (txtNewText == null) {
			txtNewText = new Text("Escolha a baixo o operador para o qual deja transferir o atendimento:");
			txtNewText.setSize("266px", "13px");
		}
		return txtNewText;
	}
	private ComboBox getCmbOperador() {
		if (cmbOperador == null) {
			cmbOperador = new ComboBox();
			cmbOperador.setSize("259px", "22px");
			cmbOperador.setStore(new ListStore());
			cmbOperador.setFieldLabel("New ComboBox");
		}
		return cmbOperador;
	}
}
