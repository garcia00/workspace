package br.com.j.hemopa.digital.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.j.hemopa.digital.model.Agenda;
import br.com.j.hemopa.digital.model.Contato;
import br.com.j.hemopa.digital.model.Endereco;
import br.com.j.hemopa.digital.model.Horario;
import br.com.j.hemopa.digital.model.Pessoa;
import br.com.j.hemopa.digital.model.Sangue;
import br.com.j.hemopa.digital.model.Sexo;
import br.com.j.hemopa.digital.model.Horario;

import br.com.j.hemopa.digital.model.TipoEvento;
import br.com.j.hemopa.digital.model.TipoSangue;
import br.com.j.hemopa.digital.model.UnidadesHemopa;
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

	private List<Sangue> sangues;

	@PostConstruct
	public void init() {
		pessoa = new Pessoa();
		setAgenda(new Agenda());
		setContato(new Contato());
		setEndereco(new Endereco());

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
		this.pessoa.getAgenda();
		this.pessoa.getContato();
		this.pessoa.getEndereco();
		
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
		
		

		return AGENDAMENTO;

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

	public List<Sangue> getSangues() {
		return sangues;
	}

	public void setSangues(List<Sangue> sangues) {
		this.sangues = sangues;
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

}
