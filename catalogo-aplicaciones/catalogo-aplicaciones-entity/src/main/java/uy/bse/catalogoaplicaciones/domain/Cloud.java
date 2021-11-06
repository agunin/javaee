package uy.bse.catalogoaplicaciones.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CLOUD")
public class Cloud extends SolInfra {

	private static final long serialVersionUID = 1L;
	
	public Cloud() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cloud(String identificador, String descripcion) {
		super(identificador, descripcion);
	}


}
