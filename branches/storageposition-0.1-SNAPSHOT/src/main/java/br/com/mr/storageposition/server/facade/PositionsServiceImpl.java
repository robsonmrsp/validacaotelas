package br.com.mr.storageposition.server.facade;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import br.com.mr.storageposition.server.JsonPosition;
import br.com.mr.storageposition.server.entities.Position;
import br.com.mr.storageposition.server.facade.DaoPosition;
import br.com.mr.storageposition.server.facade.PositionsService;

@Named
@Transactional
public class PositionsServiceImpl implements PositionsService {

	private static final Log LOGER = LogFactory.getLog(PositionsServiceImpl.class);
	
	@Inject
	DaoPosition daoBusStop;

	public Boolean save(JsonPosition position) {
		try {
			
			daoBusStop.save(new Position(position.getDeviceId(), position.getCreationDate(), position.getLatitude(), position.getLongitude(), position.getAccuracy(),
										 position.getSpeed(), position.getAltitude() ));
			
		} catch (Exception e) {
			LOGER.error("N�o foi possivel salvar a posicao-> " + position, e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}