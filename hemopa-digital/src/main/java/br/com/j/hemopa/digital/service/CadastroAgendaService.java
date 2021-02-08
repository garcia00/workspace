package br.com.j.hemopa.digital.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.j.hemopa.digital.dominios.TipoEvento;
import br.com.j.hemopa.digital.model.Agenda;
import br.com.j.hemopa.digital.model.Pessoa;
import br.com.j.hemopa.digital.repository.Agendamentos;
import repository.Pessoas;


public class CadastroAgendaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Agendamentos agendas;
	
	@Transactional
	public void salvar(Agenda agenda) {
		
		if (agenda.isExiste()) {
			
			agenda.setTipoEvento(TipoEvento.MARCADO);
		}
		
		agendas.guarda(agenda);
	}
	
	@Transactional
	public void excluir(Agenda agenda) {
		agendas.remover(agenda);
	}

}
