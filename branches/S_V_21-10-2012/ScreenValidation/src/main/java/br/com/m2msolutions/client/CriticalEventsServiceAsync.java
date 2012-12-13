package br.com.m2msolutions.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CriticalEventsServiceAsync {

	void getCriticalEventsInfo(AsyncCallback<DtoCriticalEventsInfo> callback);

	void startAutomaticUpdating(AsyncCallback<Boolean> callback);

	void endAutomaticUpdating(AsyncCallback<Boolean> callback);
}
