package uy.bse.catalogoaplicaciones.ejbs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.bse.catalogoaplicaciones.domain.Aplicacion;
import uy.bse.catalogoaplicaciones.domain.Interface;

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

	public List<Interface> getInterfacesProvistasByAplicacionIdentificador(String identificador) {

		Query query = em.createQuery("Select a.proveeInterface from Aplicacion a where a.identificador = ?1 ");

		query.setParameter(1, identificador);
		List<Interface> result = query.getResultList();
		return result;

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

}