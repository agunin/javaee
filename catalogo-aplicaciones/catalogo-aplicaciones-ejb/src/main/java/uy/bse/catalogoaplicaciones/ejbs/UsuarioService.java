package uy.bse.catalogoaplicaciones.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.bse.catalogoaplicaciones.domain.ComponenteSoftware;
import uy.bse.catalogoaplicaciones.domain.Interface;
import uy.bse.catalogoaplicaciones.domain.Usuario;

/**
 * Servicio EJB para la entity Usuario
 *
 */
@Stateless
public class UsuarioService extends AbstractService<Usuario, Long>{
	
	@PersistenceContext(unitName = "catalogo_aplicaciones")
	protected EntityManager em;
		
	
	public UsuarioService() {
		super(Usuario.class);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarios() {
		return em.createQuery("select u from Usuario u order by u.nombre,u.apellido").getResultList();
	}
		
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
	public List<ComponenteSoftware> getComponenteSofwareByUserCi(String ci){
		/*
		 * Query query = em.
		 * createQuery("Select a.proveeInterface from Aplicacion a where a.identificador = ?1 "
		 * );
		 * 
		 * query.setParameter(1,identificador); List<Interface> result =
		 * query.getResultList(); return result;
		 */
		return null;
	}
}
