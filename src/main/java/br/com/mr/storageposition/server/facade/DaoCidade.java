package br.com.mr.storageposition.server.facade;

import javax.inject.Named;

import br.com.mr.storageposition.server.entities.Cidade;
import br.com.mr.storageposition.server.persistence.Dao;

@Named
public class DaoCidade extends Dao<Cidade> {

	public DaoCidade() {
		clazz = Cidade.class;
	}
}
