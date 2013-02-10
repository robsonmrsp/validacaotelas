package br.com.mr.storageposition.server.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;

/**
 * The persistent class for the estado database table.
 * 
 */
@Entity
@Table(name = "estado")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "est_codigo")
	private String estCodigo;

	@Column(name = "est_faixa_cep1_fim")
	private String estFaixaCep1Fim;

	@Column(name = "est_faixa_cep1_ini")
	private String estFaixaCep1Ini;

	@Column(name = "est_faixa_cep2_fim")
	private String estFaixaCep2Fim;

	@Column(name = "est_faixa_cep2_ini")
	private String estFaixaCep2Ini;

	@Column(name = "est_nome")
	private String estNome;

	// bi-directional many-to-one association to Bairro
	@OneToMany(mappedBy = "estado")
	private Set<Bairro> bairros;

	// bi-directional many-to-one association to Cidade
	@OneToMany(mappedBy = "estado")
	private Set<Cidade> cidades;

	// bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy = "estado")
	private Set<Endereco> enderecos;

	public Estado() {
	}

	public String getEstCodigo() {
		return this.estCodigo;
	}

	public void setEstCodigo(String estCodigo) {
		this.estCodigo = estCodigo;
	}

	public String getEstFaixaCep1Fim() {
		return this.estFaixaCep1Fim;
	}

	public void setEstFaixaCep1Fim(String estFaixaCep1Fim) {
		this.estFaixaCep1Fim = estFaixaCep1Fim;
	}

	public String getEstFaixaCep1Ini() {
		return this.estFaixaCep1Ini;
	}

	public void setEstFaixaCep1Ini(String estFaixaCep1Ini) {
		this.estFaixaCep1Ini = estFaixaCep1Ini;
	}

	public String getEstFaixaCep2Fim() {
		return this.estFaixaCep2Fim;
	}

	public void setEstFaixaCep2Fim(String estFaixaCep2Fim) {
		this.estFaixaCep2Fim = estFaixaCep2Fim;
	}

	public String getEstFaixaCep2Ini() {
		return this.estFaixaCep2Ini;
	}

	public void setEstFaixaCep2Ini(String estFaixaCep2Ini) {
		this.estFaixaCep2Ini = estFaixaCep2Ini;
	}

	public String getEstNome() {
		return this.estNome;
	}

	public void setEstNome(String estNome) {
		this.estNome = estNome;
	}

	public Set<Bairro> getBairros() {
		return this.bairros;
	}

	public void setBairros(Set<Bairro> bairros) {
		this.bairros = bairros;
	}

	public Set<Cidade> getCidades() {
		return this.cidades;
	}

	public void setCidades(Set<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Set<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

}