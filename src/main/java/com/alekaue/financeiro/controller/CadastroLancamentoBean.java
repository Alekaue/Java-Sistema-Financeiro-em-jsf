package com.alekaue.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.alekaue.financeiro.model.Lancamento;
import com.alekaue.financeiro.model.Pessoa;
import com.alekaue.financeiro.model.TipoLancamento;
import com.alekaue.financeiro.repository.Lancamentos;
import com.alekaue.financeiro.repository.Pessoas;
import com.alekaue.financeiro.service.CadastroLancamentos;
import com.alekaue.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	@Inject
	private Pessoas pessoas;
	
	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;
	
	@Inject
	private Lancamentos lancamentos;
	
	public List<String> pesquisarDescricao(String descricao){
		return this.lancamentos.descricoesQueContem(descricao);
	}
	
	public void dataVencimentoAlterada(AjaxBehaviorEvent event){
		if(this.lancamento.getDataPagamento() == null){
			this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
		}
	}
	
	public void prepararCadastro() {
			this.todasPessoas = this.pessoas.todas();
			
			if(this.lancamento == null){
				this.lancamento = new Lancamento();
			}
		}

	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			this.cadastro.salvar(this.lancamento);
			
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lancamento salvo com sucesso!."));
		}catch(NegocioException e){
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		} 
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<Pessoa> getTodasPessoas() {
		return todasPessoas;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	
	
	
}
