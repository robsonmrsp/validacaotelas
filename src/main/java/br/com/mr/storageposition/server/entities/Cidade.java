package br.com.mr.storageposition.server.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the cidade database table.
 * 
 */
@Entity
@Table(name="cidade")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cid_codigo")
	private Long cidCodigo;

	@Column(name="cid_cep")
	private String cidCep;

	@Column(name="cid_nome")
	private String cidNome;

	//bi-directional many-to-one association to Bairro
	@OneToMany(mappedBy="cidade")
	private Set<Bairro> bairros;

	//bi-directional many-to-one association to Estado
    @ManyToOne
	@JoinColumn(name="cid_estado")
	private Estado estado;

	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="cidade")
	private Set<Endereco> enderecos;

    public Cidade() {
    }

	public Long getCidCodigo() {
		return this.cidCodigo;
	}

	public void setCidCodigo(Long cidCodigo) {
		this.cidCodigo = cidCodigo;
	}

	public String getCidCep() {
		return this.cidCep;
	}

	public void setCidCep(String cidCep) {
		this.cidCep = cidCep;
	}

	public String getCidNome() {
		return this.cidNome;
	}

	public void setCidNome(String cidNome) {
		this.cidNome = cidNome;
	}

	public Set<Bairro> getBairros() {
		return this.bairros;
	}

	public void setBairros(Set<Bairro> bairros) {
		this.bairros = bairros;
	}
	
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Set<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
}