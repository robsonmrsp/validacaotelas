package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.widget.Window;

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
		setSize(422, 470);
		add(getCriticalEventsConfiguration());
	}

	private PanelInquiryCriticalEventsAttendance getCriticalEventsConfiguration() {
		if (inquiryCriticalEventAttendance == null) {
			inquiryCriticalEventAttendance = new PanelInquiryCriticalEventsAttendance();

		}
		return inquiryCriticalEventAttendance;
	}
}
