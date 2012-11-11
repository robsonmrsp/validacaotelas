package br.com.m2msolutions.client;

import br.com.m2msolutions.client.container.AttendanceWindow;
import br.com.m2msolutions.client.container.CriticalEventsWidget;
import br.com.m2msolutions.client.images.Images;
import br.com.mr.dock.client.DockDesktop;
import br.com.mr.dock.client.menu.DockSelectionAction;

import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScreenValidation implements EntryPoint {

	@Override
	public void onModuleLoad() {
		AttendanceWindow attendanceWindow = new AttendanceWindow();
//		AdvancedFormsExample attendanceWindow = new AdvancedFormsExample();
		attendanceWindow.setAutoWidth(true);
//		attendanceWindow.setSize(1015, 500);
		
		Window window = new Window();
		window.setMaximizable(true);
		window.setMinimizable(true);
		window.setLayout(new FitLayout());
		window.setSize(1015, 550);
		
		window.add(attendanceWindow);
		window.show();
	}

	public void onModuleLoad_() {

		RootPanel rootPanel = RootPanel.get();

		DockDesktop dockDesktop = new DockDesktop();
		final CriticalEventsWidget attendance = new CriticalEventsWidget();
		dockDesktop.getBottomDock().addItem(Images.INSTANCE.settings128().getURL(), "Configurações", new DockSelectionAction() {
			@Override
			public void action() {
				// attendance.show();
			}
		});

		dockDesktop.getBottomDock().addItem(Images.INSTANCE.form128().getURL(), "Cadastros", new DockSelectionAction() {
			@Override
			public void action() {
				// attendance.show();
			}
		});

		dockDesktop.getBottomDock().addItem(Images.INSTANCE.report128().getURL(), "Relatórios", new DockSelectionAction() {
			@Override
			public void action() {
				// attendance.show();
			}
		});

		dockDesktop.getBottomDock().addItem(Images.INSTANCE.attendance128().getURL(), "Eventos Criticos", new DockSelectionAction() {
			@Override
			public void action() {
				if (!attendance.isVisible())
					attendance.show();
			}
		});

		dockDesktop.getBottomDock().addItem(Images.INSTANCE.map128().getURL(), "Monitoramento", new DockSelectionAction() {
			@Override
			public void action() {
				// attendance.show();

			}
		});

		dockDesktop.getBottomDock().addItem(Images.INSTANCE.index128().getURL(), "Indices", new DockSelectionAction() {
			@Override
			public void action() {
				// attendance.show();
			}
		});

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