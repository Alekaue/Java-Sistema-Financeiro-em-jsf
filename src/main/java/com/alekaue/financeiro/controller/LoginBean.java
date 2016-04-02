package com.alekaue.financeiro.controller;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.alekaue.financeiro.model.Usuario;

@Named
@RequestScoped
public class LoginBean implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;
	
	private String nomeUsuario;
	private String senha;
	
	public String login(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		if("admin".equals(this.nomeUsuario) && "123".equals(this.senha)){
			this.usuario.setNome(this.nomeUsuario);
			this.usuario.setDataLogin(new Date());
			
			return "/ConsultaLancamentos?faces-redirect=true";
		}else {
			FacesMessage mensagem = new FacesMessage("Usuario/senha inválidos!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage (null, mensagem);
		}
		
		return null;
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().
		invalidateSession();
		return "/Login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	
		
}
