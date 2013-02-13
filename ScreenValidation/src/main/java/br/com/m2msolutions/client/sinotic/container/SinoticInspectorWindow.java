package br.com.m2msolutions.client.sinotic.container;

public class SinoticInspectorWindow extends BaseWindow {

	private PanelSinoticInspector panelSinoticInspector;

	public SinoticInspectorWindow() {
		initComponents();

	}

	private void initComponents() {
		setBodyComponent(getPanelSinoticInspector());
	}

	public PanelSinoticInspector getPanelSinoticInspector() {
		if (panelSinoticInspector == null) {
			panelSinoticInspector = new PanelSinoticInspector();
			panelSinoticInspector.setSize(310, 550);
		}
		return panelSinoticInspector;
	}
}
