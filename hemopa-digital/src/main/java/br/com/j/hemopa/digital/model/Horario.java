package br.com.j.hemopa.digital.model;

public enum Horario {
	
	Horario_1("08:00"),
	Horario_2("08:30")	,
	Horario_3("09:00")	,
	Horario_4("09:30")	,
	Horario_5("10:00")	,
	Horario_6("10:30")	,
	Horario_7("11:00")	,
	Horario_8("11:30")	,
	Horario_9("12:00")	,
	Horario_10("12:30")	,
	Horario_11("13:00")	,
	Horario_12("13:30")	,
	Horario_13("14:00")	,
	Horario_14("14:30")	,
	Horario_15("15:00")	,
	Horario_16("15:30")	,
	Horario_17("16:00")	,
	Horario_18("16:30")	,
	Horario_19("17:00")	,
	Horario_20("17:30")	,
	Horario_21("18:00")	,
	Horario_22("18:30")	,

	
	;
	private String descricao;
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	Horario (String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}
