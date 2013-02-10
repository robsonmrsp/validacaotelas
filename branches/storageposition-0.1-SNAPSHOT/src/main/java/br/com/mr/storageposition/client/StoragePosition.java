package br.com.mr.storageposition.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;

public class StoragePosition implements EntryPoint {
	@Override
	public void onModuleLoad() {
		Window.setMargin("0px");
		final WhoAreYouPanel prototipo = new WhoAreYouPanel();

		prototipo.setGlassEnabled(true);
		prototipo.setAnimationEnabled(true);
		prototipo.center();
		prototipo.show();

		prototipo.addClickOkHandler(new ClickOkHandler() {
			@Override
			public void onClickOk(ClickOkEvent okEvent) {
				UserInfo info = okEvent.getSelectedUser();
				if (info != null) {
					prototipo.hide();
					startMonitoring(info);
				}
			}
		});
	}

	protected void startMonitoring(UserInfo user) {
		Monitor m = new Monitor();
		m.tracking(user);
	}
}
