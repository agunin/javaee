package uy.bse.catalogoaplicaciones.ejbs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uy.bse.catalogoaplicaciones.domain.Aplicacion;
import uy.bse.catalogoaplicaciones.domain.Interface;
import uy.bse.catalogoaplicaciones.domain.Product;
import uy.bse.catalogoaplicaciones.domain.Solucion;

/**
 * Servicio EJB para la entity Usuario
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
			List result = em.createQuery("select a from Aplicacion a where a.identificador like '%" + palabraClave
					+ "%' or a.descripcion like '%" + palabraClave + "%'").getResultList();
			for (Object obj : result) {
				Aplicacion a = (Aplicacion) obj;
				componentes.add(new Product(a.getId(), "APLICACION", a.getIdentificador(), a.getDescripcion()));
			}
			result = em.createQuery("select i from Interface i where i.identificador like '%" + palabraClave
					+ "%' or i.descripcion like '%" + palabraClave + "%'").getResultList();
			for (Object obj : result) {
				Interface a = (Interface) obj;
				Product p = new Product(a.getId(), "INTERFACE", a.getIdentificador(), a.getDescripcion());
				p.getComponentes().add(a);
				componentes.add(p);
			}
			result = em.createQuery("select s from Solucion s where s.identificador like '%" + palabraClave
					+ "%' or s.descripcion like '%" + palabraClave + "%'").getResultList();
			for (Object obj : result) {
				Solucion s = (Solucion) obj;
				Product p = new Product(s.getId(), "SOLUCION", s.getIdentificador(), s.getDescripcion());
				p.setComponentes(s.getComponentesSoftware());
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
				p.setComponentes(s.getComponentesSoftware());
				componentes.add(p);
			}
		}
		return componentes;
	}

}
