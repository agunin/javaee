package uy.bse.catalogoaplicaciones.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.bse.catalogoaplicaciones.domain.ComponenteSoftware;
import uy.bse.catalogoaplicaciones.domain.Rol;
import uy.bse.catalogoaplicaciones.domain.RolTipo;
import uy.bse.catalogoaplicaciones.domain.Usuario;
import uy.bse.catalogoaplicaciones.exception.CiDuplicadaException;
import uy.bse.catalogoaplicaciones.exception.EmailDuplicadoException;

/**
 * Servicio EJB para la entity Usuario
 *
 */
@Stateless
public class UsuarioService extends AbstractService<Usuario, Long> {

	@PersistenceContext(unitName = "catalogo_aplicaciones")
	protected EntityManager em;

	public UsuarioService() {
		super(Usuario.class);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarios() {
		return em.createQuery("select u from Usuario u order by u.nombre,u.apellido").getResultList();
	}

	private boolean existeUsuarioByCi(String ci) {
		try {
			Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.documentoIdentidad = ?1");
			query.setParameter(1, ci);

			Object result = query.getSingleResult();
			return result != null;
		} catch (Exception ex) {
			return false;
		}
	}

	private boolean existeUsuarioByEmail(String email) {
		try {
			Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = ?1");
			query.setParameter(1, email);

			Object result = query.getSingleResult();
			return result != null;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	public Usuario actualizar(final Usuario usuario) throws CiDuplicadaException, EmailDuplicadoException, Exception {
		boolean existeCi = existeUsuarioByCi(usuario.getDocumentoIdentidad());
		boolean existeEmail = existeUsuarioByEmail(usuario.getEmail());

		try {
			return em.merge(usuario);
		} catch (Exception ex) {
			if (ex.getCause() != null
					&& ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				if (existeEmail && existeCi) {
					throw new Exception("El documento de identidad " + usuario.getDocumentoIdentidad() + " ya existe.\n"
							+ "El email " + usuario.getEmail() + " ya existe.");
				} else if (existeCi) {
					throw new CiDuplicadaException(
							"El documento de identidad " + usuario.getDocumentoIdentidad() + " ya existe.");
				} else {
					throw new EmailDuplicadoException("El email " + usuario.getEmail() + " ya existe.");
				}
			} else {
				throw new Exception("Error inesperado del servicio.");
			}
		}
	}

	public List<ComponenteSoftware> getComponenteSofwareByUserCi(String ci, RolTipo rol) {

		Query query = em.createQuery("SELECT c FROM Rol r " + "JOIN r.usuario u " + "JOIN r.componenteSoftware c "
				+ "WHERE u.documentoIdentidad = ?1 AND r.rolTipo = ?2");

		query.setParameter(1, ci);
		query.setParameter(2, rol);
		List<ComponenteSoftware> result = query.getResultList();
		return result;
	}

	private void deleteAllRelacionRolUsuario(RolTipo rolTipo, Long idUsuario) {
		Query query = em.createQuery("DELETE FROM Rol r WHERE r.rolTipo = ?1 AND r.usuario.id = ?2");

		query.setParameter(1, rolTipo);
		query.setParameter(2, idUsuario);

		query.executeUpdate();
	}

	public void saveRolComponentes(List<ComponenteSoftware> componentes, RolTipo rolTipo, Usuario usuario)
			throws CiDuplicadaException, EmailDuplicadoException, Exception {
		boolean existeCi = existeUsuarioByCi(usuario.getDocumentoIdentidad());
		boolean existeEmail = existeUsuarioByEmail(usuario.getEmail());
		
		try {
			if (usuario.getId() == null) {
				em.persist(usuario);
				for (ComponenteSoftware cs : componentes) {
					Rol rol = new Rol(rolTipo);
					rol.setUsuario(usuario);
					rol.setComponenteSoftware(cs);

					em.persist(rol);
				}
			} else {
				deleteAllRelacionRolUsuario(rolTipo, usuario.getId());

				em.merge(usuario);
				for (ComponenteSoftware cs : componentes) {
					Rol rol = new Rol(rolTipo);
					rol.setUsuario(usuario);
					rol.setComponenteSoftware(cs);

					em.merge(rol);
				}
			}
		} catch (Exception ex) {
			if (ex.getCause() != null
					&& ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				if (existeEmail && existeCi) {
					throw new Exception("El documento de identidad " + usuario.getDocumentoIdentidad() + " ya existe.\n"
							+ "El email " + usuario.getEmail() + " ya existe.");
				} else if (existeCi) {
					throw new CiDuplicadaException(
							"El documento de identidad " + usuario.getDocumentoIdentidad() + " ya existe.");
				} else {
					throw new EmailDuplicadoException("El email " + usuario.getEmail() + " ya existe.");
				}
			} else {
				throw new Exception("Error inesperado del servicio.");
			}
		}
	}

	public void deleteUsuario(Usuario usuario) {
		deleteAllRelacionRolUsuario(RolTipo.DESARROLLADOR, usuario.getId());
		deleteAllRelacionRolUsuario(RolTipo.OPERADOR_EXTERNO, usuario.getId());
		deleteAllRelacionRolUsuario(RolTipo.OPERADOR_FUNCIONAL, usuario.getId());

		em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
	}

}
