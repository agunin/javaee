package uy.bse.catalogoaplicaciones.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TIENDA")
public class Tienda extends SolInfra {

	private static final long serialVersionUID = 1L;

	public Tienda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tienda(String identificador, String descripcion) {
		super(identificador, descripcion);
	}

}