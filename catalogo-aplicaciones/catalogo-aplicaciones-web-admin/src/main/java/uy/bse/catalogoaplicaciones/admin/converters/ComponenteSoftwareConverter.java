package uy.bse.catalogoaplicaciones.admin.converters;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import uy.bse.catalogoaplicaciones.domain.ComponenteSoftware;
import uy.bse.catalogoaplicaciones.ejbs.SolucionService;

@Named
@RequestScoped
public class ComponenteSoftwareConverter implements Converter {
	
	@EJB
	private SolucionService solucionService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || "".equals(value.trim())) {
			return null;
		} 
		
		ComponenteSoftware cs = solucionService.findComponenteById(Long.valueOf(value));
		 
		 return cs;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof ComponenteSoftware)
		{
			ComponenteSoftware p = (ComponenteSoftware) value;
            return String.valueOf( p.getId());
		}
		else
		{
			return "";
		}
	}
}
