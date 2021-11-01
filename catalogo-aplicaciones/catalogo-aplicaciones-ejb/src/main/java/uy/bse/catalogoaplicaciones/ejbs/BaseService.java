package uy.bse.catalogoaplicaciones.ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uy.bse.catalogoaplicaciones.domain.BaseEntity;

@Stateless
public class BaseService {

	@PersistenceContext(unitName = "catalogo_aplicaciones")
    private EntityManager em;

    public BaseEntity<? extends Number> find(Class<BaseEntity<? extends Number>> type, Number id) {
        return em.find(type, id);
    }
}