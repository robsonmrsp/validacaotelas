package br.com.mr.storageposition.server.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class JsonCliente implements Serializable {
	private static final long serialVersionUID = -5047592375653712024L;
	private String cpf;
	private String nome;
	private String sexo;
	private Date dataNascimento;
	private List<String> telefone;
	private JsonEndereco endereco;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<String> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<String> telefone) {
		this.telefone = telefone;
	}

	public JsonEndereco getEndereco() {
		return endereco;
	}

	public void setEndereco(JsonEndereco endereco) {
		this.endereco = endereco;
	}

}
