package br.com.mr.storageposition.server.facade;

import javax.inject.Named;

import br.com.mr.storageposition.server.entities.Cliente;
import br.com.mr.storageposition.server.persistence.Dao;

@Named
public class DaoCliente extends Dao<Cliente> {

	public DaoCliente() {
		clazz = Cliente.class;
	}
}
