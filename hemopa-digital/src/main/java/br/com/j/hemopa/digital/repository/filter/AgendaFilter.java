package br.com.j.hemopa.digital.repository.filter;

import java.io.Serializable;
import java.util.Date;

import br.com.j.hemopa.digital.model.Agenda;


public class AgendaFilter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tipoEvento;
	
	private Date dataInicio;
	
	private Date dataFim;

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		
		this.tipoEvento = tipoEvento.toString();
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	
}
