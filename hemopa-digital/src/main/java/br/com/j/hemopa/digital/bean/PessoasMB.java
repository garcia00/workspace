package br.com.j.hemopa.digital.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.j.hemopa.digital.model.Pessoa;
import br.com.j.hemopa.digital.model.TipoPessoa;
import br.com.j.hemopa.digital.service.DAO;
import br.com.j.hemopa.digital.util.FacesMessages;
import repository.Pessoas;

@Named
@SessionScoped
public class PessoasMB implements Serializable {

	private static final long serialVersionUID = -1264260459385294500L;

	public static final String NAVEGACAO = "/pessoa-form?faces-redirect=true";

	@Inject
	private Pessoas pessoas;

	@Inject
	private FacesMessages messages;

	public String getNavegacao() {

		return PessoasMB.NAVEGACAO;

	}

	private Converter<?> setorAtividadeConverter;

	private List<Pessoa> listaPessoas;

	private String termoPesquisa;

	private Pessoa pessoa;

	public String prepararNovaPessoa() {
		pessoa = new Pessoa();
		return getNavegacao();
	}

	public String salvar() {
		
		new DAO<Pessoa>(Pessoa.class).atualiza(this.pessoa);
		
		FacesMessages.addInfoMessage("Usuario cadastrado.");
		
		this.pessoa = new Pessoa();

		return "index?faces-redirect=true";

	}

	public void pesquisar() {
		listaPessoas = pessoas.pesquisar(termoPesquisa);

		if (listaPessoas.isEmpty()) {
			messages.info("Sua consulta não retornou registros.");
		}
	}

	public void todasPessoas() {
		listaPessoas = pessoas.todas();
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public TipoPessoa[] getTipoPessoa() {
		return TipoPessoa.values();
	}

	public Converter<?> getSetorAtividadeConverter() {
		return setorAtividadeConverter;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
