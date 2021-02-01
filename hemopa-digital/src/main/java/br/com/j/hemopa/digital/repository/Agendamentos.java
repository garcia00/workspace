package br.com.j.hemopa.digital.repository;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.j.hemopa.digital.model.Agenda;
import br.com.j.hemopa.digital.model.Pessoa;
import br.com.j.hemopa.digital.repository.filter.PessoaFilter;

public class Agendamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Agendamentos() {

	}

	public Agendamentos(EntityManager manager) {
		this.manager = manager;
	}

	public Agenda porId(Long id) {
		return manager.find(Agenda.class, id);
	}

	public List<Agenda> pesquisar(String tipoEvento) {
		String jpql = " from Agenda where  like : tipoEvento ";

		TypedQuery<Agenda> query = manager.createQuery(jpql, Agenda.class);

		query.setParameter("tipoEvento", tipoEvento + "%");

		return query.getResultList();
	}

	public Agenda guarda(Agenda agenda) {

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		agenda = manager.merge(agenda);
		trx.commit();

		return agenda;
	}

	public void remover(Agenda agenda) {
		agenda = porId(agenda.getId());
		manager.remove(agenda);
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
	
public List<Agenda> buscarPorCriterio(Agenda filtro) {
		
		this.limparTudoDaCache();
		
		final StringBuilder JPQL = new StringBuilder(" select * from agenda fc");
		
		final Map<String, Object> parametros = new HashMap<>();

		JPQL.append(" WHERE 1=1 ");
		
		if (filtro.getTipoEvento() != null) {
			JPQL.append(" AND fc.tipoEvento = :pTipoEvento ");
			parametros.put("pSituacao", filtro.getTipoEvento().getDescricao());

		}
		
		if (filtro.getDataInicio() != null) {
			JPQL.append(" AND (CAST(TO_CHAR(fc.dataInicio, 'ddMMyyyy') AS INT) >= :pDataInicio) ");
			parametros.put("pDataInicio", Integer.parseInt(new SimpleDateFormat("ddMMyyyy").format(filtro.getDataInicio())));
		}

		if (filtro.getDataFim() != null) {
			JPQL.append(" AND (CAST(TO_CHAR(fc.datInicio, 'ddMMyyyy') AS INT) <= :pDataFiml) ");
			parametros.put("pDataFinal", Integer.parseInt(new SimpleDateFormat("ddMMyyyy").format(filtro.getDataFim())));
		}

		JPQL.append(" ORDER BY fc.dt_inclusao DESC");

		final TypedQuery<Agenda> query = this.getEntityManager().createQuery(JPQL.toString(), Agenda.class);

		for (final String parametro : parametros.keySet()) {
			query.setParameter(parametro, parametros.get(parametro));

		}

		return (List<Agenda>) query.getResultList();
		
	}
	
	public void limparTudoDaCache() {

		// L1
		this.getEntityManager().clear();

		// L2
		final Cache cache = this.getEntityManager().getEntityManagerFactory()
				.getCache();
		cache.evictAll();
	}
	
	public EntityManager getEntityManager() {

		return this.getEntityManager();
	}
	

}
