package com.alekaue.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.alekaue.financeiro.model.Lancamento;
import com.alekaue.financeiro.repository.Lancamentos;
import com.alekaue.financeiro.service.CadastroLancamentos;
import com.alekaue.financeiro.service.NegocioException;

@Named 
@javax.faces.view.ViewScoped
public class ConsultaLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private Lancamentos lancamentosRepository;
	private List<Lancamento> lancamentos;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	private Lancamento lancamentoSelecionado;
	
	public void excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			this.cadastro.excluir(this.lancamentoSelecionado);
			this.consultar();
			
			context.addMessage(null, new FacesMessage("Lançamento excluído com sucesso"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
		
	}
	
	public void consultar(){		
		this.lancamentos = lancamentosRepository.todos();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public Lancamentos getLancamentosRepository() {
		return lancamentosRepository;
	}

	public void setLancamentosRepository(Lancamentos lancamentosRepository) {
		this.lancamentosRepository = lancamentosRepository;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}
	
	
	
	
	
	
}
