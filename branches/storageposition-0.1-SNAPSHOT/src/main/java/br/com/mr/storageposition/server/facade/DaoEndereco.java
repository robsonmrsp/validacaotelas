package br.com.mr.storageposition.server.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Named;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.mr.storageposition.server.entities.Cidade;
import br.com.mr.storageposition.server.entities.Endereco;
import br.com.mr.storageposition.server.entities.Estado;
import br.com.mr.storageposition.server.persistence.Dao;

@Named
public class DaoEndereco extends Dao<Endereco> {

	public DaoEndereco() {
		clazz = Endereco.class;
	}

	@SuppressWarnings("unchecked")
	public List<Endereco> getEnderecoByCidade(Cidade cidade) {
		List<Endereco> result = new ArrayList<Endereco>();
		try {
			result.addAll(criteria().add(Restrictions.eq("cidade", cidade)).list());
		} catch (Exception e) {
			System.out.println("Erro na busca dos enderecos da cidade:> " + cidade);
			e.printStackTrace();
		}

		return result;
	}

	public Endereco getEnderecoByCep(String cep) {
		Endereco uniqueResult = null;
		try {
			uniqueResult = (Endereco) criteria().add(Restrictions.eq("endCep", cep)).uniqueResult();
		} catch (Exception e) {
			System.out.println("Mais de um endereço para o cep:> " + cep);
		}
		return uniqueResult;
	}

	@SuppressWarnings("unchecked")
	public List<Endereco> getEnderecoByEstado(Estado estado) {
		List<Endereco> result = new ArrayList<Endereco>();
		try {
			result.addAll(criteria().add(Restrictions.eq("estado", estado)).createAlias("estado", "estado").addOrder(Order.asc("estado.estNome")).list());
		} catch (Exception e) {
			System.out.println("Erro na busca dos enderecos da cidade:> " + estado.getEstNome());
			e.printStackTrace();
		}
		return result;

	}
}
