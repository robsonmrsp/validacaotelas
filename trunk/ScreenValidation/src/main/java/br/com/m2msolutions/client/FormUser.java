package br.com.m2msolutions.client;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FormUser extends Window {
	private TextField<String> fieldName;
	private TextField<String> fieldPass;
	private Button okButton;
	private Button cancelButton;

	public FormUser() {

		initComponents();
	}

	private void initComponents() {
		setLayout(new FormLayout());
		add(getFieldName(), new FormData("100%"));
		add(getFieldPass(), new FormData("100%"));
		addButton(getCancelButton());
		addButton(getOkButton());
	}

	private TextField<String> getFieldName() {
		if (fieldName == null) {
			fieldName = new TextField<String>();
			fieldName.setFieldLabel("Nome");
		}
		return fieldName;
	}

	private TextField<String> getFieldPass() {
		if (fieldPass == null) {
			fieldPass = new TextField<String>();
			fieldPass.setPassword(true);
			fieldPass.setFieldLabel("Senha");
		}
		return fieldPass;
	}


	private Button getOkButton() {
		if (okButton == null) {
			okButton = new Button("Ok");
			okButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					save(fieldName.getValue(), fieldPass.getValue());
				}
			});
		}
		;
		return okButton;
	}

	protected void save(String name, String pass) {

	}

	private Button getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new Button("Cancel");
		}
		return cancelButton;
	}
}
