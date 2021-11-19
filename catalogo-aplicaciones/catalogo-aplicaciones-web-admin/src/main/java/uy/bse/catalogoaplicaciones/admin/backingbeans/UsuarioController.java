package uy.bse.catalogoaplicaciones.admin.backingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.PrimeFaces;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import uy.bse.catalogoaplicaciones.domain.ComponenteSoftware;
import uy.bse.catalogoaplicaciones.domain.RolTipo;
import uy.bse.catalogoaplicaciones.domain.Usuario;
import uy.bse.catalogoaplicaciones.ejbs.SolucionService;
import uy.bse.catalogoaplicaciones.ejbs.UsuarioService;
import uy.bse.catalogoaplicaciones.exception.CiDuplicadaException;
import uy.bse.catalogoaplicaciones.exception.EmailDuplicadoException;

@Named("usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	@EJB
	UsuarioService usuarioService;

	@EJB
	SolucionService solucionService;

	private String rol;
	private DualListModel<ComponenteSoftware> lstComponentesDesarrollador;
	private DualListModel<ComponenteSoftware> lstComponentesOpFuncional;
	private DualListModel<ComponenteSoftware> lstComponentesOpExterno;

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
		try {
			if (lstComponentesDesarrollador == null && lstComponentesOpExterno == null
					&& lstComponentesOpFuncional == null) {
				usuarioService.actualizar(usuario);
			} else {
				if (lstComponentesDesarrollador != null && lstComponentesDesarrollador.getTarget() != null)
					usuarioService.saveRolComponentes(lstComponentesDesarrollador.getTarget(), RolTipo.DESARROLLADOR,
							usuario);

				if (lstComponentesOpExterno != null && lstComponentesOpExterno.getTarget() != null)
					usuarioService.saveRolComponentes(lstComponentesOpExterno.getTarget(), RolTipo.OPERADOR_EXTERNO,
							usuario);

				if (lstComponentesOpFuncional != null && lstComponentesOpFuncional.getTarget() != null)
					usuarioService.saveRolComponentes(lstComponentesOpFuncional.getTarget(), RolTipo.OPERADOR_FUNCIONAL,
							usuario);

			}

			return "usuarios.xhtml?faces-redirect=true";
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));

			return null;
		}
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
	public void eliminar(Usuario u) {
		usuarioService.deleteUsuario(u);
	}

	public boolean isManaged(Long id) {
		return id != null;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public DualListModel<ComponenteSoftware> getLstComponentesDesarrollador() {
		if (lstComponentesDesarrollador == null) {

			List<ComponenteSoftware> componentesTarget = new ArrayList<ComponenteSoftware>();
			if (usuario.getId() != null) {
				componentesTarget = usuarioService.getComponenteSofwareByUserId(usuario.getId(), RolTipo.DESARROLLADOR);
			}

			List<ComponenteSoftware> componentesSource = getSourceComponentes(componentesTarget);

			lstComponentesDesarrollador = new DualListModel<>(componentesSource, componentesTarget);
		}

		return lstComponentesDesarrollador;
	}

	public void setLstComponentesDesarrollador(DualListModel<ComponenteSoftware> lstComponentesDesarrollador) {
		this.lstComponentesDesarrollador = lstComponentesDesarrollador;
	}

	public DualListModel<ComponenteSoftware> getLstComponentesOpFuncional() {
		if (lstComponentesOpFuncional == null) {
			List<ComponenteSoftware> componentesTarget = new ArrayList<ComponenteSoftware>();
			if (usuario.getId() != null) {
				componentesTarget = usuarioService.getComponenteSofwareByUserId(usuario.getId(),
						RolTipo.OPERADOR_FUNCIONAL);
			}

			List<ComponenteSoftware> componentesSource = getSourceComponentes(componentesTarget);
			lstComponentesOpFuncional = new DualListModel<>(componentesSource, componentesTarget);
		}

		return lstComponentesOpFuncional;
	}

	public void setLstComponentesOpFuncional(DualListModel<ComponenteSoftware> lstComponentesOpFuncional) {
		this.lstComponentesOpFuncional = lstComponentesOpFuncional;
	}

	public DualListModel<ComponenteSoftware> getLstComponentesOpExterno() {
		if (lstComponentesOpExterno == null) {
			List<ComponenteSoftware> componentesTarget = new ArrayList<ComponenteSoftware>();
			if (usuario.getId() != null) {
				componentesTarget = usuarioService.getComponenteSofwareByUserId(usuario.getId(),
						RolTipo.OPERADOR_EXTERNO);
			}

			List<ComponenteSoftware> componentesSource = getSourceComponentes(componentesTarget);

			lstComponentesOpExterno = new DualListModel<>(componentesSource, componentesTarget);
		}
		return lstComponentesOpExterno;
	}

	public void setLstComponentesOpExterno(DualListModel<ComponenteSoftware> lstComponentesOpExterno) {
		this.lstComponentesOpExterno = lstComponentesOpExterno;
	}

	private List<ComponenteSoftware> getSourceComponentes(List<ComponenteSoftware> componentesTarget) {
		List<ComponenteSoftware> componentesTmp = solucionService.getAllComponentes();
		List<ComponenteSoftware> componentesSource = new ArrayList<ComponenteSoftware>();
		for (ComponenteSoftware cs : componentesTmp) {
			if (!esTarget(cs.getId(), componentesTarget)) {
				componentesSource.add(cs);
			}
		}
		return componentesSource;
	}

	private boolean esTarget(Long id, List<ComponenteSoftware> componentesTarget) {
		for (ComponenteSoftware cs : componentesTarget) {
			if (cs.getId() == id)
				return true;
		}
		return false;
	}

	public void onTransfer(TransferEvent event) {
	}

}
