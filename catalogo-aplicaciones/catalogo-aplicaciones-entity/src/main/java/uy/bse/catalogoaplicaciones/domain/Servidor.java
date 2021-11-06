package uy.bse.catalogoaplicaciones.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SERVIDOR")
public class Servidor extends SolInfra {

	private static final long serialVersionUID = 1L;
	
	public Servidor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Servidor(String identificador, String descripcion) {
		super(identificador, descripcion);
	}


}
