package uy.bse.catalogoaplicaciones.admin.backingbeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import uy.bse.catalogoaplicaciones.domain.Solucion;
import uy.bse.catalogoaplicaciones.ejbs.SolucionService;

@Named("solucionController")
@ViewScoped
public class SolucionController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Solucion solucion = new Solucion();

	@EJB
	SolucionService solucionService;

	public SolucionController() {

	}

	public Solucion getSolucion() {
		return solucion;
	}

	public void setSolucion(Solucion solucion) {
		this.solucion = solucion;
	}

	/**
	 * Retorno la lista de usuarios cargadas en la BD
	 * 
	 * @return List<Usuario>
	 */
	public List<Solucion> getListaSoluciones() {
		return solucionService.getSoluciones();

	}

	/**
	 * Agrega una nueva usuario ala BD
	 * 
	 * @return String con la regla de navegacion
	 */
	public String crearSolucion() {
		solucionService.update(solucion);
		return "soluciones.xhtml?faces-redirect=true";
	}

	/**
	 * Busca a una usuario por el ID Si la usuario existe en la BD retorna esa
	 * usuario En caso contrario crea una nueva
	 */
	public void findSolucionById() {
		if (solucion.getId() != null) {
			solucion = solucionService.find(solucion.getId());
			if (solucion == null) {
				solucion = new Solucion();
			}
		}
	}

	/**
	 * Elimina una solucion de la BD
	 * 
	 * @param p
	 */
	public void eliminar(Solucion s) {
		solucionService.delete(s);
	}

	public boolean isManaged(Long id) {
		return id != null;
	}
}
