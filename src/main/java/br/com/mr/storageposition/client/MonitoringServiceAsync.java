package br.com.mr.storageposition.client;

import br.com.mr.storageposition.shared.UserInfoPosition;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MonitoringServiceAsync {

	void startServices(UserInfo user, AsyncCallback<UserInfoPosition> callback);

}
