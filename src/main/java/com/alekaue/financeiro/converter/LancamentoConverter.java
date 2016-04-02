package com.alekaue.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.alekaue.financeiro.model.Lancamento;
import com.alekaue.financeiro.repository.Lancamentos;
import com.alekaue.financeiro.util.CDILocator;

@FacesConverter(forClass = Lancamento.class)
public class LancamentoConverter implements Converter{

	private Lancamentos lancamentos;
	
	public LancamentoConverter() {
		this.lancamentos = CDILocator.getBean(Lancamentos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Lancamento retorno = null;
		if(value != null && !"".equals(value)){
			retorno = this.lancamentos.porId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Lancamento lancamento = ((Lancamento) value);
			return lancamento.getId() == null ? null : lancamento.getId().toString();
		}
		
		return null;
	}

}
