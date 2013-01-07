package br.com.mr.storageposition.server.facade;

import br.com.mr.storageposition.server.JsonPosition;

public interface PositionsService {

	Boolean save(JsonPosition position);
}
