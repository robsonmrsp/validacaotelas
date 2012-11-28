package br.com.m2msolutions.client;

import java.util.ArrayList;

import br.com.m2msolutions.shared.dto.DtoEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoSearchParameters;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AttendanceServiceAsync {

	void findEventsByParameter(DtoSearchParameters createSearchParamenter, AsyncCallback<ArrayList<DtoEvent>> asyncCallback);

	void findExtraInfoEvent(DtoEvent dtoEvent, AsyncCallback<DtoExtraInfoEvent> callback);
}
