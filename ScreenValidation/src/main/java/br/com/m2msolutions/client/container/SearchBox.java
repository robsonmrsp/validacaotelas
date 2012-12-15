package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.TextBox;

public class SearchBox extends LayoutContainer {

	private TextBox searchBox;

	public SearchBox() {
		initComponents();
	}

	@Override
	public void setSize(String width, String height) {
		super.setSize(width, "24px");
		searchBox.setWidth(width);
	}

	public void setSize(int width, int height) {
		super.setSize(width, 24);
		searchBox.setWidth(width - 5 + "px");
	}

	private void initComponents() {
//		setSize("150px", "24px");
		setLayout(new FitLayout());
		add(getSearchBox());
		setWidth("150px");
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

	public void setChangeListener(final Listener<SearchBoxEvent> listener) {
		// final BaseEvent be = new BaseEvent(Events.KeyPress);
		// be.setSource(this);

		searchBox.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (length() > 2 || length() == 0) {
					listener.handleEvent(new SearchBoxEvent(this, getValue()));
				}
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
