package uy.bse.catalogoaplicaciones.admin.backingbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import uy.bse.catalogoaplicaciones.domain.Solucion;

@Named
@ViewScoped
public class SolucionWizard implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//Referenciar al Solucion Controller
	@Inject
	SolucionController solucionController;
	
	//Referenciar al Aplicacion Controller
	@Inject
	AplicacionController aplicacionController;
	
	//private Solucion solucion = new Solucion();

    private boolean skip;

    public Solucion getSolucion() {
        return solucionController.getSolucion();
    }

    public void setsolucion(Solucion solucion) {
        this.solucionController.setSolucion(solucion);
    }

    public void save() {
    	
    	
		/*
		 * usuarioService.update(usuario); return "usuarios.xhtml?faces-redirect=true";
		 */
    	//Persisitir la entidad Solucion
    	solucionController.crearSolucion();
    	
    	
    	
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + solucionController.getSolucion().getIdentificador());
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
            skip = false; //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
}