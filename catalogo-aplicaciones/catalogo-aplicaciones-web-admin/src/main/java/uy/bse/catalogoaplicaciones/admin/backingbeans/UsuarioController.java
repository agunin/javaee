package uy.bse.catalogoaplicaciones.admin.backingbeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import uy.bse.catalogoaplicaciones.domain.Usuario;
import uy.bse.catalogoaplicaciones.ejbs.UsuarioService;

@Named("usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	@EJB
	UsuarioService usuarioService;

	public UsuarioController() {

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Retorno la lista de usuarios cargadas en la BD
	 * 
	 * @return List<Usuario>
	 */
	public List<Usuario> getListaUsuarios() {
		return usuarioService.getUsuarios();

	}

	/**
	 * Agrega una nueva usuario ala BD
	 * 
	 * @return String con la regla de navegacion
	 */
	public String crearUsuario() {
		usuarioService.update(usuario);
		return "usuarios.xhtml?faces-redirect=true";
	}

	/**
	 * Busca a una usuario por el ID Si la usuario existe en la BD retorna esa
	 * usuario En caso contrario crea una nueva
	 */
	public void findUsuarioById() {
		if (usuario.getId() != null) {
			usuario = usuarioService.find(usuario.getId());
			if (usuario == null) {
				usuario = new Usuario();
			}
		}
	}

	/**
	 * Elimina una usuario de la BD
	 * 
	 * @param p
	 */
	public void eliminar(Usuario p) {
		usuarioService.delete(p);
	}

	public boolean isManaged(Long id) {
		return id != null;
	}
}
