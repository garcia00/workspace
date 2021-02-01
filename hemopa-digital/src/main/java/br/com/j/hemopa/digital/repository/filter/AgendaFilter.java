package br.com.j.hemopa.digital.repository.filter;

import java.io.Serializable;

import br.com.j.hemopa.digital.model.Agenda;


public class AgendaFilter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tipoEvento;
	
	private String dataInicio;
	
	private String dataFim;

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		
		this.tipoEvento = tipoEvento.toString();
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	
	
}
