package br.com.m2msolutions.client;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.Image;

public class FormLogin extends Window {
	protected static final EventType CheckLoginEvent = new EventType();
	private Image image;
	private LayoutContainer layoutContainer;
	private TextField textBox;
	private Image image_1;

	public FormLogin() {
		initComponents();
	}

	private void initComponents() {
		setClosable(false);
		setSize(336, 174);
		setLayout(new AbsoluteLayout());
		add(getImage(), new AbsoluteData(125, 6));
		getLayoutContainer().setLayout(new AbsoluteLayout());
		add(getLayoutContainer(), new AbsoluteData(6, 84));
	}

	private Image getImage() {
		if (image == null) {
			image = new Image("http://cdn1.iconfinder.com/data/icons/Futurosoft%20Icons%200.5.2/72x72/apps/Login%20Manager.png");
			image.setSize("72", "72");
		}
		return image;
	}

	private LayoutContainer getLayoutContainer() {
		if (layoutContainer == null) {
			layoutContainer = new LayoutContainer();
			layoutContainer.setSize("305px", "39px");
			layoutContainer.setBorders(true);
			layoutContainer.add(getTextBox(), new AbsoluteData(68, 6));
			layoutContainer.add(getImage_1(), new AbsoluteData(213, 7));
		}
		return layoutContainer;
	}

	private TextField getTextBox() {
		if (textBox == null) {
			textBox = new TextField();
			textBox.setSize("143px", "24px");
			textBox.setPassword(true);
			textBox.getElement().setAttribute("placeholder", "Senha");
			textBox.addKeyListener(new KeyListener() {
				@Override
				public void componentKeyPress(ComponentEvent event) {
					super.componentKeyPress(event);
					if (event.getKeyCode() == KeyCodes.KEY_ENTER) {
						LoginEvent e = new LoginEvent(this, textBox.getRawValue());
						fireEvent(CheckLoginEvent, e);
					}
				}
			});

		}
		return textBox;
	}

	public void addValidateLoginListener(Listener<LoginEvent> listener) {
		addListener(CheckLoginEvent, listener);
	}

	private Image getImage_1() {
		if (image_1 == null) {
			image_1 = new Image("http://cdn1.iconfinder.com/data/icons/icojoy/noshadow/standart/gif/24x24/001_06.gif");
			image_1.setSize("24px", "24px");
			image_1.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent eve) {
					LoginEvent event = new LoginEvent(this, textBox.getRawValue());
					fireEvent(CheckLoginEvent, event);
				}
			});
		}
		return image_1;
	}

	public void invalidate() {
		textBox.clear();
	}
}
