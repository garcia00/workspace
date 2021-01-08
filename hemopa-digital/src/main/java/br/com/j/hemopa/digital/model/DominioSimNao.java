package br.com.j.hemopa.digital.model;

public enum DominioSimNao {
	
	SIM("Sim"),
	NAO("Não");
	
	private String descricao;
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	DominioSimNao (String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}
