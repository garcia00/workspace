package br.com.j.hemopa.digital.bean;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.j.hemopa.digital.dominios.TipoEvento;
import br.com.j.hemopa.digital.model.Agenda;
import br.com.j.hemopa.digital.model.Usuario;
import br.com.j.hemopa.digital.repository.Agendamentos;
import br.com.j.hemopa.digital.repository.filter.AgendaFilter;

@Named
@ViewScoped
public class PesquisarAgendasMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String AGENDAMENTO = "/agendamento?faces-redirect=true";
	
	@Inject
	private Agenda agenda;

	@Inject
	private Agendamentos agendas;

	private AgendaFilter filtro;
	
	private List<Agenda> filtroAgenda;

	public PesquisarAgendasMB() {
 
		filtro = new AgendaFilter();
		setFiltroAgenda(new ArrayList<>());
	}

	public void pesquisar() {
		
		filtroAgenda = agendas.filtrados(filtro);
	}
	
	public String inicializar() {
		
		setAgenda(new Agenda());
		
		return AGENDAMENTO;
	}

	public TipoEvento[] getTipoEvento() {

		return TipoEvento.values();
	}

	public List<Agenda> getFiltroAgenda() {
		return filtroAgenda;
	}

	public void setFiltroAgenda(List<Agenda> filtroAgenda) {
		this.filtroAgenda = filtroAgenda;
	}

	public Agendamentos getAgendas() {
		return agendas;
	}

	public void setAgendas(Agendamentos agendas) {
		this.agendas = agendas;
	}

	public AgendaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(AgendaFilter filtro) {
		this.filtro = filtro;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}


}
