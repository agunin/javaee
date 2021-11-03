package uy.bse.catalogoaplicaciones.jpa.test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import uy.bse.catalogoaplicaciones.domain.Aplicacion;
import uy.bse.catalogoaplicaciones.domain.AplicacionLenguaje;


/**
 * Ejemplos básicos - Prueba relacion ManyToMany
 * 
 * Lectura recomendada:
 * 
 * https://thorben-janssen.com/best-practices-for-many-to-many-associations-with-hibernate-and-jpa/
 * 
 * ADVERTENCIA: Las relaciones toMany por defecto son LAZY. OJO con cambiar
 * esto, puede ser peligroso y traer problemas de performance
 */

public class PruebaJPA {
	
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_aplicaciones");
	private EntityManager em;
	private EntityTransaction tx;
	
	//Se ejecuta antes de cada test
	@Before
	public void initEntityManager() throws Exception {
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	//Se ejecuta luego de cada test
	@After
	public void closeEntityManager() {
		if (em != null) em.close();
	}

	//Se ejecuta una sola vez luego de terminado los test
	@AfterClass
	public static void closeFactory() {
       emf.close();
	}
	
	/**
	 * ManyToMany bidireccional mapeada utilizandao java.util.List -> Cascade.ALL Se
	 * elimina un elemento de la coleccion de libros del autor
	 */
	@Test
	public void manyToManyUnidirectionalRemove() {
		//Aplicacion app = new Aplicacion(AplicacionLenguaje.JAVA,null,null);

		//tx.begin();
		//em.persist(app);
		//tx.commit();

		/*
		 * Long autorId = autor.getId(); Long libroId =
		 * autor.getLibrosEscritos().get(0).getId();
		 * 
		 * tx.begin(); em.clear(); //Observar que se recupera en la bd cuando se consuta
		 * por un autor Libro aux = em.find(Libro.class, libroId); autor =
		 * em.find(Autor.class, autorId);
		 * 
		 * //Observar en detalle que pasa cuando se remueve un libro //Fijarse como
		 * borra toda la coleccion (join table) y luego inserta todos nuevamente menos
		 * el eliminado autor.getLibrosEscritos().remove(aux); tx.commit();
		 * 
		 */ 
		 ///assertTrue(app.getAplicacionLenguaje() == AplicacionLenguaje.JAVA);
		 

	}

	
	

}
