package uy.bse.catalogoaplicaciones.ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uy.bse.catalogoaplicaciones.domain.BaseEntity;

/**
 * Servicio base utilizado desde el BaseEntityConverter
 * @see uy.org.curso.converters.BaseEntityConverter
 * expone un unico metodo que se utiliza desde el converter
 * @author juan
 *
 */
@Stateless
public class BaseService {

	@PersistenceContext(unitName = "catalogo_aplicaciones")
    private EntityManager em;

    public BaseEntity<? extends Number> find(Class<BaseEntity<? extends Number>> type, Number id) {
        return em.find(type, id);
    }
}