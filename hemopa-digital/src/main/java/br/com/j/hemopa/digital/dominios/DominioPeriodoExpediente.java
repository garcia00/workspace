package br.com.j.hemopa.digital.dominios;

public enum DominioPeriodoExpediente {
	
	SEGUNDA_SEXTA("SEGUNDA A SEXTA"),
	SABADO("SÁBADO"),
	DOMINGO("DOMINGO"),
	FERIADO("FERIADO");

	private String descricao;

	private DominioPeriodoExpediente(String descricao) {

		this.descricao = descricao;

	}

	
	public Integer getCodigo() {

		return this.ordinal();
	}

	
	public String getDescricao() {

		return this.descricao;
	}


	public String toString() {

		return this.descricao;
	}


}
