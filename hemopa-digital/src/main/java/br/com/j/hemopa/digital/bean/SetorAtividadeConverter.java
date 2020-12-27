package br.com.j.hemopa.digital.bean;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.j.hemopa.digital.model.SetorAtividade;


@FacesConverter(forClass = SetorAtividade.class)
public class SetorAtividadeConverter implements Converter<Object> {
    
    private List<SetorAtividade> listaSetorAtividades;

    public SetorAtividadeConverter(List<SetorAtividade> listaSetorAtividades) {
        this.listaSetorAtividades = listaSetorAtividades;
    }


	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
            return null;
        }
        
        Long id = Long.valueOf(value);
        
        for (SetorAtividade setorAtividade: listaSetorAtividades) {
            if (id.equals(setorAtividade.getId())) {
                return setorAtividade;
            }
        }
        
        return null;
    }

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		 if (value == null) {
	            return null;
	        }
	        
	        SetorAtividade setorAtividade = (SetorAtividade) value;
	        
	        return setorAtividade.getId().toString();
	}
}