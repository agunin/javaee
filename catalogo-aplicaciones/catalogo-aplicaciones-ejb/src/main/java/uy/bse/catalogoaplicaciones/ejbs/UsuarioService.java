package uy.bse.catalogoaplicaciones.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.bse.catalogoaplicaciones.domain.ComponenteSoftware;
import uy.bse.catalogoaplicaciones.domain.Interface;
import uy.bse.catalogoaplicaciones.domain.RolTipo;
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
	
	public List<ComponenteSoftware> getComponenteSofwareByUserCi(String ci,RolTipo rol){
		
		Query query = em.createQuery("SELECT c FROM Rol r "
				+ "JOIN r.usuarios u "
				+ "JOIN r.componentesSoftware c "
				+ "WHERE u.documentoIdentidad = ?1 AND r.rolTipo = ?2");
		
		
		
		query.setParameter(1,ci);
		query.setParameter(2,rol);
		List<ComponenteSoftware> result = query.getResultList();
		return result;
	}
}
