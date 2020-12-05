package br.com.j.hemopa.digital.repository;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.j.hemopa.digital.model.Pessoa;



public class Pessoas implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public Pessoas() {

	}

	public Pessoas(EntityManager manager) {
		this.manager = manager;
	}

	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}

	public List<Pessoa> pesquisar(String nome) {
		String jpql = "from Pessoa where nome like :nome";
		
		TypedQuery<Pessoa> query = manager
				.createQuery(jpql, Pessoa.class);
		
		query.setParameter("nome", nome + "%");
		
		return query.getResultList();
	}

	public Pessoa guardar(Pessoa pessoa) {
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		pessoa = manager.merge(pessoa);
		trx.commit();
		
		return pessoa;
		
	}

	public void remover(Pessoa pessoa) {
		pessoa = porId(pessoa.getId());
		manager.remove(pessoa);
	}
	
	

}
