package br.com.j.hemopa.digital.dominios;

public enum Sexo {
	
	FEMININO("Feminino"),
	MASCULINO("Masculino");
	
	private String descricao;
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	Sexo (String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
