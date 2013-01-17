package br.com.mr.storageposition.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;

public class StoragePosition implements EntryPoint {
	@Override
	public void onModuleLoad() {

		Principal principal = new Principal();
		MenuBar popupMenuBar = new MenuBar(true);
		MenuItem alertItem = new MenuItem("Ultima posição..", true, new Command() {
			@Override
			public void execute() {

			}
		});
		MenuItem imageItem = new MenuItem("Ultimas 10 posições...", true, new Command() {
			@Override
			public void execute() {
				
			}
		});

		alertItem.addStyleName("popup-item");
		imageItem.addStyleName("popup-item");

		popupMenuBar.addItem(alertItem);
		popupMenuBar.addItem(imageItem);

		RootPanel.get().add(principal);
	}
}
