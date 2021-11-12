package uy.bse.catalogoaplicaciones.admin.backingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
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

	@Inject
	SolucionController solucionController;

	@Inject
	AplicacionesWizardController aplicacionesWizardController;
	
	@Inject
	InterfacesWizardController interfacesWizardController;

	private boolean skip;
	
	
	@PostConstruct
    public void init() {
		if (solucionController.getSolucion().getId() != null) {
			Solucion s = solucionController.getSolucion();
			
			List<Aplicacion> aplicaciones = new ArrayList<Aplicacion>();
			List<Interface> interfaces = new ArrayList<Interface>();
			
			for (ComponenteSoftware c : s.getComponentesSoftware()) {
				
				if (c instanceof Interface) {  
				    Interface i = (Interface)c; 
				    interfaces.add(i); 
				}
				 
				if (c instanceof Aplicacion) {
					Aplicacion a = (Aplicacion)c;
					aplicaciones.add(a);
				}
				
			}
			
			aplicacionesWizardController.setSelectedAplicaciones(aplicaciones);
			interfacesWizardController.setInterfaces(interfaces);
	
		}
    	
    }

	public Solucion getSolucion() {
		
		return solucionController.getSolucion();
	}

	public void setsolucion(Solucion solucion) {
		this.solucionController.setSolucion(solucion);
	}

	public String save() {


		Set<ComponenteSoftware> componentesSoftware = new HashSet<ComponenteSoftware>();
		
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
			if(tabName.compareTo("interfaces") == 0 ) {
				interfacesWizardController.initOnDemand();
			}
			
			
			
			return event.getNewStep();
		}
	}
}