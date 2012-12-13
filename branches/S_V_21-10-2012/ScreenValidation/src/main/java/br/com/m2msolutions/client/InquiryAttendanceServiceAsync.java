package br.com.m2msolutions.client;

import java.util.ArrayList;

import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoSearchParameters;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface InquiryAttendanceServiceAsync {

	void findEventsByParameter(DtoSearchParameters createSearchParamenter, AsyncCallback<ArrayList<DtoCriticalEvent>> asyncCallback);

	void findExtraInfoEvent(DtoCriticalEvent dtoEvent, AsyncCallback<DtoExtraInfoEvent> callback);
}
