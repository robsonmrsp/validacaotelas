package br.com.mr.storageposition.server.facade;

import javax.inject.Named;

import br.com.mr.storageposition.server.entities.Position;
import br.com.mr.storageposition.server.persistence.Dao;

@Named
public class DaoPosition extends Dao<Position> {

	public DaoPosition() {
		clazz = Position.class;
	}
}
