package uy.bse.catalogoaplicaciones.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uy.bse.catalogoaplicaciones.domain.Usuario;

/**
 * Servicio EJB para la entity Usuario
 * @author juan
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
		return em.createQuery("select u from Usurio p order by u.nombre,u.apellido").getResultList();
	}
		
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
