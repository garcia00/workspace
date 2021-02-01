package br.com.j.hemopa.digital.dominios;

public enum DiasSemana {
	
	DOMINGO("Domingo"),
	SEGUNDA("Masculino"),
	TERCA("Terça"),
	QUARTA("Quarta"),
	QUINTA("Quinta"),
	SEXTA("Sexta"),
	SABADO("Sabado"),
	
	;
	private String descricao;
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	DiasSemana (String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}
