package br.com.j.hemopa.digital.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.j.hemopa.digital.model.Agenda;
import br.com.j.hemopa.digital.model.AlterarTipoEvento;
import br.com.j.hemopa.digital.service.CancelamentoAgendaService;
import br.com.j.hemopa.digital.service.NegocioException;
import br.com.j.hemopa.digital.util.FacesUtil;

@Named
@SessionScoped
public class CancelarAgendamentoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CancelamentoAgendaService cancelamentoAgendaService;
	
	@Inject
	private Event<AlterarTipoEvento> alterarTipoEvento;
	
	private Agenda agenda;
	
	public void cancelar(Agenda agenda) throws NegocioException {
		this.agenda = this.cancelamentoAgendaService.cancelar(this.agenda);
		this.alterarTipoEvento.fire(new AlterarTipoEvento(this.agenda));
		
		FacesUtil.addInfoMessage("Agendamento cancelado com sucesso!");
	}

}
