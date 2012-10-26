package br.com.mr.dock.client.menu;


import br.com.m2msolutions.client.images.Images;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;

public class DockItem {

	private Image image;
	private HTML text = new HTML("Item");
	private LayoutContainer div = new LayoutContainer();;

	public DockItem(String imageSrc, String textValue) {

		image = new Image(imageSrc);
		text = new HTML("<div class=\"item-text\">" + textValue + "</div>");
		div.setLayout(new FlowLayout());
		div.setBorders(true);
		div.add(new Html(textValue + "novo"));
		text.getElement().getStyle().setDisplay(Display.NONE);

		text.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
//				text.getElement().getStyle().setDisplay(Display.BLOCK);
//				html2.show();
			}
		});

		image.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				text.getElement().getStyle().setDisplay(Display.BLOCK);
//				html2.show();
			}
		});
		image.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent event) {
				text.getElement().getStyle().setDisplay(Display.NONE);
			}
		});
	}

	public DockItem(String imageSrc, String text, DockSelectionAction action) {
		this(imageSrc, text);
		addSelectionAction(action);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void addSelectionAction(final DockSelectionAction dockSelectionAction) {

		image.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (dockSelectionAction != null) {
					dockSelectionAction.action();
				}
			}
		});
	}

	public HTML getText() {
		return text;
	}

	public LayoutContainer getHtml2() {
		return div;
	}
}
