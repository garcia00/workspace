package br.com.j.hemopa.digital.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.mapping.Array;

import br.com.j.hemopa.digital.dominios.Dia;
import br.com.j.hemopa.digital.dominios.DiasSemana;
import br.com.j.hemopa.digital.dominios.DominioMes;
import br.com.j.hemopa.digital.dominios.DominioPeriodoExpediente;
import br.com.j.hemopa.digital.dominios.Horario;
import br.com.j.hemopa.digital.dominios.Sexo;
import br.com.j.hemopa.digital.dominios.TipoEvento;
import br.com.j.hemopa.digital.dominios.TipoSangue;
import br.com.j.hemopa.digital.dominios.UnidadesHemopa;
import br.com.j.hemopa.digital.model.Agenda;
import br.com.j.hemopa.digital.model.Pessoa;
import br.com.j.hemopa.digital.repository.Agendamentos;
import br.com.j.hemopa.digital.repository.Pessoas;
import br.com.j.hemopa.digital.repository.filter.AgendaFilter;
import br.com.j.hemopa.digital.repository.filter.PessoaFilter;
import br.com.j.hemopa.digital.service.AgendamentoDAO;
import br.com.j.hemopa.digital.service.DAO;
import br.com.j.hemopa.digital.service.PessoaDAO;
import br.com.j.hemopa.digital.util.DataUtil;
import br.com.j.hemopa.digital.util.FacesMessages;

@Named
@SessionScoped
public class AgendamentosMB implements Serializable {

	private static final long serialVersionUID = -3000055469580812336L;

	public static final String NAVEGACAO = "/agendamento?faces-redirect=true";

	public static final String AGENDAMENTO = "/agenda?faces-redirect=true";

	private Pessoa pessoa;
	
	private AgendamentoDAO agendamentoDAO;

	private Agenda agenda;

	private PessoaFilter filtro;

	private AgendaFilter agendaFiltro;

	private List<Agenda> filtroAgenda;

	private List<Pessoa> pessoasFiltrados;

	private DataUtil dataUtil;

	@Inject
	private Agendamentos agendamentos;

	private boolean chekin = false;

	@PostConstruct
	public void init() {

	}

	public void agendamento() {
		agenda = new Agenda();
		pessoa = new Pessoa();
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
		return new AgendamentoDAO().buscarPorCriterio(agendaFiltro);
	}

	public List<Pessoa> getPessoas() {
		return new PessoaDAO().listaTodos();
	}

	public String salvar() {

		new AgendamentoDAO().adiciona(agenda);

		FacesContext.getCurrentInstance().addMessage("O agendamento", new FacesMessage("Realizado com sucesso!"));

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

		new AgendamentoDAO().atualiza(agenda);

		FacesMessages.addInfoMessage("Agendamento".concat(pessoa.getNome()).concat(" Reagendada!"));
	}

	public Pessoa getPessoa() {

		if (pessoa == null) {

			pessoa = new Pessoa();
		}

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

	public DiasSemana[] getDiasSemanas() {
		return DiasSemana.values();
	}

	public DominioMes[] getDominioMeses() {
		return DominioMes.values();
	}

	public Dia[] getDias() {
		return Dia.values();
	}

	public DominioPeriodoExpediente[] getDominioPeriodoExpedientes() {
		return DominioPeriodoExpediente.values();
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

	public void cancelar() {

		FacesMessages.addInfoMessage("Cancelado agendamento!");
		
		this.agendamentos.remover(agenda);


	}

	public void acaoPesquisar() {
		
		this.agendamentoDAO = new AgendamentoDAO();
		List<Agenda> agendas = this.agendamentoDAO.buscarPorCriterio(this.getAgendaFiltro());
		
		agendas.forEach(System.out::println);
				

	}

	public PessoaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(PessoaFilter filtro) {
		this.filtro = filtro;
	}

	public List<Pessoa> getPessoasFiltrados() {
		return pessoasFiltrados;
	}

	public void setPessoasFiltrados(List<Pessoa> pessoasFiltrados) {
		this.pessoasFiltrados = pessoasFiltrados;
	}

	public boolean isChekin() {
		
		return chekin;
	}

	public void setChekin(boolean chekin) {
		this.chekin = chekin;
	}

	public List<Agenda> getFiltroAgenda() {
			
		return filtroAgenda;
	}

	public void setFiltroAgenda(List<Agenda> filtroAgenda) {
		this.filtroAgenda = filtroAgenda;
	}

	public AgendaFilter getAgendaFiltro() {

		if (agendaFiltro == null) {
			agendaFiltro = new AgendaFilter();
		}
		return agendaFiltro;
	}

	public void setAgendaFiltro(AgendaFilter agendaFiltro) {
		this.agendaFiltro = agendaFiltro;
	}

	public DataUtil getDataUtil() {
		return dataUtil;
	}

	public void setDataUtil(DataUtil dataUtil) {
		this.dataUtil = dataUtil;
	}

	public Agendamentos getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(Agendamentos agendamentos) {
		this.agendamentos = agendamentos;
	}

	public AgendamentoDAO getAgendamentoDAO() {
		return agendamentoDAO;
	}

	public void setAgendamentoDAO(AgendamentoDAO agendamentoDAO) {
		this.agendamentoDAO = agendamentoDAO;
	}

}
