package br.com.j.hemopa.digital.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.j.hemopa.digital.model.Pessoa;
import br.com.j.hemopa.digital.repository.filter.PessoaFilter;

public class Pessoas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Pessoas() {

	}

	public Pessoas(EntityManager manager) {
		this.manager = manager;
	}

	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}

	public List<Pessoa> pesquisar(String cpf) {
		String jpql = " from Pessoa where cpf like : cpf ";

		TypedQuery<Pessoa> query = manager.createQuery(jpql, Pessoa.class);

		query.setParameter("cpf", cpf + "%");

		return query.getResultList();
	}

	public Pessoa guarda(Pessoa pessoa) {

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

	@SuppressWarnings("unchecked")
	public List<Pessoa> filtrados(PessoaFilter filtro) {
		Session session = manager.unwrap(Session.class);
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Pessoa.class);

		if (StringUtils.isNotBlank(filtro.getCpf())) {
			criteria.add(Restrictions.eq("cpf", filtro.getCpf()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}
	
	public List<Pessoa> agendas() {
		// TODO filtrar apenas vendedores (por um grupo específico)
		return this.manager.createQuery("from pessoa", Pessoa.class)
				.getResultList();
	}
	
	

}
