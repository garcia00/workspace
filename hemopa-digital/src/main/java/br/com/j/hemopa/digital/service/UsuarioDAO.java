package br.com.j.hemopa.digital.service;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.j.hemopa.digital.model.Usuario;
import br.com.j.hemopa.digital.util.JPAUtil;

public class UsuarioDAO {
	
	public void adiciona(Usuario usuario) {

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();

		// abre transacao
		em.getTransaction().begin();

		// persiste o objeto
		em.persist(usuario);

		// commita a transacao
		em.getTransaction().commit();

		// fecha a entity manager
		em.close();
	}

	public boolean existe(Usuario usuario) {

		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Usuario> query = em
				.createQuery(" select u from Usuario u " 
							+ " where u.email = :pEmail and u.senha = :pSenha ", Usuario.class);
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		
		try {
			
			Usuario resultado = query.getSingleResult();
			
		}catch (NoResultException ex) {
			
			return false;
			
		}
		

		em.close();

		return true;
	}
}
