package uy.bse.catalogoaplicaciones.admin.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import uy.bse.catalogoaplicaciones.domain.Login;


//Mock de autenticacion contra servicio
@SessionScoped
public class LoginModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Set<Login> loginUsuarios = new HashSet<Login>();

	@PostConstruct
	public void init() {
		Login u1 = new Login("admin", "admin");
		Login u2 = new Login("login1", "admin1");
		Login u3 = new Login("login2", "admin2");
		Login u4 = new Login("login3", "admin3");
		Login u5 = new Login("login4", "admin4");
		
		loginUsuarios.add(u1);
		loginUsuarios.add(u2);
		loginUsuarios.add(u3);
		loginUsuarios.add(u4);
		loginUsuarios.add(u5);
	}
	
	
	public Login findLoginUsuario(String username) {
		return loginUsuarios.stream()
				  .filter(Login -> username.equals(Login.getUsername()))
				  .findFirst()
				  .orElse(null);
	}
	
	public boolean autenticar(String username, String password) {
		boolean autenticar = false;
		
		Login Login = findLoginUsuario(username);
		
		if (Login != null && Login.getPassword().equals(password)) {
			autenticar = true;
		}
		return autenticar;
	}
}
