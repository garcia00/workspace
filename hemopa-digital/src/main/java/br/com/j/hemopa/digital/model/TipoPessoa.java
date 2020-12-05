package br.com.j.hemopa.digital.model;

public enum TipoPessoa {
	
	SERVIDOR("Servidor"),
	DOADOR("Doador");
	
	private String descricao;
	
	TipoPessoa (String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
