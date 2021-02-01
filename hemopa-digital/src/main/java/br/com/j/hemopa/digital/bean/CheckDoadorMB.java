package br.com.j.hemopa.digital.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.j.hemopa.digital.model.Agenda;
import br.com.j.hemopa.digital.model.Pessoa;
import br.com.j.hemopa.digital.repository.Pessoas;
import br.com.j.hemopa.digital.repository.filter.PessoaFilter;
import br.com.j.hemopa.digital.util.FacesMessages;

@Named
@SessionScoped
public class CheckDoadorMB implements Serializable {

	private static final long serialVersionUID = -3000055469580812336L;

	public static final String NAVEGACAO = "/checkDoador?faces-redirect=true";

	@Inject
	private Pessoas pessoas;
	
	private Pessoa pessoa;
	
	private Agenda agenda;
	
	private PessoaFilter filtro;
	
	private List<Pessoa> pessoasFiltrados;
	
	private boolean chekin = false;
	
	public CheckDoadorMB() {

		filtro = new PessoaFilter();
		
	}
	
	public String prepararNovoChekin() {
		limpar();
		return NAVEGACAO;
	}
	
	public void inicializar() {
		
		pessoa = new Pessoa();
		this.limpar();
		
		}
	
	public void pesquisar() {
				
		if (this.filtro.getCpf() == null) {
			
			FacesMessages.addInfoMessage("CPF em Branco!");
			
		}
		
		pessoasFiltrados = pessoas.pesquisar(this.filtro.getCpf());
		
		
	}
	
	private void limpar() {
		
		pessoa = new Pessoa();
		filtro = new PessoaFilter();
				
	}
	
	public List<Pessoa> getPessoaFiltrados() {
		return pessoasFiltrados;
	}

	public PessoaFilter getFiltro() {
		return filtro;
	}

	public Pessoas getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}

	public List<Pessoa> getPessoasFiltrados() {
		return pessoasFiltrados;
	}

	public void setPessoasFiltrados(List<Pessoa> pessoasFiltrados) {
		this.pessoasFiltrados = pessoasFiltrados;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public boolean isChekin() {
		return chekin;
	}

	public void setChekin(boolean chekin) {
		this.chekin = chekin;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
}
