package br.com.mr.storageposition.client;

public class Monitor {
	MonitoringScreen monitoringScreen = new MonitoringScreen();

	public Monitor() {
		monitoringScreen = createMonitoringScreen();
	}

	private MonitoringScreen createMonitoringScreen() {
		if (monitoringScreen == null) {
			monitoringScreen = new MonitoringScreen();
		}
		return monitoringScreen;
	}

	public void tracking(UserInfo user) {
		monitoringScreen.startMonitoringScreen(user);
	}
}
