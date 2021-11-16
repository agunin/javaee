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

		Usuario u1 = new Usuario("Agustin", "Nin", "41059731","aguquimnin@gmail.com");
		em.persist(u1);
		em.flush();
		
		Usuario u2 = new Usuario("Luciana", "Canales", "40000001","lcanales@gmail.com");
		em.persist(u2);
		em.flush();
		
		
		

		
		
		

		Interface inter = new Interface("Interface1", "Esta es la Interface", InterfaceTipo.PROCEDURE_SQL);
		em.persist(inter);
		em.flush();

		Interface inter1 = new Interface("Interface2", "Esta es la Interface1", InterfaceTipo.PROCEDURE_SQL);
		em.persist(inter1);
		em.flush();

		Interface inter2 = new Interface("Interface2", "Esta es la Interface2", InterfaceTipo.PROCEDURE_SQL);
		em.persist(inter2);
		em.flush();
		
		Interface inter3 = new Interface("Interface3", "Esta es la Interface3", InterfaceTipo.BATCH);
		em.persist(inter3);
		em.flush();
		Interface inter4 = new Interface("Interface4", "Esta es la Interface4", InterfaceTipo.REST);
		em.persist(inter4);
		em.flush();
		Interface inter5 = new Interface("Interface5", "Esta es la Interface5", InterfaceTipo.PROCEDURE_SQL);
		em.persist(inter5);
		em.flush();
		Interface inter6 = new Interface("Interface6", "Esta es la Interface6", InterfaceTipo.SOAP);
		em.persist(inter6);
		em.flush();
		Interface inter7 = new Interface("Interface7", "Esta es la Interface7", InterfaceTipo.PROCEDURE_SQL);
		em.persist(inter7);
		em.flush();
		Interface inter8 = new Interface("Interface8", "Esta es la Interface8", InterfaceTipo.BATCH);
		em.persist(inter8);
		em.flush();
		Interface inter9 = new Interface("Interface9", "Esta es la Interface9", InterfaceTipo.BATCH);
		em.persist(inter9);
		em.flush();
		Interface inter10 = new Interface("Interface10", "Esta es la Interface10", InterfaceTipo.REST);
		em.persist(inter10);
		em.flush();

		Aplicacion app1 = new Aplicacion("pdi", "Esta es la Aplicacion1", AplicacionLenguaje.JAVA);
		em.persist(app1);
		em.flush();

		app1.addProveeInterface(inter);
		em.persist(app1);
		em.flush();
		
		app1.addConsumeInterface(inter);
		em.persist(app1);
		em.flush();
		

		Aplicacion app2 = new Aplicacion("Aplicacion2", "Esta es la Aplicacion2", AplicacionLenguaje.PHP);
		em.persist(app2);
		em.flush();

		app2.addProveeInterface(inter1);
		app2.addProveeInterface(inter2);
		app2.addProveeInterface(inter3);
		app2.addProveeInterface(inter4);
		app2.addProveeInterface(inter5);
		app2.addProveeInterface(inter6);
		app2.addProveeInterface(inter7);
		app2.addProveeInterface(inter8);
		app2.addProveeInterface(inter9);
		app2.addProveeInterface(inter10);
		em.persist(app2);
		em.flush();
		
		app2.addConsumeInterface(inter1);
		app2.addConsumeInterface(inter2);
		app2.addConsumeInterface(inter3);
		app2.addConsumeInterface(inter4);
		app2.addConsumeInterface(inter5);
		app2.addConsumeInterface(inter6);
		app2.addConsumeInterface(inter7);
		app2.addConsumeInterface(inter8);
		app2.addConsumeInterface(inter9);
		app2.addConsumeInterface(inter10);
		em.persist(app2);
		em.flush();

		app2.addProveeInterface(inter2);
		em.persist(app2);
		em.flush();

		Aplicacion app3 = new Aplicacion("Aplicacion3", "Esta es la Aplicacion3", AplicacionLenguaje.PENTAHO);
		em.persist(app3);
		em.flush();

		Aplicacion app4 = new Aplicacion("Aplicacion4", "Esta es la Aplicacion4", AplicacionLenguaje.JAVA);
		em.persist(app4);
		em.flush();

		Aplicacion app5 = new Aplicacion("Aplicacion5", "Esta es la Aplicacion5", AplicacionLenguaje.JAVA);
		em.persist(app5);
		em.flush();

		Aplicacion app6 = new Aplicacion("Aplicacion6", "Esta es la Aplicacion6", AplicacionLenguaje.PHP);
		em.persist(app6);
		em.flush();

		Aplicacion app7 = new Aplicacion("Aplicacion7", "Esta es la Aplicacion7", AplicacionLenguaje.JAVA);
		em.persist(app7);
		em.flush();

		Aplicacion app8 = new Aplicacion("Aplicacion8", "Esta es la Aplicacion8", AplicacionLenguaje.APEX);
		em.persist(app8);
		em.flush();

		Aplicacion app9 = new Aplicacion("Aplicacion9", "Esta es la Aplicacion9", AplicacionLenguaje.JAVA);
		em.persist(app9);
		em.flush();

		Aplicacion app10 = new Aplicacion("Aplicacion10", "Esta es la Aplicacion10", AplicacionLenguaje.JAVA);
		em.persist(app10);
		em.flush();

		Tienda tienda1 = new Tienda("Identificador_tienda_1",
				"Descripción de la tienda 1 que debe ser minimo de sesenta caracteres.");
		em.persist(tienda1);
		em.flush();
		Tienda tienda2 = new Tienda("Identificador_tienda_2",
				"Descripción de la tienda 2 que debe ser minimo de sesenta caracteres.");
		em.persist(tienda2);
		em.flush();
		Servidor servidor1 = new Servidor("Identificador_servidor_1",
				"Descripción del servidor 1 que debe ser minimo de sesenta caracteres.");
		em.persist(servidor1);
		em.flush();
		Servidor servidor2 = new Servidor("Identificador_servidor_2",
				"Descripción del servidor 2 que debe ser minimo de sesenta caracteres.");
		em.persist(servidor2);
		em.flush();
		Cloud cloud1 = new Cloud("Identificador_cloud_1",
				"Descripción del cloud 1 que debe ser minimo de sesenta caracteres.");
		em.persist(cloud1);
		em.flush();
		Cloud cloud2 = new Cloud("Identificador_cloud_2",
				"Descripción del cloud 2 que debe ser minimo de sesenta caracteres.");
		em.persist(cloud2);
		em.flush();
		Cluster cluster1 = new Cluster("Identificador_cluster_1",
				"Descripción del cluster 1 que debe ser minimo de sesenta caracteres.");
		em.persist(cluster1);
		em.flush();
		Cluster cluster2 = new Cluster("Identificador_cluster_2",
				"Descripción del cluster 2 que debe ser minimo de sesenta caracteres.");
		em.persist(cluster2);
		em.flush();
		
		
		
		Rol u1_desarrollador_app1 = new Rol(RolTipo.DESARROLLADOR);
		u1_desarrollador_app1.setUsuario(u1);
		u1_desarrollador_app1.setComponenteSoftware(app1);
		//desarrollador.addComponenteSoftware(app2);
		em.persist(u1_desarrollador_app1);
		em.flush();
		
	
		
		Rol u2_desarrollador_app1 = new Rol(RolTipo.DESARROLLADOR);
		u2_desarrollador_app1.setUsuario(u2);
		u2_desarrollador_app1.setComponenteSoftware(app1);
		//desarrollador.addComponenteSoftware(app2);
		em.persist(u2_desarrollador_app1);
		em.flush();
		
		Rol u1_funcional_app1 = new Rol(RolTipo.OPERADOR_FUNCIONAL);
		u1_funcional_app1.setUsuario(u1);
		u1_funcional_app1.setComponenteSoftware(app2);
		em.persist(u1_funcional_app1);
		em.flush();

	}

	@PreDestroy
	public void destroy() {
		System.out.println("************ PreDestroy SingletonEJB ************");

	}

}
