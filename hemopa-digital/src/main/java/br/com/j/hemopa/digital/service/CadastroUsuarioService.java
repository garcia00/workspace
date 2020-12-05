package br.com.j.hemopa.digital.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.j.hemopa.digital.model.Usuario;
import br.com.j.hemopa.digital.repository.Usuarios;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	public Usuario salvar(Usuario usuario) {
		
		return this.usuarios.guarda(usuario);
	}

}
