package br.com.mr.storageposition.server.facade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;

import br.com.mr.storageposition.server.EnderecosService;
import br.com.mr.storageposition.server.entities.Cidade;
import br.com.mr.storageposition.server.entities.Cliente;
import br.com.mr.storageposition.server.entities.Endereco;
import br.com.mr.storageposition.server.entities.Estado;

@Named
@Transactional
public class EnderecosServiceImpl implements EnderecosService {

	private static final Log LOGER = LogFactory.getLog(EnderecosServiceImpl.class);

	@Inject
	DaoPosition daoPosition;
	@Inject
	DaoCliente daoCliente;

	@Inject
	DaoEndereco daoEndereco;

	@Inject
	DaoCidade daoCidade;

	@Inject
	DaoEstado daoEstado;

	@Override
	public Boolean generateJsons() {
		List<Estado> estados = daoEstado.loadAll();

		try {
			for (Estado estado : estados) {
				List<JsonEndereco> jsonEnderecos = new ArrayList<JsonEndereco>();
				List<Endereco> clientes = daoEndereco.getEnderecoByEstado(estado);
				LOGER.info("Enderecos do estado: " + estado.getEstNome());
				for (Endereco cliente : clientes) {
					JsonEndereco endereco = getEndereco(cliente);
					if (endereco != null) {
						jsonEnderecos.add(endereco);
					}
				}
				clientes.clear();
				clientes = null;
				guardaEnderecos(jsonEnderecos, estado.getEstCodigo());
				jsonEnderecos.clear();
				jsonEnderecos = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.TRUE;

	}

	private void guardaEnderecos(List<JsonEndereco> jsonEnderecos, String sufix) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		File resultFile = new File("c:\\enderecos_" + sufix + ".json");
		mapper.writeValue(resultFile, jsonEnderecos);
	}

	private JsonCliente createJsonEndereco(Endereco cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	public Boolean generateClientesJsons() {

		List<Cliente> clientes = daoCliente.loadAll();
		try {
			File resultFile = new File("c:\\clientes.json");
			for (Cliente cliente : clientes) {
				JsonEndereco endereco = getEndereco(cliente);
				//mapper.writeValue(resultFile, endereco);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.TRUE;

	}

	private JsonCliente createJsonCliente(Cliente cliente) {
		JsonCliente jsonCliente = new JsonCliente();
		jsonCliente.setCpf(cliente.getCliCpf());
		jsonCliente.setNome(cliente.getCliNome());
		jsonCliente.setDataNascimento(cliente.getCliDtnascimento());
		jsonCliente.setSexo(cliente.getCliSexo());
		jsonCliente.setTelefone(getTelefones(cliente));
		jsonCliente.setEndereco(getEndereco(cliente));
		return jsonCliente;
	}

	private JsonEndereco getEndereco(Endereco ende) {
		JsonEndereco endereco = new JsonEndereco();

		if (ende != null) {
			endereco.setCep(ende.getEndCep());
			endereco.setLogradouro((ende.getEndNome()));
			endereco.setBairro(ende.getBairro().getBaiNome());
			endereco.setCidade(ende.getCidade().getCidNome());
			endereco.setEstado(ende.getEstado().getEstNome());
		}
		return endereco;
	}

	private JsonEndereco getEndereco(Cliente cliente) {
		JsonEndereco endereco = new JsonEndereco();
		Endereco ende = daoEndereco.getEnderecoByCep(cliente.getCliCep());
		if (ende != null) {
			endereco.setCep(ende.getEndCep());
			endereco.setBairro(ende.getBairro().getBaiNome());
			endereco.setCidade(ende.getCidade().getCidNome());
			endereco.setEstado(ende.getEstado().getEstNome());
			endereco.setLogradouro((ende.getEndNome()));
		}
		return endereco;
	}

	private List<String> getTelefones(Cliente cliente) {
		List<String> telefones = new ArrayList<String>();
		if (cliente.getCliFone() != null)
			telefones.add(cliente.getCliFone());
		if (cliente.getCliFoneComercial() != null)
			telefones.add(cliente.getCliFoneComercial());
		return telefones;
	}

}