package uy.bse.catalogoaplicaciones.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uy.bse.catalogoaplicaciones.domain.Solucion;

/**
 * Servicio EJB para la entity Solucion
 *
 */
@Stateless
public class SolucionService extends AbstractService<Solucion, Long>{
	
	@PersistenceContext(unitName = "catalogo_aplicaciones")
	protected EntityManager em;
		
	
	public SolucionService() {
		super(Solucion.class);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Solucion> getSoluciones() {
		return em.createQuery("select s from Solucion s").getResultList();
	}
		
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
