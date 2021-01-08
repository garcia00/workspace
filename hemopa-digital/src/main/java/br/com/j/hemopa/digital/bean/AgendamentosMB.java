package br.com.j.hemopa.digital.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.j.hemopa.digital.dominios.Horario;
import br.com.j.hemopa.digital.dominios.Sexo;
import br.com.j.hemopa.digital.dominios.TipoEvento;
import br.com.j.hemopa.digital.dominios.TipoSangue;
import br.com.j.hemopa.digital.dominios.UnidadesHemopa;
import br.com.j.hemopa.digital.model.Agenda;
import br.com.j.hemopa.digital.model.Pessoa;
import br.com.j.hemopa.digital.repository.Agendamentos;
import br.com.j.hemopa.digital.repository.Pessoas;
import br.com.j.hemopa.digital.repository.filter.PessoaFilter;
import br.com.j.hemopa.digital.service.AgendamentoDAO;
import br.com.j.hemopa.digital.service.DAO;
import br.com.j.hemopa.digital.service.PessoaDAO;
import br.com.j.hemopa.digital.util.FacesMessages;

@Named
@SessionScoped
public class AgendamentosMB implements Serializable {

	private static final long serialVersionUID = -3000055469580812336L;

	public static final String NAVEGACAO = "/agendamento?faces-redirect=true";
	
	public static final String AGENDAMENTO = "/agenda?faces-redirect=true";


	private Pessoa pessoa;

	private Agenda agenda;
	
	private PessoaFilter filtro;
	
	@Inject
	private Pessoas pessoas;
	
	private List<Pessoa> pessoasFiltrados;


	@Inject
	private Agendamentos agendamentos;

	@PostConstruct
	public void init() {
		pessoa = new Pessoa();
		agenda = new Agenda();
	}

	public String getNavegacao() {

		limpar();

		return AgendamentosMB.NAVEGACAO;

	}

	public String prepararNovoAgendamento() {
		limpar();
		return getNavegacao();
	}

	public List<Agenda> getAgendas() {
		return new AgendamentoDAO().listaTodos();
	}

	public List<Pessoa> getPessoas() {
		return new PessoaDAO().listaTodos();
	}

	public String salvar() {
		
		new DAO<Pessoa>(Pessoa.class).atualiza(this.pessoa);
		

			FacesContext.getCurrentInstance().addMessage("O agendamento",
					new FacesMessage("O agendamento precisa ter um doador"));
			
			limpar();
			
			return AGENDAMENTO;

	}

	public void carregarAgendaPelaId() {
		
		new AgendamentoDAO().buscaPorId(this.agenda.getId());
	}

	public String carregar(Agenda agenda) {

		this.agenda = agenda;

		return NAVEGACAO;
	}

	public void inicializar() {

		agenda = new Agenda();

	}

	private void limpar() {

		agenda = new Agenda();

	}

	public void reagendar(Agenda agenda) {

		new AgendamentoDAO().atualiza(agenda);;

		FacesMessages.addInfoMessage("Agendamento".concat(pessoa.getNome()).concat(" Reagendada!"));
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public Horario[] getHorarios() {
		return Horario.values();
	}

	public UnidadesHemopa[] getUnidadeHemopa() {
		return UnidadesHemopa.values();
	}

	public TipoEvento[] getTipoEvento() {
		return TipoEvento.values();
	}
	
	public TipoSangue[] getTipoSangues() {
		return TipoSangue.values();
	}
	
	public boolean isEditando() {

		return this.pessoa.getId() != null;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public void isChekin() {

		this.agenda.isChekin();
	}

	@Transactional
	public Agenda cancelar(Agenda agenda) {
		agenda = this.agendamentos.porId(this.agenda.getId());

		if (this.agenda.isMarcado()) {

			agenda.setTipoEvento(TipoEvento.CANCELADO);

			agenda = this.agendamentos.guarda(agenda);
		}
		return agenda;
	}
	
	public void pesquisar() {
		
		if (this.filtro.getCpf() == null) {
			
			FacesMessages.addInfoMessage("CPF em Branco!");
			
		}
		
		setPessoasFiltrados(pessoas.pesquisar(this.filtro.getCpf()));
		
	}

	public List<Pessoa> getPessoasFiltrados() {
		return pessoasFiltrados;
	}

	public void setPessoasFiltrados(List<Pessoa> pessoasFiltrados) {
		this.pessoasFiltrados = pessoasFiltrados;
	}

}
