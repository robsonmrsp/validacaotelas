package br.com.mr.storageposition.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class StoragePosition implements EntryPoint {
	@Override
	public void onModuleLoad() {
		
		Principal principal = new Principal();
		RootPanel.get().add(principal);
	}
}
