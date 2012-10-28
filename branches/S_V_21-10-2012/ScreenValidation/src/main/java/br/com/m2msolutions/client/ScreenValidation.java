package br.com.m2msolutions.client;

import br.com.m2msolutions.client.container.CriticalEventsWidget;
import br.com.m2msolutions.client.images.Images;
import br.com.mr.dock.client.DockDesktop;
import br.com.mr.dock.client.menu.DockSelectionAction;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScreenValidation implements EntryPoint {
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();

		DockDesktop dockDesktop = new DockDesktop();
		final CriticalEventsWidget attendance = new CriticalEventsWidget();
		dockDesktop.getBottomDock().addItem(Images.INSTANCE.attendance128().getURL(), "Atendimento", new DockSelectionAction() {
			@Override
			public void action() {
				attendance.show();
			}
		});
		
		// dockDesktop.getBottomDock().addItem(Images.INSTANCE.link128().getURL(),
		// "Links");
		// dockDesktop.getBottomDock().addItem(Images.INSTANCE.alert128().getURL(),
		// "Eventos");
		// dockDesktop.getBottomDock().addItem(Images.INSTANCE.rss128().getURL(),
		// "Rss");
		// dockDesktop.getBottomDock().addItem(Images.INSTANCE.calendar128().getURL(),
		// "Calendario");
		// dockDesktop.getBottomDock().addItem(Images.INSTANCE.alert128().getURL(),
		// "Eventos");
		// dockDesktop.getBottomDock().addItem(Images.INSTANCE.link128().getURL(),
		// "Links");
		// dockDesktop.getBottomDock().addItem(Images.INSTANCE.rss128().getURL(),
		// "Rss");
		// dockDesktop.getBottomDock().addItem(Images.INSTANCE.alert128().getURL(),
		// "Eventos");
		dockDesktop.addWindow(attendance);

		rootPanel.add(dockDesktop);
	}

	public void onModuleLoad2() {
		FormUser formUser = new FormUser();
		formUser.show();
		formUser.setSize(310, 130);
		RootPanel.get().add(formUser);
	}
}
