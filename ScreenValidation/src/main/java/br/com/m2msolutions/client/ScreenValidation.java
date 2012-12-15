package br.com.m2msolutions.client;

import br.com.m2msolutions.client.container.CriticalEventsWidget;
import br.com.m2msolutions.client.container.FormCriticalEventsAttendance;
import br.com.m2msolutions.client.container.FormCriticalEventsConfiguration;
import br.com.m2msolutions.client.container.PanelCriticalEventsAttendance;
import br.com.m2msolutions.client.container.PanelInquiryCriticalEventsAttendance;
import br.com.m2msolutions.client.images.Images;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.mr.dock.client.DockDesktop;
import br.com.mr.dock.client.menu.DockSelectionAction;

import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScreenValidation implements EntryPoint {

	public void on_ModuleLoad() {
		final CriticalEventsWidget attendance = new CriticalEventsWidget();
		RootPanel.get().add(attendance);
		attendance.show();
	}

	public void onModuleLoad() {
		final FormLogin formLogin = new FormLogin();
		formLogin.addValidateLoginListener(new Listener<LoginEvent>() {

			@Override
			public void handleEvent(LoginEvent be) {
				if (be.getPassValue().equals("m2mant")) {
					formLogin.hide();
					initApp();
				} else {
					formLogin.invalidate();
				}
			}
		});
		formLogin.show();
	}

	private void initApp() {
		DockDesktop dockDesktop = new DockDesktop();
		RootPanel rootPanel = RootPanel.get();

		final CriticalEventsWidget attendance = createCriticalEventsWidget();

		dockDesktop.getBottomDock().addItem(Images.INSTANCE.settings128().getURL(), "Configurações", new DockSelectionAction() {
			@Override
			public void action() {
				showWidGetConfiguration();
			}

		});

		dockDesktop.getBottomDock().addItem(Images.INSTANCE.form128().getURL(), "Velha consulta atendimento", new DockSelectionAction() {
			@Override
			public void action() {
				queryAttendanceWindowO().show();
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
				showFormCriticalEventsAttendance();
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

	protected void showFormCriticalEventsAttendance() {
		FormCriticalEventsAttendance criticalEventAttendance = new FormCriticalEventsAttendance();
		criticalEventAttendance.show();
	}

	private CriticalEventsWidget createCriticalEventsWidget() {
		CriticalEventsWidget attendance = new CriticalEventsWidget();
		attendance.addActionOnDobleClick(new Listener<GridEvent<DtoCriticalEvent>>() {
			public void handleEvent(final GridEvent<DtoCriticalEvent> ge) {
				DtoCriticalEvent dtoEvent = ge.getModel();

				if (dtoEvent != null) {
					PanelCriticalEventsAttendance criticalEventAttendancePanel = new PanelCriticalEventsAttendance();
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

	

	private Window queryAttendanceWindowO() {
		PanelInquiryCriticalEventsAttendance attendanceWindow = new PanelInquiryCriticalEventsAttendance();
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

	private void showWidGetConfiguration() {
		FormCriticalEventsConfiguration configuration = new FormCriticalEventsConfiguration();
		configuration.show();
	}

}
