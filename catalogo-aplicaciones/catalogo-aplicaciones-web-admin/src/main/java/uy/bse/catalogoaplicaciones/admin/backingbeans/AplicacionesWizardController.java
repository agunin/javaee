package uy.bse.catalogoaplicaciones.admin.backingbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import uy.bse.catalogoaplicaciones.domain.Aplicacion;
import uy.bse.catalogoaplicaciones.ejbs.AplicacionService;

@Named("aplicacionesWizardController")
@ViewScoped
public class AplicacionesWizardController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Aplicacion> aplicaciones;
   
    
	private List<Aplicacion> selectedAplicaciones;

    @Inject
    private AplicacionService service;

    @PostConstruct
    public void init() {
      
        aplicaciones = service.getAplicaciones();
    }

    public List<Aplicacion> getAplicaciones() {
        return aplicaciones;
    }

    public void setService(AplicacionService service) {
        this.service = service;
    }

    public List<Aplicacion> getSelectedAplicaciones() {
        return selectedAplicaciones;
    }

    public void setSelectedAplicaciones(List<Aplicacion> selectedplicaciones) {
        this.selectedAplicaciones = selectedplicaciones;
    }

	
}