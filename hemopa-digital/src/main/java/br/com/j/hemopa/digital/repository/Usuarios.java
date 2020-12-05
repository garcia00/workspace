package br.com.j.hemopa.digital.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.j.hemopa.digital.model.Usuario;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}
	
	public List<Usuario> usuarios() {
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}


	public  Usuario guarda(Usuario usuario) {
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		usuario = manager.merge(usuario);
		trx.commit();
		
		return usuario;
	}
	
}
