package br.com.mr.storageposition.facade;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import br.com.mr.storageposition.JsonPosition;
import br.com.mr.storageposition.entities.Position;

@Named
@Transactional
public class PositionsServiceImpl implements PositionsService {

	private static final Log LOGER = LogFactory.getLog(PositionsServiceImpl.class);
	@Inject
	DaoPosition daoBusStop;

	public Boolean save(JsonPosition position) {
		try {
			daoBusStop.save(new Position(position.getDevideId(),position.getCreationDate(), position.getLatitude(), position.getLongitude()));
		} catch (Exception e) {
			LOGER.error("Não foi possivel salar a posicao-> " + position, e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}