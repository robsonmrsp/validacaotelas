package br.com.m2msolutions.client.container;

import br.com.m2msolutions.client.images.Images;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.Image;

public class LabeledImage extends LayoutContainer {
	private static final EventType ClickImage = new EventType();
	private Html htmlQtd;
	private int eventQtd;
	private Image image;
	private LayoutContainer qtdContainer;
	private LayoutContainer imageContainer;

	public void setImage(ImageResource image) {
		setImage(image.getSafeUri());
	}

	public void setImage(SafeUri imageSrc) {
		if (image == null) {
			image = new Image();
		}
		image.setUrl(imageSrc);
	}

	public LabeledImage() {
		initComponents();
	}

	private void initComponents() {
		setSize("53px", "24px");
		setLayout(new RowLayout(Orientation.HORIZONTAL));
		setSize(53, 24);
		setStyleName("labeled-image");
		add(createQtdContainer());
		add(createImageContainer());
	}

	private Html getHtmlQtd() {
		if (htmlQtd == null) {
			htmlQtd = new Html("0");
			htmlQtd.setSize("-1", "20");
		}
		return htmlQtd;
	}

	private Image getImage() {
		if (image == null) {
			image = new Image(Images.INSTANCE.interrogation18().getSafeUri());
			image.setSize("18px", "18px");
			image.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					onClickImage(event);
				}
			});
		}
		return image;
	}

	protected void onClickImage(ClickEvent event) {
		SelectionLabeledImageEvent imageEvent = new SelectionLabeledImageEvent(this);
		imageEvent.setEventsQtd(eventQtd);

		fireEvent(ClickImage, imageEvent);
	}

	public void addSelectionListener(Listener<SelectionLabeledImageEvent> listener) {
		addListener(ClickImage, listener);
	}

	private LayoutContainer createQtdContainer() {
		qtdContainer = new LayoutContainer();
		qtdContainer.setSize("20", "20");
		qtdContainer.setLayout(new CenterLayout());
		qtdContainer.add(getHtmlQtd());
		return qtdContainer;
	}

	private LayoutContainer createImageContainer() {
		imageContainer = new LayoutContainer();
		imageContainer.setSize("20", "20");
		imageContainer.setLayout(new CenterLayout());
		imageContainer.add(getImage());

		return imageContainer;
	}

	public int getEventQtd() {
		return eventQtd;
	}

	public void setEventQtd(int eventQtd) {
		htmlQtd.setHtml("<div>"+eventQtd+"</div>");		
		this.eventQtd = eventQtd;
	}
	

}
