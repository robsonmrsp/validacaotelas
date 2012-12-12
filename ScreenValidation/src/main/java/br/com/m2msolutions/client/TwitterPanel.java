package br.com.m2msolutions.client;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.store.ListStore;

public class TwitterPanel extends LayoutContainer {
	private ListView<DTOTwitter> listView;

	public TwitterPanel() {
		initComponents();
	}

	private void initComponents() {
		add(getListView());
	}

	private ListView<DTOTwitter> getListView() {
		if (listView == null) {
			listView = new ListView<DTOTwitter>(createStore());
			listView.setTemplate(getTemplate());
		}
		return listView;
	}

	private ListStore<DTOTwitter> createStore() {
		return new ListStore<DTOTwitter>();
	}

	private String getTemplate() {
		return "";
	}
}
