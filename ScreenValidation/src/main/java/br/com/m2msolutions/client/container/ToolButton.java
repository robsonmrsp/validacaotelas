package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

public class ToolButton extends LayoutContainer {
	private Image image;
	private static EventType OnClick = new EventType();

	public ToolButton(ImageResource resource, Listener<BaseEvent> listener) {
		super();
		initComponents();
		setImage(resource);
		if (listener != null)
			addClickListener(listener);
	}

	public ToolButton(ImageResource imageResource) {
		this(imageResource, null);
	}

	public void setImage(ImageResource resource) {
		image.setResource(resource);
	}

	public void addClickListener(Listener<BaseEvent> listener) {
		addListener(OnClick, listener);
	}

	private void initComponents() {
		setSize("18", "18");
		setStyleAttribute("cursor", "pointer");
		setLayout(new CenterLayout());
		add(getImage());
	}

	private Image getImage() {
		if (image == null) {
			image = new Image();
			image.setSize("16px", "16px");
			image.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					fireEvent(OnClick);
				}
			});
		}
		return image;
	}
}
