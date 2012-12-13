package br.com.m2msolutions.client;

import java.util.ArrayList;

import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoSearchParameters;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("attendanceService")
public interface InquiryAttendanceService extends RemoteService {

	DtoExtraInfoEvent findExtraInfoEvent(DtoCriticalEvent dtoEvent) throws Exception;

	ArrayList<DtoCriticalEvent> findEventsByParameter(DtoSearchParameters createSearchParamenter) throws Exception;

}
