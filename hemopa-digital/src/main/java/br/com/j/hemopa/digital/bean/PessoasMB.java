package br.com.j.hemopa.digital.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.j.hemopa.digital.dominios.Horario;
import br.com.j.hemopa.digital.dominios.Sexo;
import br.com.j.hemopa.digital.dominios.TipoEvento;
import br.com.j.hemopa.digital.dominios.TipoSangue;
import br.com.j.hemopa.digital.dominios.UnidadesHemopa;
import br.com.j.hemopa.digital.model.Agenda;
import br.com.j.hemopa.digital.model.Contato;
import br.com.j.hemopa.digital.model.Endereco;
import br.com.j.hemopa.digital.model.Pessoa;
import br.com.j.hemopa.digital.repository.Pessoas;
import br.com.j.hemopa.digital.repository.filter.PessoaFilter;
import br.com.j.hemopa.digital.service.DAO;
import br.com.j.hemopa.digital.util.FacesMessages;

@Named
@SessionScoped
public class PessoasMB implements Serializable {

	private static final long serialVersionUID = -3000055469580812336L;

	public static final String NAVEGACAO = "/doador-form?faces-redirect=true";
	
	public static final String AGENDAMENTO = "/agendamento?faces-redirect=true";


	private Pessoa pessoa;
	
	private Agenda agenda;
	
	private Contato contato;
	
	private Endereco endereco;
	
	private Horario horario;
	
	private PessoaFilter filtro;
	
	@Inject
	private Pessoas pessoas;
	
	private List<Pessoa> pessoasFiltrados;

	@PostConstruct
	public void init() {

	}

	public String getNavegacao() {
		
		limpar();
		
		return PessoasMB.NAVEGACAO;

	}

	public String prepararNovoPessoa() {
		limpar();
		return getNavegacao();
	}

	public List<Pessoa> getPessoas() {
		
		return new DAO<Pessoa>(Pessoa.class).listaTodos();
	}

	public String salvar() {

		new DAO<Pessoa>(Pessoa.class).adiciona(this.pessoa);

		limpar();
		
		FacesMessages.addInfoMessage("Doador Cadastrado.");
		
		

		return AGENDAMENTO;

	}
	
	
	
	public String atualizar() {

		new DAO<Pessoa>(Pessoa.class).atualiza(this.pessoa);
		
		FacesMessages.addInfoMessage("Doador Agendado!");
		
		

		return NAVEGACAO;

	}
	

	public String carregar (){

		this.salvar();
	
		return AGENDAMENTO;
	}
	
	public void inicializar() {

		this.pessoa = pessoa;
	
	}
	
	private void limpar() {
		
		pessoa = new Pessoa();
		
	}

	public void remover(Pessoa pessoa) {

		new DAO<Pessoa>(Pessoa.class).remove(pessoa);
		
		FacesMessages.addInfoMessage("Doador".concat(pessoa.getNome()).concat(" removido!"));
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public TipoSangue[] getTipoSangues() {
		return TipoSangue.values();
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
	
	public boolean isEditando() {
		
		return this.pessoa.getId() != null;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
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

	public PessoaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(PessoaFilter filtro) {
		this.filtro = filtro;
	}

}
