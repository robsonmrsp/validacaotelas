package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
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
}
