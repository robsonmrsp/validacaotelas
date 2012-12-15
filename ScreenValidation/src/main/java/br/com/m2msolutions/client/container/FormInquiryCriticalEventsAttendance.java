package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class FormInquiryCriticalEventsAttendance extends Window {
	private PanelInquiryCriticalEventsAttendance inquiryCriticalEventAttendance;

	public FormInquiryCriticalEventsAttendance() {
		initComponents();
	}

	private void initComponents() {
		setHeading("Consulta de atendimento");
		setMinimizable(true);
		setMaximizable(true);
		setClosable(true);
		setSize(1015, 550);
		setLayout(new FitLayout());
		add(getCriticalEventsConfiguration());
	}

	private PanelInquiryCriticalEventsAttendance getCriticalEventsConfiguration() {
		if (inquiryCriticalEventAttendance == null) {
			inquiryCriticalEventAttendance = new PanelInquiryCriticalEventsAttendance();

		}
		return inquiryCriticalEventAttendance;
	}
}
