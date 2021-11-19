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

		
		/************************************************** Usuarios ****************************************************/
		
		Usuario u1 = new Usuario("Agustin", "Nin", "4.105.973-1","aguquimnin@gmail.com");
		em.persist(u1);
		em.flush();
		
		Usuario u2 = new Usuario("Luciana", "Canales", "3.234.675-9","lucanales@gmail.com");
		em.persist(u2);
		em.flush();
		
		Usuario u3 = new Usuario("Laura", "Rodriguez", "3.111.575-3","laurodriguez@gmail.com");
		em.persist(u3);
		em.flush();
		Usuario u4 = new Usuario("Pablo", "Perez", "2.311.575-1","pabloperez@gmail.com");
		em.persist(u4);
		em.flush();
		
		/****************************************************************************************************************/
		/************************************************ Interfaces ****************************************************/
		Interface inter = new Interface("ICubos", "Interface para publicar cubos.", InterfaceTipo.PROCEDURE_SQL);
		em.persist(inter);
		em.flush();
		Rol rol_inter = new Rol(RolTipo.DESARROLLADOR);
		rol_inter.setUsuario(u1);
		rol_inter.setComponenteSoftware(inter);
		em.persist(rol_inter);
		em.flush();
		
		Interface inter1 = new Interface("ICargaHoras", "Interface para la carga de hora de funcionarios.", InterfaceTipo.REST);
		em.persist(inter1);
		em.flush();
		Rol rol_inter1 = new Rol(RolTipo.DESARROLLADOR);
		rol_inter1.setUsuario(u1);
		rol_inter1.setComponenteSoftware(inter1);
		em.persist(rol_inter);
		em.flush();
		
		Interface inter2 = new Interface("IFacturacionInterna", "Interface para el calculo de facturación interna de la empresa.", InterfaceTipo.PROCEDURE_SQL);
		em.persist(inter2);
		em.flush();
		Rol rol_inter2 = new Rol(RolTipo.DESARROLLADOR);
		rol_inter2.setUsuario(u2);
		rol_inter2.setComponenteSoftware(inter2);
		em.persist(rol_inter2);
		em.flush();
		
		Interface inter3 = new Interface("IDatalogicGIA", "Interface externa provista por Datalogic para la gestión de rrhh.", InterfaceTipo.SOAP);
		em.persist(inter3);
		em.flush();
		Interface inter4 = new Interface("IDatalogicDGI", "Interface externa provista por Datalogic para la gestión con dgi.", InterfaceTipo.SOAP);
		em.persist(inter4);
		em.flush();
		Interface inter6 = new Interface("IBMP", "Interface externa provista por IBM.", InterfaceTipo.SOAP);
		em.persist(inter6);
		em.flush();
		
		Interface inter5 = new Interface("ISiniestros", "Interface que provee los métodos necesarios para el proceso del siniestro.", InterfaceTipo.REST);
		em.persist(inter5);
		em.flush();
		Rol rol_inter5 = new Rol(RolTipo.DESARROLLADOR);
		rol_inter5.setUsuario(u2);
		rol_inter5.setComponenteSoftware(inter5);
		em.persist(rol_inter5);
		em.flush();
		
		Interface inter7 = new Interface("IPresentismo", "Interface para la gestión de licencias de funcionarios.", InterfaceTipo.REST);
		em.persist(inter7);
		em.flush();
		Rol rol_inter7 = new Rol(RolTipo.DESARROLLADOR);
		rol_inter7.setUsuario(u2);
		rol_inter7.setComponenteSoftware(inter7);
		em.persist(rol_inter7);
		em.flush();
		
		Interface inter8 = new Interface("ISueldos", "Interface para la gestión de sueldos de funcionarios", InterfaceTipo.SOAP);
		em.persist(inter8);
		em.flush();
		Rol rol_inter8 = new Rol(RolTipo.DESARROLLADOR);
		rol_inter8.setUsuario(u2);
		rol_inter8.setComponenteSoftware(inter8);
		em.persist(rol_inter8);
		em.flush();

		/****************************************************************************************************************/
		/************************************************ Aplicaciones **************************************************/
		
		
		Aplicacion app1 = new Aplicacion("AppPentahoServer", "Aplicacion web para integracion de suite Pentaho.", AplicacionLenguaje.PENTAHO);
		em.persist(app1);
		em.flush();
		// interfaces que provee
		app1.addProveeInterface(inter);
		app1.addProveeInterface(inter6);
		em.persist(app1);
		em.flush();
		Rol rol_app1 = new Rol(RolTipo.DESARROLLADOR);
		rol_app1.setUsuario(u1);
		rol_app1.setComponenteSoftware(app1);
		em.persist(rol_app1);
		em.flush();
		
		Aplicacion app2 = new Aplicacion("ApppPentahoDataIntegration", "Aplicacion para desarrollar ETLs.", AplicacionLenguaje.PENTAHO);
		em.persist(app2);
		em.flush();
		// interfaces que consume
		app2.addConsumeInterface(inter6);
		em.persist(app2);
		em.flush();
		Rol rol_app2 = new Rol(RolTipo.DESARROLLADOR);
		rol_app2.setUsuario(u1);
		rol_app2.setComponenteSoftware(app2);
		em.persist(rol_app2);
		em.flush();
		
		Aplicacion app3 = new Aplicacion("ApppPentahoSchemaWorkbench", "Aplicacion para desarrollar Cubos.", AplicacionLenguaje.PENTAHO);
		em.persist(app3);
		em.flush();
		// interfaces que consume
		app3.addConsumeInterface(inter6);
		em.persist(app3);
		em.flush();
		Rol rol_app3 = new Rol(RolTipo.DESARROLLADOR);
		rol_app3.setUsuario(u1);
		rol_app3.setComponenteSoftware(app3);
		em.persist(rol_app3);
		em.flush();

		Aplicacion app4 = new Aplicacion("AppRRHH", "Aplicacion para la gestion de rrhh.", AplicacionLenguaje.JAVA);
		em.persist(app4);
		em.flush();
		// interfaces que provee
		app4.addProveeInterface(inter1);
		app4.addProveeInterface(inter7);
		app4.addProveeInterface(inter8);
		em.persist(app4);
		em.flush();
		// interfaces que consume
		app4.addConsumeInterface(inter3);
		em.persist(app4);
		em.flush();
		Rol rol_app4 = new Rol(RolTipo.DESARROLLADOR);
		rol_app4.setUsuario(u2);
		rol_app4.setComponenteSoftware(app4);
		em.persist(rol_app4);
		em.flush();
		Rol rolFun_app4 = new Rol(RolTipo.OPERADOR_FUNCIONAL);
		rolFun_app4.setUsuario(u3);
		rolFun_app4.setComponenteSoftware(app4);
		em.persist(rolFun_app4);
		em.flush();

		Aplicacion app5 = new Aplicacion("AppFacturacion", "Aplicacion para la gestion de facturación de seguros.", AplicacionLenguaje.JAVA);
		em.persist(app5);
		em.flush();
		// interfaces que provee
		app5.addProveeInterface(inter2);
		app5.addProveeInterface(inter5);
		em.persist(app5);
		em.flush();
		// interfaces que consume
		app5.addConsumeInterface(inter4);
		em.persist(app5);
		em.flush();
		Rol rol_app5 = new Rol(RolTipo.DESARROLLADOR);
		rol_app5.setUsuario(u2);
		rol_app5.setComponenteSoftware(app5);
		em.persist(rol_app5);
		em.flush();
		Rol rolFun_app5 = new Rol(RolTipo.OPERADOR_FUNCIONAL);
		rolFun_app5.setUsuario(u4);
		rolFun_app5.setComponenteSoftware(app5);
		em.persist(rolFun_app5);
		em.flush();
		
		/****************************************************************************************************************/
		/************************************************** Solucion ****************************************************/	
		Solucion sol = new Solucion("SolCentralBanco", "Solucion core para gestion de cada accion del banco.");
		em.persist(sol);
		em.flush();
		sol.addComponentesSoftware(app1);
		sol.addComponentesSoftware(app2);
		sol.addComponentesSoftware(app3);
		sol.addComponentesSoftware(app4);
		sol.addComponentesSoftware(app5);
		sol.addComponentesSoftware(inter7);
		sol.addComponentesSoftware(inter8);
		sol.addComponentesSoftware(inter5);
		em.persist(sol);
		em.flush();
		/****************************************************************************************************************/
		/************************************************** SolInfra ****************************************************/	
		
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
		
		/****************************************************************************************************************/
		/************************************************** Ambiente ****************************************************/	
		
		Ambiente ambiente01 = new Ambiente(AmbienteTipo.PRODUCCION,8080,"192.198.1.1");
		ambiente01.setAplicacion(app1);
		ambiente01.setSolInfra(servidor1);
		em.persist(ambiente01);
		em.flush();
	}

	@PreDestroy
	public void destroy() {
		System.out.println("************ PreDestroy SingletonEJB ************");

	}

}
