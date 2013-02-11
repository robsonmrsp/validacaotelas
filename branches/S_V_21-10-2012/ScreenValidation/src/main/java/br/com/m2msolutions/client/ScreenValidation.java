package br.com.m2msolutions.client;

import br.com.m2msolutions.client.container.CriticalEventsWidget;
import br.com.m2msolutions.client.container.FormCriticalEventsAttendance;
import br.com.m2msolutions.client.container.FormCriticalEventsConfiguration;
import br.com.m2msolutions.client.container.FormInquiryCriticalEventsAttendance;
import br.com.m2msolutions.client.images.Images;
import br.com.m2msolutions.client.sinotic.container.SinoticContainer;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.mr.dock.client.DockDesktop;
import br.com.mr.dock.client.SinoticDesktop;
import br.com.mr.dock.client.menu.DockSelectionAction;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScreenValidation implements EntryPoint {

	private SinoticContainer container = new SinoticContainer();

	// usado apenas em testes de layout rápido
	public void on_ModuleLoad() {
		final CriticalEventsWidget attendance = createCriticalEventsWidget();
		// RootPanel.get().add(attendance);
		attendance.show();
	}

	public void onModuleL_oad() {
		Window.setMargin("0px");
		initAppSinotic();
	}

	public void onModuleLoad() {
		Window.setMargin("0px");

		final FormLogin formLogin = new FormLogin();
		formLogin.addValidateLoginListener(new Listener<LoginEvent>() {

			@Override
			public void handleEvent(LoginEvent be) {
				if (be.getPassValue().equals("m2mant")) {
					formLogin.hide();
					initAppSinotic();
					// initApp();
				} else {
					formLogin.invalidate();
				}
			}
		});
		formLogin.show();
	}

	private void initAppSinotic() {
		RootPanel rootPanel = RootPanel.get();
		SinoticDesktop dockDesktop = new SinoticDesktop();
		dockDesktop.addWindow(container);
		dockDesktop.addTaskButton(createShowSinotic());
		rootPanel.add(dockDesktop);
	}

	private Component createShowSinotic() {
		Button button = new Button("Sinotico");
		button.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				container.show();
			}
		});
		return button;
	}

	private void initApp() {
		DockDesktop dockDesktop = new DockDesktop();
		RootPanel rootPanel = RootPanel.get();

		final CriticalEventsWidget attendance = createCriticalEventsWidget();

		dockDesktop.getBottomDock().addItem(Images.INSTANCE.settings128().getURL(), "Configurações", new DockSelectionAction() {
			@Override
			public void action() {
				showCriticalEventsConfiguration();
			}

		});

		dockDesktop.getBottomDock().addItem(Images.INSTANCE.form128().getURL(), "Velha consulta atendimento", new DockSelectionAction() {
			@Override
			public void action() {
				showInquiryAttendanceWindow();
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
				showCriticalEventsAttendance();
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

	/**
	 * Tela de atendimento de eventos criticos.
	 */
	protected void showCriticalEventsAttendance() {
		FormCriticalEventsAttendance criticalEventAttendance = new FormCriticalEventsAttendance();
		criticalEventAttendance.show();
	}

	/**
	 * monta o Widget e o retorna para uso
	 * 
	 * @return o widget de eventos críticos propriamente dito.
	 */
	private CriticalEventsWidget createCriticalEventsWidget() {
		CriticalEventsWidget attendance = new CriticalEventsWidget();
		attendance.addAttendanceListener(new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				showInquiryAttendanceWindow();
			}
		});

		attendance.addActionOnDobleClick(new Listener<GridEvent<DtoCriticalEvent>>() {
			public void handleEvent(final GridEvent<DtoCriticalEvent> ge) {
				DtoCriticalEvent dtoEvent = ge.getModel();
				if (dtoEvent != null) {
					FormCriticalEventsAttendance criticalEventAttendancePanel = new FormCriticalEventsAttendance();
					criticalEventAttendancePanel.setEvent(dtoEvent);
					criticalEventAttendancePanel.show();
				}
			}
		});

		return attendance;
	}

	/**
	 * Tela de consulta de atendimento
	 */
	private void showInquiryAttendanceWindow() {
		FormInquiryCriticalEventsAttendance inquiryCriticalEventsAttendance = new FormInquiryCriticalEventsAttendance();
		inquiryCriticalEventsAttendance.show();

	}

	/**
	 * Tela de configuração de eventos críticos.
	 */
	private void showCriticalEventsConfiguration() {
		FormCriticalEventsConfiguration configuration = new FormCriticalEventsConfiguration();
		configuration.show();
	}

}
