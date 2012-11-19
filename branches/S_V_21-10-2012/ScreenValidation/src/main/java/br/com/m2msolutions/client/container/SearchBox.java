package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.TextBox;

public class SearchBox extends LayoutContainer {

	private TextBox searchBox;

	public SearchBox() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new RowLayout(Orientation.HORIZONTAL));
		add(getSearchBox());
	}

	private TextBox getSearchBox() {
		if (searchBox == null) {
			searchBox = new TextBox();
			searchBox.getElement().setId("search");
			searchBox.getElement().setAttribute("placeholder", "Search");
			searchBox.setStyleName("search-box");
		}
		return searchBox;
	}

	public void setTextWhenEmpty(String text) {
		searchBox.getElement().setAttribute("placeholder", text);
	}

	public void setChangeListener(final Listener<BaseEvent> listener) {
		final BaseEvent be = new BaseEvent(Events.KeyPress);
		be.setSource(this);
		searchBox.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				listener.handleEvent(be);
			}
		});
	}

	public String getValue() {
		return searchBox.getText();
	}

	public int length() {
		return searchBox.getText().length();
	}

}
