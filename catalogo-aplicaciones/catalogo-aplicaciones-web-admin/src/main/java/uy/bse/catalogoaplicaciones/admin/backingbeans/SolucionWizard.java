package uy.bse.catalogoaplicaciones.admin.backingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;
import org.primefaces.model.TreeNode;

import uy.bse.catalogoaplicaciones.domain.Aplicacion;
import uy.bse.catalogoaplicaciones.domain.ComponenteSoftware;
import uy.bse.catalogoaplicaciones.domain.Interface;
import uy.bse.catalogoaplicaciones.domain.Solucion;

@Named
@ViewScoped
public class SolucionWizard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Referenciar al SolucionController
	@Inject
	SolucionController solucionController;

	//Referenciar al AplicacionController
	@Inject
	AplicacionesWizardController aplicacionesWizardController;
	
	//Referenciar al InterfaceController
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

	public String save() {


		List<ComponenteSoftware> componentesSoftware = new ArrayList<ComponenteSoftware>();
		
		/******** Aplicaciones Seleccionadas *********/
		List<Aplicacion> applicacionesSeleccionadas = aplicacionesWizardController.getSelectedAplicaciones();
		for (Aplicacion aplicacion : applicacionesSeleccionadas) {
			componentesSoftware.add(aplicacion);
		}
		
		/******** Interfaces Seleccionadas *********/
		TreeNode[] interfacesSeleccionadas = interfacesWizardController.getSelectedNodes2();
		for (TreeNode t : interfacesSeleccionadas) {
			
			if (t.getData() instanceof Interface) {
			
			Interface inter = (Interface)t.getData();
				componentesSoftware.add(inter);
			}
		}
		
		/******** ComponenteSoftware en Solucion *********/
		solucionController.getSolucion().setComponentesSoftware(componentesSoftware);
		solucionController.crearSolucion();
		

		
		FacesMessage msg = new FacesMessage("Exito",
				"Solucion creada :" + solucionController.getSolucion().getIdentificador());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return "soluciones.xhtml?faces-redirect=true";
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
			return "confirmacion";
		} else {
			String tabName = event.getNewStep().toString();
			if(tabName.compareTo("interfaces") == 0) {
				interfacesWizardController.initOnDemand();
			}
			return event.getNewStep();
		}
	}
}