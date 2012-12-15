package br.com.m2msolutions.client;

import java.util.ArrayList;

import br.com.m2msolutions.client.container.DtoCategory;
import br.com.m2msolutions.client.container.DtoCriticalEventsConfiguration;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CriticalEventsConfigurationServiceAsync {

	
	void getCriticalEventsByCategory(DtoCategory category, AsyncCallback<ArrayList<DtoEventType>> asyncCallback);

	void saveConfiguration(DtoCriticalEventsConfiguration createConfiguration, AsyncCallback<Boolean> asyncCallback);

}
