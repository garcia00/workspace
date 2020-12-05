package br.com.j.hemopa.digital.model;

public enum TipoSangue {
	
SANGUE_AP("Sangue A+"),
SANGUE_AN("Sangue A-"),
SANGUE_BP("Sangue B+"),	
SANGUE_BN("Sangue B-"),	
SANGUE_ABP("Sangue AB+"),	
SANGUE_ABN("Sangue AB-"),	
Sangue_OP("Sangue O+"),	
SANGUE_ON("Sangue O-");
	
	private String descricao;
	
	TipoSangue (String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}
