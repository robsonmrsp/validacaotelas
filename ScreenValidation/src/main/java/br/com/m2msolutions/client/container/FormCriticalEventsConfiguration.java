package br.com.m2msolutions.client.container;

import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;

public class FormCriticalEventsConfiguration extends Window{
	private Button buttonCancel;
	private PanelCriticalEventsConfiguration criticalEventsConfiguration;
	public FormCriticalEventsConfiguration() {
		initComponents();
	}
	private void initComponents() {
		setHeading("Configuração de Eventos Criticos");
		setMinimizable(true);
		setMaximizable(true);
		setClosable(true);
		setSize(422, 470);
		add(getCriticalEventsConfiguration());
	}
	private PanelCriticalEventsConfiguration getCriticalEventsConfiguration() {
		if (criticalEventsConfiguration == null) {
			criticalEventsConfiguration = new PanelCriticalEventsConfiguration();
			criticalEventsConfiguration.setHeight("435");
//			criticalEventsConfiguration.setSize(425, 470);
			
		}
		return criticalEventsConfiguration;
	}
}
