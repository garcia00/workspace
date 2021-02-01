package br.com.j.hemopa.digital.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class Endereco {
	
	@NotNull
	@Column(name = "logradouro", nullable = false, length = 150)
	private String logradouro;
	
	@NotNull
	@Column(name = "numero",nullable = false, length = 20)
	private String numero;
	
	@NotNull
	@Column(name = "complemento",length = 150)
	private String complemento;
	
	@NotNull
	@Column(name = "cidade",nullable = false, length = 60)
	private String cidade;
	
	@NotNull
	@Column(name = "rua", nullable = false, length = 30)
	private String rua;
	
	@NotNull
	@Column(name = "uf",nullable = false, length = 60)
	private String uf;
	
	@NotNull
	@Column(name = "cep",nullable = false, length = 9)
	private String cep;
	
	@NotNull
	@Column(name = "bairro",nullable = false, length = 30)
	private String bairro;
	
	@NotNull
	@Column(name = "estado", nullable = false, length = 50)
	private String estado;
		
	@OneToOne
	@JoinColumn(name = "ID_PESSOA")
	private Pessoa pessoa;
	
	public Endereco() {
		super();
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
