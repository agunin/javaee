package uy.bse.catalogoaplicaciones.backingbeans;

import java.util.Date;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import uy.bse.catalogoaplicaciones.model.LoginLogeado;
import uy.bse.catalogoaplicaciones.model.LoginModel;


@Model
public class LoginController {
	
	private String username;
	private String password;
	
	@Inject
	private LoginModel loginModel;
	
	@Inject
	private LoginLogeado loginLogeado;
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String autenticar() {		
		if (loginModel.autenticar(username, password)) {
			FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO,
	                		"Bienvenido usuario", null));
			
			loginLogeado.setUsername(username);
			loginLogeado.setDate(new Date());
			
			return "home.xhtml?faces-redirect=true";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                		"El usuario o la contrasenia no son correctos", null));
			return "";		
		}
	}
	
	public String logout() {
		loginLogeado.setUsername(null);
		loginLogeado.setDate(null);
		
		return "login.xhtml?faces-redirect=true";
		
	}

}
