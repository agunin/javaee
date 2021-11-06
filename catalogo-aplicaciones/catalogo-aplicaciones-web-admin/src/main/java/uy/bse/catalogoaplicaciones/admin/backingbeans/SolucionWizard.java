package uy.bse.catalogoaplicaciones.admin.backingbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;
import org.primefaces.model.TreeNode;

import uy.bse.catalogoaplicaciones.domain.Aplicacion;
import uy.bse.catalogoaplicaciones.domain.Interface;
import uy.bse.catalogoaplicaciones.domain.Solucion;

@Named
@ViewScoped
public class SolucionWizard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Referenciar al Solucion Controller
	@Inject
	SolucionController solucionController;

	// Referenciar al Aplicacion Controller
	@Inject
	AplicacionesWizardController aplicacionesWizardController;
	
	//Referenciar al Aplicacion Controller
	@Inject
	InterfacesWizardController interfacesWizardController;

	// private Solucion solucion = new Solucion();

	private boolean skip;

	public Solucion getSolucion() {
		return solucionController.getSolucion();
	}

	public void setsolucion(Solucion solucion) {
		this.solucionController.setSolucion(solucion);
	}

	public void save() {

		// Persisitir la entidad Solucion

		List<Aplicacion> applicacionesSeleccionadas = aplicacionesWizardController.getSelectedAplicaciones();

		for (Aplicacion aplicacion : applicacionesSeleccionadas) {
			solucionController.getSolucion().getComponentesSoftware().add(aplicacion);
		}
		
		TreeNode[] interfacesSeleccionadas = interfacesWizardController.getSelectedNodes2();

		for (TreeNode i : interfacesSeleccionadas) {
			Interface inter = (Interface)i.getData();
			solucionController.getSolucion().getComponentesSoftware().add(inter);
		}

		solucionController.crearSolucion();

		FacesMessage msg = new FacesMessage("Successful",
				"Welcome :" + solucionController.getSolucion().getIdentificador());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}
}