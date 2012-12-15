package br.com.m2msolutions.client;

import java.util.ArrayList;

import br.com.m2msolutions.shared.dto.DtoCriticalEvent;
import br.com.m2msolutions.shared.dto.DtoExtraInfoEvent;
import br.com.m2msolutions.shared.dto.DtoSearchParameters;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("inquiryAttendanceService")
public interface InquiryAttendanceService extends RemoteService {

	/**
	 *Deverá retornar as informações adicionais para o preenchimento da tela  "Consulta de atendimentos". veja DtoExtraInfoEvent  
	 **/
	DtoExtraInfoEvent findExtraInfoEvent(DtoCriticalEvent dtoEvent) throws Exception;

	/**
	 * Consulta os Eventos Criticos segundo as informações em DtoSearchParameters
	 * @param createSearchParamenter
	 * @return
	 * @throws Exception
	 */
	ArrayList<DtoCriticalEvent> findEventsByParameter(DtoSearchParameters createSearchParamenter) throws Exception;
	
	
	public static class Util {
		private static CriticalEventsConfigurationServiceAsync instance;

		public static CriticalEventsConfigurationServiceAsync getInstance() {
			if (instance == null) {
				instance = GWT.create(CriticalEventsConfigurationService.class);
			}
			return instance;
		}
	}


}
