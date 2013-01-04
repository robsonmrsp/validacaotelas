package br.com.m2msolutions.client.container;

import br.com.m2msolutions.client.images.Images;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FxEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.fx.FxConfig;
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

	private FxConfig config = createConfigFX();

	private FxConfig createConfigFX() {
		config = new FxConfig();
		config.setEffectStartListener(new Listener<FxEvent>() {
			@Override
			public void handleEvent(FxEvent be) {
				// disableImage = true;
			}
		});
		config.setEffectCompleteListener(new Listener<FxEvent>() {
			@Override
			public void handleEvent(FxEvent be) {
				setEventQtd_(++eventQtd);
				if (rendered)
					htmlQtd.el().fadeIn(FxConfig.NONE);
			}
		});
		return config;
	}

	public void setImage(String url) {
		if (image == null) {
			image = new Image();
		}
		image.setUrl(url);
	}
	
	@SuppressWarnings("deprecation")
	public void setImage(ImageResource image) {
		setImage(image.getURL());
	}

//	public void setImage(SafeUri imageSrc) {
//		if (image == null) {
//			image = new Image();
//		}
//		image.setUrl(imageSrc);
//	}

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
		addListener(Events.Attach, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				setEventQtd_(eventQtd);
			}
		});
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
			image.setSize("16px", "16px");
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

	private void setEventQtd_(int eventQtd) {
		htmlQtd.setHtml("<div>" + eventQtd + "</div>");
	}

	public void setEventQtd(int eventQtd) {
		this.eventQtd = eventQtd;
		if (rendered)
			htmlQtd.el().fadeOut(config);
	}
}
