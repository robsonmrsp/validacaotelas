package br.com.mr.storageposition.server.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "end_codigo")
	private Long endCodigo;

	@Column(name = "end_cep")
	private String endCep;

	@Column(name = "end_nome")
	private String endNome;

	// bi-directional many-to-one association to Bairro
	@ManyToOne
	@JoinColumn(name = "end_bairro")
	private Bairro bairro;

	// bi-directional many-to-one association to Cidade
	@ManyToOne
	@JoinColumn(name = "end_cidade")
	private Cidade cidade;

	// bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name = "end_estado")
	private Estado estado;

	public Endereco() {
	}

	public Long getEndCodigo() {
		return this.endCodigo;
	}

	public void setEndCodigo(Long endCodigo) {
		this.endCodigo = endCodigo;
	}

	public String getEndCep() {
		return this.endCep;
	}

	public void setEndCep(String endCep) {
		this.endCep = endCep;
	}

	public String getEndNome() {
		return this.endNome;
	}

	public void setEndNome(String endNome) {
		this.endNome = endNome;
	}

	public Bairro getBairro() {
		return this.bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}