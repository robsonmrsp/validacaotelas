package br.com.m2msolutions.server;

import java.util.ArrayList;

import br.com.m2msolutions.client.AttendanceService;
import br.com.m2msolutions.shared.dto.DtoEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoSearchParameters;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AttendanceServiceImpl extends RemoteServiceServlet implements AttendanceService {

	private static final long serialVersionUID = -2864423702101130131L;

	@Override
	public DtoExtraInfoEvent findExtraInfoEvent(DtoEvent event) throws Exception {
		return UtilData.EXTRA_INFO.get(event.getId());
	}

	@Override
	public ArrayList<DtoEvent> findEventsByParameter(DtoSearchParameters createSearchParamenter) throws Exception {

		return UtilData.ALL_EVENTS;
	}

}
