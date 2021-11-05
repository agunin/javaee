package uy.bse.catalogoaplicaciones.ejbs;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import uy.bse.catalogoaplicaciones.domain.*;



@Singleton
@Startup
public class CargaDBEJB {
	
	
	@PersistenceContext(unitName = "catalogo_aplicaciones")
	protected EntityManager em;
	
	
	@PostConstruct
	public void init() {
		System.out.println("************ PostConstruct SingletonEJB ************");			
		
		Solucion sol = new Solucion("Solucion1","Esta es la Solucion1");
		em.persist(sol);
		em.flush();
		
		
		Rol rol = new Rol(RolTipo.DESARROLLADOR);
		em.persist(rol);
		em.flush();
		
		
		Interface inter = new Interface("Interface1","Esta es la Interface1",InterfaceTipo.PROCEDURE_SQL);
		em.persist(inter);
		em.flush();
		
		Aplicacion app = new Aplicacion("Aplicacion1","Esta es la Aplicacion1",AplicacionLenguaje.JAVA);
		em.persist(app);
		em.flush();
		
		app.addConsumeInterface(inter);
		
		em.persist(app);
		em.flush();
		
	
	}
	
	
	@PreDestroy
	public void destroy() {
		System.out.println("************ PreDestroy SingletonEJB ************");
		
	}

}
