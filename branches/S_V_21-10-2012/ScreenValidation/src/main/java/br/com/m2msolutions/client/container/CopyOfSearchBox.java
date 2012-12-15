package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.TextBox;

public class CopyOfSearchBox extends LayoutContainer {

	private TextBox searchBox;

	public CopyOfSearchBox() {
		initComponents();
	}

	@Override
	public void setSize(String width, String height) {
		super.setSize(width, "26px");
		searchBox.setWidth(width);
	}

	public void setSize(int width, int height) {
		super.setSize(width, 26);
		searchBox.setWidth(width - 5 + "px");
	}

	private void initComponents() {
		setLayout(new CenterLayout());
		add(getSearchBox());
	}

	private TextBox getSearchBox() {
		if (searchBox == null) {
			searchBox = new TextBox();
			searchBox.getElement().setId("search");
			searchBox.getElement().getStyle().setLeft(0, Unit.PX);
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
