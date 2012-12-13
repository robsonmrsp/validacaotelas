package br.com.m2msolutions.server;

import java.util.ArrayList;

import br.com.m2msolutions.client.InquiryAttendanceService;
import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoSearchParameters;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class InquiryAttendanceServiceImpl extends RemoteServiceServlet implements InquiryAttendanceService {

	private static final long serialVersionUID = -2864423702101130131L;

	@Override
	public DtoExtraInfoEvent findExtraInfoEvent(DtoCriticalEvent event) throws Exception {
		return UtilData.EXTRA_INFO.get(event.getId());
	}

	@Override
	public ArrayList<DtoCriticalEvent> findEventsByParameter(DtoSearchParameters createSearchParamenter) throws Exception {

		return UtilData.ALL_EVENTS;
	}

}
