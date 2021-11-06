package uy.bse.catalogoaplicaciones.ejbs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uy.bse.catalogoaplicaciones.domain.Aplicacion;

@Stateless
public class AplicacionService extends AbstractService<Aplicacion, Long> {

	@PersistenceContext(unitName = "catalogo_aplicaciones")
	protected EntityManager em;

	private List<Aplicacion> aplicaciones;

	public AplicacionService() {
		super(Aplicacion.class);
	}

	@SuppressWarnings("unchecked")
	public List<Aplicacion> getAplicaciones() {
		return em.createQuery("select a from Aplicacion a ").getResultList();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	/*
	 * @PostConstruct public void init() { aplicaciones = new ArrayList<>();
	 * aplicaciones.add(new Aplicacion(1000, "f230fh0g3", "Bamboo Watch",
	 * "Aplicacion Description", "bamboo-watch.jpg", 65, "Accessories", 24,
	 * InventoryStatus.INSTOCK, 5)); aplicaciones.add(new Aplicacion(1001,
	 * "nvklal433", "Black Watch", "Aplicacion Description", "black-watch.jpg", 72,
	 * 
	 * 
	 * }
	 */

	public List<Aplicacion> getAplicaciones1() {
		return new ArrayList<>(aplicaciones);
	}

	public List<Aplicacion> getAplicaciones(int size) {

		if (size > aplicaciones.size()) {
			Random rand = new Random();

			List<Aplicacion> randomList = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				int randomIndex = rand.nextInt(aplicaciones.size());
				randomList.add(aplicaciones.get(randomIndex));
			}

			return randomList;
		}

		else {
			return new ArrayList<>(aplicaciones.subList(0, size));
		}

	}

	/*
	 * public List<Aplicacion> getClonedAplicaciones(int size) { List<Aplicacion>
	 * results = new ArrayList<>(); List<Aplicacion> originals =
	 * getAplicaciones(size); for (Aplicacion original : originals) {
	 * results.add(original.clone()); } return results; }
	 */
}