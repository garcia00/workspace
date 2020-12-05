package br.com.j.hemopa.digital.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.j.hemopa.digital.model.SetorAtividade;



public class SetorAtividades implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public SetorAtividades() {

	}

	public SetorAtividades(EntityManager manager) {
		this.manager = manager;
	}

	public List<SetorAtividade> pesquisar(String descricao) {

		String jpql = "from SetorAtividade where descricao like :descricao";

		TypedQuery<SetorAtividade> query = manager.createQuery(jpql, SetorAtividade.class);

		query.setParameter("descricao", descricao + "%");

		return query.getResultList();
	}
}
