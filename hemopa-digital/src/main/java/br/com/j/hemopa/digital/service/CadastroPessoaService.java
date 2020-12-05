package br.com.j.hemopa.digital.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.j.hemopa.digital.model.Pessoa;
import repository.Pessoas;


public class CadastroPessoaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoas pessoas;
	
	@Transactional
	public void salvar(Pessoa pessoa) {
		pessoas.guardar(pessoa);
	}
	
	@Transactional
	public void excluir(Pessoa pessoa) {
		pessoas.remover(pessoa);
	}

}
