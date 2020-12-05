package br.com.j.hemopa.digital.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contato")
public class Contato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoa", nullable = false)
	private Pessoa pessoa;

	@DecimalMin(value = "0")
	@DecimalMax(value = "99")
	@Column(name = "NU_DDD_TELEFONE", nullable = false, precision = 2)
	private BigDecimal dddTelefone;

	@NotNull
	@DecimalMin(value = "0")
	@DecimalMax(value = "999999999")
	@Column(name = "NU_TELEFONE", nullable = false, precision = 9)
	private BigDecimal numeroTelefone;

	@NotNull
	@DecimalMin(value = "0")
	@DecimalMax(value = "99")
	@Column(name = "NU_DDD_CELULAR", nullable = false, precision = 2)
	private BigDecimal dddCelular;

	@NotNull
	@DecimalMin(value = "0")
	@DecimalMax(value = "999999999")
	@Column(name = "NU_CELULAR", nullable = false, precision = 9)
	private BigDecimal numeroCelular;

	@NotNull
	@Column(name = "TX_EMAIL", nullable = false, length = 63)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public BigDecimal getDddTelefone() {
		return dddTelefone;
	}

	public void setDddTelefone(BigDecimal dddTelefone) {
		this.dddTelefone = dddTelefone;
	}

	public BigDecimal getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(BigDecimal numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public BigDecimal getDddCelular() {
		return dddCelular;
	}

	public void setDddCelular(BigDecimal dddCelular) {
		this.dddCelular = dddCelular;
	}

	public BigDecimal getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(BigDecimal numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Contato other = (Contato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
