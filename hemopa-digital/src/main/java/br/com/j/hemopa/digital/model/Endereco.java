package br.com.j.hemopa.digital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="endereco")
public class Endereco {
	
	@Id
	@GeneratedValue
	@Column(name = "ID_ENDERECO")
	private Long id;
	
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
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
