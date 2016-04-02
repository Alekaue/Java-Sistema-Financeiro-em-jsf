package com.alekaue.financeiro.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.alekaue.financeiro.interceptor.Transactional;
import com.alekaue.financeiro.model.Lancamento;
import com.alekaue.financeiro.repository.Lancamentos;


public class CadastroLancamentos implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Inject
	private Lancamentos lancamentos;
	
	
	@Transactional 
	public void salvar (Lancamento lancamento) throws NegocioException{
		if(lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())){
			throw new NegocioException("Data de pagamento nÃ£o pode ser uma data futura.");
		}
		this.lancamentos.guardar(lancamento);
	}
	
	@Transactional
	public void excluir(Lancamento lancamento) throws NegocioException {
		lancamento = this.lancamentos.porId(lancamento.getId());
		
		if(lancamento.getDataPagamento() != null) {
			throw new NegocioException("Não é possivel excluir um lançamento pago!");
		}
		
		this.lancamentos.remover(lancamento);
	}
	
	
}
