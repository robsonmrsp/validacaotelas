package br.com.mr.storageposition.server.facade;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import br.com.mr.storageposition.server.JsonPosition;
import br.com.mr.storageposition.server.entities.Position;

@Named
@Transactional
public class PositionsServiceImpl implements PositionsService {

	private static final Log LOGER = LogFactory.getLog(PositionsServiceImpl.class);

	@Inject
	DaoPosition daoPosition;

	public Boolean save(JsonPosition position) {
		try {
			daoPosition.save(new Position(position.getDeviceId(), position.getCreationDate(), new Date(position.getCreationDate()), position.getLatitude(), position.getLongitude(), position.getAccuracy(), position.getSpeed(), position.getAltitude()));
		} catch (Exception e) {
			LOGER.error("Nao foi possivel salvar a posicao-> " + position, e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	
}