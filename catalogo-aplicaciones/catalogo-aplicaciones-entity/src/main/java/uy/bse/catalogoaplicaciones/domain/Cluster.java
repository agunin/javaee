package uy.bse.catalogoaplicaciones.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CLUSTER")
public class Cluster extends SolIfra {

	private static final long serialVersionUID = 1L;

}

