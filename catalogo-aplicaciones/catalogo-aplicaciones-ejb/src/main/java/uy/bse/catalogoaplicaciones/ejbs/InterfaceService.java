package uy.bse.catalogoaplicaciones.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uy.bse.catalogoaplicaciones.domain.Aplicacion;
import uy.bse.catalogoaplicaciones.domain.Interface;

@Stateless
public class InterfaceService extends AbstractService<Interface, Long> {

	@PersistenceContext(unitName = "catalogo_aplicaciones")
	protected EntityManager em;


	public InterfaceService() {
		super(Interface.class);
	}

	@SuppressWarnings("unchecked")
	public List<Interface> getInterfaces() {
		return em.createQuery("select i from Interfaz a=i ").getResultList();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
	

	
}