package br.com.m2msolutions.client;

import java.util.List;

import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoOperator;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CriticalEventsAttendanceServiceAsync {

	void getOperators(AsyncCallback<List<DtoOperator>> asyncCallback);

	void transferAttendance(DtoCriticalEvent actualEvent, DtoOperator operator, AsyncCallback<Boolean> asyncCallback);

	void findExtraInfoEvent(DtoCriticalEvent selectedItem, AsyncCallback<DtoExtraInfoEvent> asyncCallback);

	void closeAttencance(DtoCriticalEvent actualEvent, AsyncCallback<Boolean> asyncCallback);

}
