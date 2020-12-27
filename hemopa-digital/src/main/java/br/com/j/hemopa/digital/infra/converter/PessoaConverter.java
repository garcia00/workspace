package br.com.j.hemopa.digital.infra.converter;

import javax.faces.convert.FacesConverter;

import br.com.j.hemopa.digital.model.Pessoa;
import br.com.j.hemopa.digital.repository.Pessoas;
import br.com.j.hemopa.digital.util.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter<Object>{

	// @Inject
	private Pessoas pessoas;

	public PessoaConverter() {
		pessoas = CDIServiceLocator.getBean(Pessoas.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = pessoas.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pessoa produto = (Pessoa) value;
			return produto.getId() == null ? null : produto.getId().toString();
		}

		return "";
	}
}