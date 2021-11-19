package uy.bse.catalogoaplicaciones.ejbs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.bse.catalogoaplicaciones.domain.Aplicacion;
import uy.bse.catalogoaplicaciones.domain.ComponenteSoftware;
import uy.bse.catalogoaplicaciones.domain.Interface;
import uy.bse.catalogoaplicaciones.domain.Product;
import uy.bse.catalogoaplicaciones.domain.Rol;
import uy.bse.catalogoaplicaciones.domain.Solucion;

/**
 * Servicio EJB para el frontend
 *
 */
@Stateless
public class SearchViewService {

	private static String TIPO_COMPONENTE_TODOS = "0";
	private static String TIPO_COMPONENTE_APLICACION = "1";
	private static String TIPO_COMPONENTE_INTERFACE = "2";
	private static String TIPO_COMPONENTE_SOLUCION = "3";

	@PersistenceContext(unitName = "catalogo_aplicaciones")
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Product> getComponentes(String tipoComponente, String palabraClave) {
		List<Product> componentes = new ArrayList<Product>();

		if (tipoComponente.equals(TIPO_COMPONENTE_TODOS)) {
			// busca en Aplicacion
			List result = em.createQuery("select a from Aplicacion a where a.identificador like '%" + palabraClave
					+ "%' or a.descripcion like '%" + palabraClave + "%'").getResultList();
			for (Object obj : result) {
				Aplicacion a = (Aplicacion) obj;
				Product p = new Product(a.getId(), "APLICACION", a.getIdentificador(), a.getDescripcion());
				p.getComponentes().add(a);
				componentes.add(p);
			}

			// busca en Interface
			result = em.createQuery("select i from Interface i where i.identificador like '%" + palabraClave
					+ "%' or i.descripcion like '%" + palabraClave + "%'").getResultList();
			for (Object obj : result) {
				Interface a = (Interface) obj;
				Product p = new Product(a.getId(), "INTERFACE", a.getIdentificador(), a.getDescripcion());
				p.getComponentes().add(a);
				componentes.add(p);
			}

			// busca en solucion
			result = em.createQuery("select s from Solucion s where s.identificador like '%" + palabraClave
					+ "%' or s.descripcion like '%" + palabraClave + "%'").getResultList();
			for (Object obj : result) {
				Solucion s = (Solucion) obj;
				Product p = new Product(s.getId(), "SOLUCION", s.getIdentificador(), s.getDescripcion());
				p.setComponentes(crearSetComponentesSoftware(s.getComponentesSoftware()));
				componentes.add(p);
			}
		} else if (tipoComponente.equals(TIPO_COMPONENTE_APLICACION)) {
			List result = em.createQuery("select a from Aplicacion a where a.identificador like '%" + palabraClave
					+ "%' or a.descripcion like '%" + palabraClave + "%'").getResultList();
			for (Object obj : result) {
				Aplicacion a = (Aplicacion) obj;
				Product p = new Product(a.getId(), "APLICACION", a.getIdentificador(), a.getDescripcion());
				p.getComponentes().add(a);
				componentes.add(p);
			}
		} else if (tipoComponente.equals(TIPO_COMPONENTE_INTERFACE)) {
			List result = em.createQuery("select i from Interface i where i.identificador like '%" + palabraClave
					+ "%' or i.descripcion like '%" + palabraClave + "%'").getResultList();
			for (Object obj : result) {
				Interface a = (Interface) obj;
				Product p = new Product(a.getId(), "INTERFACE", a.getIdentificador(), a.getDescripcion());
				p.getComponentes().add(a);
				componentes.add(p);
			}
		} else { // TIPO_COMPONENTE_SOLUCION
			List result = em.createQuery("select s from Solucion s where s.identificador like '%" + palabraClave
					+ "%' or s.descripcion like '%" + palabraClave + "%'").getResultList();
			for (Object obj : result) {
				Solucion s = (Solucion) obj;
				Product p = new Product(s.getId(), "SOLUCION", s.getIdentificador(), s.getDescripcion());
				p.setComponentes(crearSetComponentesSoftware(s.getComponentesSoftware()));
				componentes.add(p);
			}
		}
		
		for (Product product : componentes) {
			for (ComponenteSoftware cs : product.getComponentes()) {
				product.setRoles(getAllRolComponenteSofware(cs.getId()));
			}
		}

		return componentes;
	}

	// arma la lista de aplicaciones con las interfaces que provee pero que fueron
	// seleccionadas al crear la solucion
	private List<ComponenteSoftware> crearSetComponentesSoftware(Set<ComponenteSoftware> componentes) {
		List<ComponenteSoftware> csResult = new ArrayList<ComponenteSoftware>();
		List<ComponenteSoftware> csTemp = new ArrayList<ComponenteSoftware>();

		// crea la lista con las aplicaciones que tiene la solucion
		// estas aplicaciones trae todas las interfaces que provee
		// y todas las interfaces que consume
		for (ComponenteSoftware cs : componentes) {
			if (cs instanceof Aplicacion) {
				csTemp.add(cs);
			}
		}

		// crea la lista con las aplicaciones que tiene la solucion
		// y las interfaces que provee y consume vacias
		for (ComponenteSoftware cs : componentes) {
			if (cs instanceof Aplicacion) {
				Aplicacion app = (Aplicacion) cs;
				Aplicacion newApp = new Aplicacion(app.getIdentificador(), app.getDescripcion(),
						app.getAplicacionLenguaje());
				newApp.setId(app.getId());
				Set<Interface> proveeInterface = new HashSet<Interface>();
				Set<Interface> consumeInterface = new HashSet<Interface>();
				newApp.setProveeInterface(proveeInterface);
				newApp.setConsumeInterface(consumeInterface);
				csResult.add(newApp);
			}
		}

		for (ComponenteSoftware cs : componentes) {
			if (cs instanceof Interface) {
				armarInterfacesProveeSeleccionadas(cs.getId(), csTemp, csResult);
			}
		}

		return csResult;
	}

	private void armarInterfacesProveeSeleccionadas(Long idInterface, List<ComponenteSoftware> csTemp,
			List<ComponenteSoftware> csResult) {
		for (ComponenteSoftware componenteSoftwareTemp : csTemp) {
			Aplicacion appTemp = (Aplicacion) componenteSoftwareTemp;
			for (Interface interfaceProvee : appTemp.getProveeInterface()) {
				if (interfaceProvee.getId() == idInterface) {
					for (ComponenteSoftware componenteSoftwareResult : csResult) {
						Aplicacion appResult = (Aplicacion) componenteSoftwareResult;
						if (appResult.getId() == appTemp.getId()) {
							appResult.addProveeInterface(interfaceProvee);
						}
					}
				}
			}
		}
	}
	
	private List<Rol> getAllRolComponenteSofware(Long idComponente) {
		Query query = em.createQuery("SELECT r FROM Rol r WHERE r.componenteSoftware.id = ?1");

		query.setParameter(1, idComponente);
		List<Rol> result = query.getResultList();
		
		return result;
	}

}
