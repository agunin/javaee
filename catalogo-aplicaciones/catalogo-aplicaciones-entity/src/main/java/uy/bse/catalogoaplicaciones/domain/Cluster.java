package uy.bse.catalogoaplicaciones.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CLUSTER")
public class Cluster extends SolInfra {

	private static final long serialVersionUID = 1L;

	public Cluster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cluster(String identificador, String descripcion) {
		super(identificador, descripcion);
	}

}

