package br.com.j.hemopa.digital.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import br.com.j.hemopa.digital.model.Agenda;
import br.com.j.hemopa.digital.util.JPAUtil;

public class AgendamentoDAO {

	public void adiciona(Agenda agenda) {

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();

		// abre transacao
		em.getTransaction().begin();

		// persiste o objeto
		em.persist(agenda);

		// commita a transacao
		em.getTransaction().commit();

		// fecha a entity manager
		em.close();
	}

	public void remove(Agenda agenda) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.remove(em.merge(agenda));

		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(Agenda agenda) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.merge(agenda);

		em.getTransaction().commit();
		em.close();
	}

	public List<Agenda> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<Agenda> query = em.getCriteriaBuilder().createQuery(Agenda.class);
		query.select(query.from(Agenda.class));

		List<Agenda> lista = em.createQuery(query).getResultList();

		em.close();

		return lista;
	}

	public Agenda buscaPorId(Integer id) {
		EntityManager em = new JPAUtil().getEntityManager();
		Agenda instancia = em.find(Agenda.class, id);
		em.close();
		return instancia;
	}

	public int contaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery(" select count(n) from Agenda n ").getSingleResult();
		em.close();

		return (int) result;
	}

	public List<Agenda> listaTodosPaginada(int firstResult, int maxResults) {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<Agenda> query = em.getCriteriaBuilder().createQuery(Agenda.class);
		query.select(query.from(Agenda.class));

		List<Agenda> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();

		em.close();
		return lista;
	}

	public Agenda buscaPorId(Long id) {

		EntityManager em = new JPAUtil().getEntityManager();
		Agenda instancia = em.find(Agenda.class, id);
		em.close();
		return instancia;

	}

	public EntityManager getEntityManager() {

		return this.getEntityManager();
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

}
