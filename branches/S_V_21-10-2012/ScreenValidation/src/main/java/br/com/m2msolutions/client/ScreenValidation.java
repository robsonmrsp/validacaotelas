package br.com.m2msolutions.client;

import br.com.m2msolutions.client.container.ContentPanelImp;
import br.com.m2msolutions.client.container.CopyOfCriticalEventAttendancePanel;
import br.com.m2msolutions.client.container.CriticalEventsWidget;
import br.com.m2msolutions.client.container.QueryAttendanceWindow;
import br.com.m2msolutions.client.images.Images;
import br.com.m2msolutions.shared.dto.DtoEvent;
import br.com.mr.dock.client.DockDesktop;
import br.com.mr.dock.client.menu.DockSelectionAction;

import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.fx.Draggable;
import com.extjs.gxt.ui.client.fx.Resizable;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScreenValidation implements EntryPoint {

	// public void onModuleLoad() {
	// GridExample example = new GridExample();
	// RootPanel.get().add(example);
	// }
	// public void onModuleLoad() {
	// CopyOfAttendanceWindow attendanceWindow = new CopyOfAttendanceWindow();
	// // AdvancedFormsExample attendanceWindow = new AdvancedFormsExample();
	// attendanceWindow.setAutoWidth(true);
	// // attendanceWindow.setSize(1015, 500);
	//
	// Window window = new Window();
	// window.setMaximizable(true);
	// window.setMinimizable(true);
	// window.setLayout(new FitLayout());
	// window.setSize(1015, 550);
	//
	// window.add(attendanceWindow);
	// window.show();
	// }

	public void onModule_Load() {
		final CriticalEventsWidget attendance = new CriticalEventsWidget();
		RootPanel.get().add(attendance);
		attendance.show();
	}

	public void onModuleLoad() {

		RootPanel rootPanel = RootPanel.get();

		DockDesktop dockDesktop = new DockDesktop();
		final CriticalEventsWidget attendance = createCriticalEventsWidget();

		dockDesktop.getBottomDock().addItem(Images.INSTANCE.settings128().getURL(), "Configurações", new DockSelectionAction() {
			@Override
			public void action() {
				// attendance.show();
			}
		});

		dockDesktop.getBottomDock().addItem(Images.INSTANCE.form128().getURL(), "Velha consulta atendimento", new DockSelectionAction() {
			@Override
			public void action() {
				queryAttendanceWindowOld().show();
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

				CopyOfCriticalEventAttendancePanel criticalEventAttendancePanel = new CopyOfCriticalEventAttendancePanel();

				Window window = new Window();
				window.setMaximizable(true);
				window.setMinimizable(true);
				window.setLayout(new FitLayout());
				window.setSize(850, 600);

				window.add(criticalEventAttendancePanel);
				window.show();

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

	private CriticalEventsWidget createCriticalEventsWidget() {
		CriticalEventsWidget attendance = new CriticalEventsWidget();
		attendance.addActionOnDobleClick(new Listener<GridEvent<DtoEvent>>() {
			public void handleEvent(final GridEvent<DtoEvent> ge) {
				DtoEvent dtoEvent = ge.getModel();

				if (dtoEvent != null) {
					CopyOfCriticalEventAttendancePanel criticalEventAttendancePanel = new CopyOfCriticalEventAttendancePanel();
					criticalEventAttendancePanel.setEvent(dtoEvent);
					Window window = new Window();
					window.setMaximizable(true);
					window.setMinimizable(true);
					window.setLayout(new FitLayout());
					window.setSize(850, 600);

					window.add(criticalEventAttendancePanel);
					window.show();
				}
			}
		});

		return attendance;
	}

	public void onModuleLoad___() {
		ContentPanel formUser = new ContentPanelImp();
		// formUser.getHeader().addTool(new Button("Z"));
		// formUser.getHeader().addTool(new Button("A"));
		// formUser.getHeader().addTool(new Button("X"));
		Draggable d = new Draggable(formUser);
		formUser.show();
		formUser.setSize(310, 130);
		RootPanel.get().add(formUser);
	}

	private Window queryAttendanceWindowOld() {
		QueryAttendanceWindow attendanceWindow = new QueryAttendanceWindow();
		attendanceWindow.setAutoWidth(true);

		Window window = new Window();
		window.setBodyBorder(false);
		window.setBorders(false);

		window.setMaximizable(true);
		window.setMinimizable(true);
		window.setLayout(new FitLayout());
		window.setSize(1015, 550);

		window.add(attendanceWindow);
		return window;
	}
}
