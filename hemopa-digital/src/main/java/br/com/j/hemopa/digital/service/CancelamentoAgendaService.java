package br.com.j.hemopa.digital.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.j.hemopa.digital.dominios.TipoEvento;
import br.com.j.hemopa.digital.model.Agenda;
import br.com.j.hemopa.digital.repository.Agendamentos;

public class CancelamentoAgendaService implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Agendamentos agendas;
	
	@Transactional
	public Agenda cancelar(Agenda agenda) throws NegocioException {
		agenda = this.agendas.porId(agenda.getId());
		
		if (agenda.isNaoCancelavel()) {
			throw new NegocioException("O Agendamento não pode ser cancelado no status "
					+ agenda.getTipoEvento().getDescricao() + ".");
		}
		
		
		agenda.setTipoEvento(TipoEvento.CANCELADO);
		
		agenda = this.agendas.guarda(agenda);
		
		return agenda;
	}

}
