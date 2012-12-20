package br.com.m2msolutions.client.container;

import br.com.m2msolutions.shared.dto.DtoCriticalEvent;

import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

//TODO essa tela irá mudar para herdar de classe do Labirinthus
public class FormCriticalEventsAttendance extends Window {
	private PanelCriticalEventsAttendance criticalEventsAttendance;

	public FormCriticalEventsAttendance() {
		initComponents();
	}

	private void initComponents() {
		setHeading("Atendimento");
		setMinimizable(true);
		setMaximizable(true);
		setClosable(true);
		setLayout(new FitLayout());
		setSize(850, 600);

		add(getCriticalEventsConfiguration());
	}

	private PanelCriticalEventsAttendance getCriticalEventsConfiguration() {
		if (criticalEventsAttendance == null) {
			criticalEventsAttendance = new PanelCriticalEventsAttendance();
			criticalEventsAttendance.setAnchorSlidePanel(this);
			criticalEventsAttendance.setHeight("435");
		}
		return criticalEventsAttendance;
	}

	public void setEvent(DtoCriticalEvent dtoEvent) {
		criticalEventsAttendance.setEvent(dtoEvent);
	}
}
