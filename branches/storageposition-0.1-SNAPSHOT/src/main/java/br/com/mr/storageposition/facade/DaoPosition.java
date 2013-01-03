package br.com.mr.storageposition.facade;

import javax.inject.Named;

import br.com.mr.storageposition.entities.Position;
import br.com.mr.storageposition.persistence.Dao;

@Named
public class DaoPosition extends Dao<Position> {

	public DaoPosition() {
		clazz = Position.class;
	}
}
