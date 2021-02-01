package br.com.j.hemopa.digital.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contato {

	@Column(name = "NU_DDD_TELEFONE", nullable = false, precision = 2)
	private String dddTelefone;

	@Column(name = "NU_TELEFONE", nullable = false, precision = 9)
	private String numeroTelefone;

	@Column(name = "NU_DDD_CELULAR", nullable = false, precision = 2)
	private String dddCelular;

	@Column(name = "NU_CELULAR", nullable = false, precision = 9)
	private String numeroCelular;

	@Column(name = "TX_EMAIL", nullable = false, length = 63)
	private String email;
	
	
	public Contato() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDddTelefone() {
		return dddTelefone;
	}

	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getDddCelular() {
		return dddCelular;
	}

	public void setDddCelular(String dddCelular) {
		this.dddCelular = dddCelular;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

}
