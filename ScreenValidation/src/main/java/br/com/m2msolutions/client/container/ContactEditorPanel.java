package br.com.m2msolutions.client.container;

import br.com.mr.dock.client.containers.Position;

import com.extjs.gxt.ui.client.Style.Direction;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.DragEvent;
import com.extjs.gxt.ui.client.event.DragListener;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ContactEditorPanel extends ContentPanelImp {

	private static final EventType OnSelectedMessage = new EventType();
	private static final EventType OnRemoveMessage = new EventType();
	private static final EventType OnSaveNewMessage = new EventType();
	private static final EventType OnEditMessage = new EventType();
	private static final EventType OnUpdateMessage = new EventType();

	private Component anchor;
	private Window anchorMoveAndClose;
	private Position position;

	private FormPanel formContact;
	private TextField<String> fieldName;
	private TextField<String> fieldPhone;
	private TextField<String> fieldEmail;

	public ContactEditorPanel() {
		initComponents();
	}

	private void initComponents() {
		setHeaderVisible(false);
		setSize("384px", "186px");
		setLayout(new FitLayout());
		add(getFormContact());
	}

	private void hide(boolean slide) {
		if (slide) {
			el().slideOut(Direction.UP, FxConfig.NONE);
		} else {
			super.hide();
		}
	}

	public void hide() {
		hide(true);
	}

	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		getBody().setStyleName("m2m-borders-panel");
	}

	public void show() {
//		super.show();
		reposition();
		if (!isAttached()) {
			RootPanel.get().add(this);
		}
		el().makePositionable(true);

		this.setStyleAttribute("z-index", "" + anchorMoveAndClose.el().getZIndex() + 5);
		this.setStyleAttribute("opacity", "0.976543");
		this.el().dom.setAttribute("opacity", "0.976543");
		el().slideIn(Direction.DOWN, FxConfig.NONE);
	}

	public void anchorAt(final ContentPanelImp anchor, Position position) {
		this.anchor = anchor;
		anchor.addListener(Events.Minimize, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {

			}
		});

		anchor.addListener(Events.Close, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {

			}
		});
		anchor.addListener(Events.Resize, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {

			}
		});
		// anchor.addListener(Events.Move, new Listener<BaseEvent>() {
		// @Override
		// public void handleEvent(BaseEvent be) {
		// if (isVisible()) {
		// setPosition(14 + anchor.getAbsoluteLeft() + anchor.getOffsetWidth(),
		// anchor.getAbsoluteTop());
		// }
		// }
		// });
		this.position = position;
	}

	public void setWindowAnchor(Widget parent) {

	}

	public Window getAnchorMoveAndClose() {
		return anchorMoveAndClose;
	}

	private void reposition() {
		setPosition(14 + anchor.getAbsoluteLeft(), anchor.getAbsoluteTop() + 27);
	}

	public void setAnchorMoveAndClose(Window anchorMoveAndClose) {
		this.anchorMoveAndClose = anchorMoveAndClose;
		anchorMoveAndClose.getDraggable().addDragListener(new DragListener() {
			@Override
			public void dragMove(DragEvent de) {
				super.dragMove(de);
				setPosition(16 + de.getComponent().getAbsoluteLeft() + de.getComponent().getOffsetWidth(), de.getComponent().getAbsoluteTop() + de.getComponent().getOffsetHeight()
						- ContactEditorPanel.this.getHeight());
			}
		});

		anchorMoveAndClose.addListener(Events.Move, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				reposition();
			}
		});
		anchorMoveAndClose.addListener(Events.Resize, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				reposition();
			}

		});
		anchorMoveAndClose.addListener(Events.Hide, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				ContactEditorPanel.this.hide(false);
			}
		});
	}

	public void addSelectMessageListener(Listener<SlidePanelEvent> listener) {
		addListener(OnSelectedMessage, listener);
	}

	public void addSaveNewMessageListener(Listener<SlidePanelEvent> listener) {
		addListener(OnSaveNewMessage, listener);
	}

	public void addRemoveMessageListener(Listener<SlidePanelEvent> listener) {
		addListener(OnRemoveMessage, listener);
	}

	public void addEditMessageListener(Listener<SlidePanelEvent> listener) {
		addListener(OnEditMessage, listener);
	}

	public void addUpdateMessageListener(Listener<SlidePanelEvent> listener) {
		addListener(OnUpdateMessage, listener);
	}

	private FormPanel getFormContact() {
		if (formContact == null) {
			formContact = new FormPanel();
			formContact.setHeight("183px");
			formContact.setLabelAlign(LabelAlign.TOP);
			formContact.setHeaderVisible(false);
			formContact.setHeading("New FormPanel");
			formContact.setCollapsible(true);
			formContact.add(getFieldName(), new FormData("100%"));
			formContact.add(getFieldPhone(), new FormData("100%"));
			formContact.add(getFieldEmail(), new FormData("100%"));
			formContact.addButton(createButtonCancel());
			formContact.addButton(createButtonOk());
		}
		return formContact;
	}

	private Button createButtonOk() {

		return new Button("Ok");
	}

	private Button createButtonCancel() {
		// TODO Auto-generated method stub
		return new Button("Cancelar");
	}

	private TextField<String> getFieldName() {
		if (fieldName == null) {
			fieldName = new TextField<String>();
			fieldName.setFieldLabel("Nome");
		}
		return fieldName;
	}

	private TextField<String> getFieldPhone() {
		if (fieldPhone == null) {
			fieldPhone = new TextField<String>();
			fieldPhone.setFieldLabel("Telefone");
		}
		return fieldPhone;
	}

	private TextField<String> getFieldEmail() {
		if (fieldEmail == null) {
			fieldEmail = new TextField<String>();
			fieldEmail.setFieldLabel("E-mail");
		}
		return fieldEmail;
	}
}
