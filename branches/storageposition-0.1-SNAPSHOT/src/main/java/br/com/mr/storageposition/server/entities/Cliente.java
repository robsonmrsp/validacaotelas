package br.com.mr.storageposition.server.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cli_codigo")
	private Long cliCodigo;

	@Column(name="cli_bairro")
	private String cliBairro;

	@Column(name="cli_cep")
	private String cliCep;

	@Column(name="cli_cidade")
	private String cliCidade;

	@Column(name="cli_cpf")
	private String cliCpf;

	@Column(name="cli_dtalteracao")
	private Timestamp cliDtalteracao;

	@Column(name="cli_dtcadastro")
	private Timestamp cliDtcadastro;

    @Temporal( TemporalType.DATE)
	@Column(name="cli_dtnascimento")
	private Date cliDtnascimento;

	@Column(name="cli_endereco")
	private Long cliEndereco;

	@Column(name="cli_estado")
	private String cliEstado;

	@Column(name="cli_fone")
	private String cliFone;

	@Column(name="cli_fone_comercial")
	private String cliFoneComercial;

	@Column(name="cli_nome")
	private String cliNome;

	@Column(name="cli_numero")
	private Long cliNumero;

	@Column(name="cli_pais")
	private String cliPais;

	@Column(name="cli_sexo")
	private String cliSexo;

    public Cliente() {
    }

	public Long getCliCodigo() {
		return this.cliCodigo;
	}

	public void setCliCodigo(Long cliCodigo) {
		this.cliCodigo = cliCodigo;
	}

	public String getCliBairro() {
		return this.cliBairro;
	}

	public void setCliBairro(String cliBairro) {
		this.cliBairro = cliBairro;
	}

	public String getCliCep() {
		return this.cliCep;
	}

	public void setCliCep(String cliCep) {
		this.cliCep = cliCep;
	}

	public String getCliCidade() {
		return this.cliCidade;
	}

	public void setCliCidade(String cliCidade) {
		this.cliCidade = cliCidade;
	}

	public String getCliCpf() {
		return this.cliCpf;
	}

	public void setCliCpf(String cliCpf) {
		this.cliCpf = cliCpf;
	}

	public Timestamp getCliDtalteracao() {
		return this.cliDtalteracao;
	}

	public void setCliDtalteracao(Timestamp cliDtalteracao) {
		this.cliDtalteracao = cliDtalteracao;
	}

	public Timestamp getCliDtcadastro() {
		return this.cliDtcadastro;
	}

	public void setCliDtcadastro(Timestamp cliDtcadastro) {
		this.cliDtcadastro = cliDtcadastro;
	}

	public Date getCliDtnascimento() {
		return this.cliDtnascimento;
	}

	public void setCliDtnascimento(Date cliDtnascimento) {
		this.cliDtnascimento = cliDtnascimento;
	}

	public Long getCliEndereco() {
		return this.cliEndereco;
	}

	public void setCliEndereco(Long cliEndereco) {
		this.cliEndereco = cliEndereco;
	}

	public String getCliEstado() {
		return this.cliEstado;
	}

	public void setCliEstado(String cliEstado) {
		this.cliEstado = cliEstado;
	}

	public String getCliFone() {
		return this.cliFone;
	}

	public void setCliFone(String cliFone) {
		this.cliFone = cliFone;
	}

	public String getCliFoneComercial() {
		return this.cliFoneComercial;
	}

	public void setCliFoneComercial(String cliFoneComercial) {
		this.cliFoneComercial = cliFoneComercial;
	}

	public String getCliNome() {
		return this.cliNome;
	}

	public void setCliNome(String cliNome) {
		this.cliNome = cliNome;
	}

	public Long getCliNumero() {
		return this.cliNumero;
	}

	public void setCliNumero(Long cliNumero) {
		this.cliNumero = cliNumero;
	}

	public String getCliPais() {
		return this.cliPais;
	}

	public void setCliPais(String cliPais) {
		this.cliPais = cliPais;
	}

	public String getCliSexo() {
		return this.cliSexo;
	}

	public void setCliSexo(String cliSexo) {
		this.cliSexo = cliSexo;
	}

}