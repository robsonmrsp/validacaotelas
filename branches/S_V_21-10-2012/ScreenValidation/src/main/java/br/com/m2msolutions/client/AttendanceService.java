package br.com.m2msolutions.client;

import java.util.ArrayList;

import br.com.m2msolutions.shared.dto.DtoEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoSearchParameters;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("attendanceService")
public interface AttendanceService extends RemoteService {

	DtoExtraInfoEvent findExtraInfoEvent(DtoEvent dtoEvent) throws Exception;

	ArrayList<DtoEvent> findEventsByParameter(DtoSearchParameters createSearchParamenter) throws Exception;

}
