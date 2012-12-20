package br.com.m2msolutions.client;

import java.util.List;

import br.com.m2msolutions.client.container.TransferEvent;
import br.com.m2msolutions.shared.dto.DtoOperator;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;

//TODO Internacionalização
public class DialogWindowTransfer extends Window {
	private static final EventType ClickCancelListener = new EventType();
	private static EventType ClickOkListener = new EventType();

	private Text txtNewText;
	private ComboBox<DtoOperator> cmbOperador;

	private DtoOperator operator;

	public DialogWindowTransfer() {
		initComponents();
	}

	private void initComponents() {
		setSize("290px", "163px");
		// setButtons(Dialog.OKCANCEL);
		setResizable(false);
		setModal(true);
		setHeading("Transferencia de Atendimento");
		setLayout(new AbsoluteLayout());
		add(getTxtNewText(), new AbsoluteData(6, 24));
		add(getCmbOperador(), new AbsoluteData(6, 56));
		addButton(createOkButton());
		addButton(createCancelButton());
		addListener(Events.Attach, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				operator = null;
			}
		});
	}

	private Button createCancelButton() {
		Button cancel = new Button("Cancelar");
		cancel.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				fireEvent(ClickCancelListener);
				hide();
			}
		});
		return cancel;
	}

	private Button createOkButton() {
		final Button ok = new Button("Ok");
		ok.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (operator != null) {
					TransferEvent event = new TransferEvent(ok, operator);
					fireEvent(ClickOkListener, event);
				}
			}
		});
		return ok;
	}

	private Text getTxtNewText() {
		if (txtNewText == null) {
			txtNewText = new Text("Escolha a baixo o operador para o qual deja transferir o atendimento:");
			txtNewText.setSize("266px", "13px");
		}
		return txtNewText;
	}

	private ComboBox<DtoOperator> getCmbOperador() {
		if (cmbOperador == null) {
			cmbOperador = new ComboBox<DtoOperator>();
			cmbOperador.setSize("259px", "22px");
			cmbOperador.setStore(new ListStore<DtoOperator>());
			cmbOperador.addSelectionChangedListener(new SelectionChangedListener<DtoOperator>() {
				public void selectionChanged(SelectionChangedEvent<DtoOperator> se) {
					operator = se.getSelectedItem();
				}
			});
			cmbOperador.setFieldLabel("New ComboBox");
		}
		return cmbOperador;
	}

	public void updateOperators(List<DtoOperator> operators) {
		cmbOperador.getStore().removeAll();
		cmbOperador.getStore().add(operators);
	}

	public void addClickCancelListener(Listener<TransferEvent> listener) {
		addListener(ClickCancelListener, listener);
	}

	public void addClickOkListener(Listener<TransferEvent> listener) {
		addListener(ClickOkListener, listener);
	}
}
