package uy.bse.catalogoaplicaciones.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.bse.catalogoaplicaciones.domain.ComponenteSoftware;
import uy.bse.catalogoaplicaciones.domain.RolTipo;
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
	
	public List<ComponenteSoftware> getAllComponentes(){
		
		Query query = em.createQuery("SELECT c FROM ComponenteSoftware c");
		
		List<ComponenteSoftware> result = query.getResultList();
		return result;
	}
	
	public ComponenteSoftware findComponenteById(Long id){
		
		Query query = em.createQuery("SELECT c FROM ComponenteSoftware c where c.id = ?1");
		query.setParameter(1, id);
		
		ComponenteSoftware result = (ComponenteSoftware)query.getSingleResult();
		return result;
	}	
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
