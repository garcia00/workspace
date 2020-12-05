package br.com.j.hemopa.digital.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.j.hemopa.digital.model.Usuario;
import br.com.j.hemopa.digital.service.DAO;
import br.com.j.hemopa.digital.util.FacesMessages;

@Named
@SessionScoped
public class UsuariosMB implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NAVEGACAO = "/user-form?faces-redirect=true";

	private Usuario usuario;

	public String getNavegacao() {

		return UsuariosMB.NAVEGACAO;

	}

	public String prepararNovoUsuario() {
		usuario = new Usuario();
		return getNavegacao();
	}

	public List<Usuario> getUsuarios() {
		return new DAO<Usuario>(Usuario.class).listaTodos();
	}

	public String salvar() {

		new DAO<Usuario>(Usuario.class).atualiza(this.usuario);
		
		FacesMessages.addInfoMessage("Usuario cadastrado.");
		
		this.usuario = new Usuario();

		return "index?faces-redirect=true";

	}
	
	public void carregar(Usuario usuario) {
		
		this.usuario = usuario;
	}

	public void remover(Usuario usuario) {
		
		new DAO<Usuario>(Usuario.class).remove(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
