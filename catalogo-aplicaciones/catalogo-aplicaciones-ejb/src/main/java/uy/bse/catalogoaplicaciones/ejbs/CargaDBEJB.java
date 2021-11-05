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
		
		
		
		Aplicacion app1 = new Aplicacion("Aplicacion1","Esta es la Aplicacion1",AplicacionLenguaje.JAVA);
		em.persist(app1);
		em.flush();
		
		Aplicacion app2 = new Aplicacion("Aplicacion2","Esta es la Aplicacion2",AplicacionLenguaje.PHP);
		em.persist(app2);
		em.flush();
		
		Aplicacion app3 = new Aplicacion("Aplicacion3","Esta es la Aplicacion3",AplicacionLenguaje.PENTAHO);
		em.persist(app3);
		em.flush();
		
		Aplicacion app4 = new Aplicacion("Aplicacion4","Esta es la Aplicacion4",AplicacionLenguaje.JAVA);
		em.persist(app4);
		em.flush();
		
		Aplicacion app5 = new Aplicacion("Aplicacion5","Esta es la Aplicacion5",AplicacionLenguaje.JAVA);
		em.persist(app5);
		em.flush();
		
		Aplicacion app6 = new Aplicacion("Aplicacion6","Esta es la Aplicacion6",AplicacionLenguaje.PHP);
		em.persist(app6);
		em.flush();
		
		Aplicacion app7 = new Aplicacion("Aplicacion7","Esta es la Aplicacion7",AplicacionLenguaje.JAVA);
		em.persist(app7);
		em.flush();
		
		Aplicacion app8 = new Aplicacion("Aplicacion8","Esta es la Aplicacion8",AplicacionLenguaje.APEX);
		em.persist(app8);
		em.flush();
		
		Aplicacion app9 = new Aplicacion("Aplicacion9","Esta es la Aplicacion9",AplicacionLenguaje.JAVA);
		em.persist(app9);
		em.flush();
		
		Aplicacion app10 = new Aplicacion("Aplicacion10","Esta es la Aplicacion10",AplicacionLenguaje.JAVA);
		em.persist(app10);
		em.flush();
		

		
		
		/*
		 * app1.addConsumeInterface(inter);
		 * 
		 * em.persist(app1); em.flush();
		 */
		
	
	}
	
	
	@PreDestroy
	public void destroy() {
		System.out.println("************ PreDestroy SingletonEJB ************");
		
	}

}
