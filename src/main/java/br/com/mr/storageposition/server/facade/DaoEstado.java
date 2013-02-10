package br.com.mr.storageposition.server.facade;

import java.util.List;

import javax.inject.Named;

import org.hibernate.criterion.Order;

import br.com.mr.storageposition.server.entities.Estado;
import br.com.mr.storageposition.server.persistence.Dao;

@Named
public class DaoEstado extends Dao<Estado> {

	public DaoEstado() {
		clazz = Estado.class;
	}
	@Override
	public List<Estado> loadAll() {
	
		return criteria().addOrder(Order.asc("estCodigo")).list();
	}
}
