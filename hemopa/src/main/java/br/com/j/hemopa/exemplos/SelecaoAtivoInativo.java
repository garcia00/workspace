package br.com.j.hemopa.exemplos;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class SelecaoAtivoInativo {

	private boolean selecao;

	public boolean isSelecao() {
		return selecao;
	}

	public void setSelecao(boolean selecao) {
		this.selecao = selecao;
	}

	public void msgRetorno() {
		String mensagemRetorno = selecao ? "Selecionado" : "Não Selecionado";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensagemRetorno));
	}
}
