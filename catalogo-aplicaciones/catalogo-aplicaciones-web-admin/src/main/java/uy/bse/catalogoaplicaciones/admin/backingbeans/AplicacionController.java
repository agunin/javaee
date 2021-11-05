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

@Named("aplicacionController")
@ViewScoped
public class AplicacionController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Aplicacion> aplicaciones;
    
	private Aplicacion selectedAplicacion;
    
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

    public Aplicacion getSelectedAplicacion() {
        return selectedAplicacion;
    }

    public void setSelectedAplicacion(Aplicacion selectedplicacion) {
        this.selectedAplicacion = selectedplicacion;
    }

    public List<Aplicacion> getSelectedAplicaciones() {
        return selectedAplicaciones;
    }

    public void setSelectedAplicaciones(List<Aplicacion> selectedplicaciones) {
        this.selectedAplicaciones = selectedplicaciones;
    }

    public void onRowSelect(SelectEvent<Aplicacion> event) {
        FacesMessage msg = new FacesMessage("Aplicacion Selected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent<Aplicacion> event) {
        FacesMessage msg = new FacesMessage("Aplicacion Unselected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}