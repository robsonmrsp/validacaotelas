package br.com.mr.storageposition.server.facade;

import java.util.List;

import br.com.mr.storageposition.server.JsonPosition;

public interface PositionsService {

	Boolean save(JsonPosition position);

	Boolean save(List<JsonPosition> positions);
}
