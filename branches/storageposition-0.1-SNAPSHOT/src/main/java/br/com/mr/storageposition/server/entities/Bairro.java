package br.com.mr.storageposition.server.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the bairro database table.
 * 
 */
@Entity
@Table(name="bairro")
public class Bairro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="bai_codigo")
	private Long baiCodigo;

	@Column(name="bai_nome")
	private String baiNome;

	//bi-directional many-to-one association to Cidade
    @ManyToOne
	@JoinColumn(name="bai_cidade")
	private Cidade cidade;

	//bi-directional many-to-one association to Estado
    @ManyToOne
	@JoinColumn(name="bai_estado")
	private Estado estado;

	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="bairro")
	private Set<Endereco> enderecos;

    public Bairro() {
    }

	public Long getBaiCodigo() {
		return this.baiCodigo;
	}

	public void setBaiCodigo(Long baiCodigo) {
		this.baiCodigo = baiCodigo;
	}

	public String getBaiNome() {
		return this.baiNome;
	}

	public void setBaiNome(String baiNome) {
		this.baiNome = baiNome;
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
	
	public Set<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
}