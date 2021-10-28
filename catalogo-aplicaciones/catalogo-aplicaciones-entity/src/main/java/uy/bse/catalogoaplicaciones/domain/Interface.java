package uy.bse.catalogoaplicaciones.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Interface extends ComponenteSoftware {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idInterface")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "proveeInterface")
	private Aplicacion aplicaiconProvee;

	/*
	@ManyToOne()
	@JoinColumn(name = "idAplicacion")
	private List<Aplicacion> aplicaiconConsume;
*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aplicacion getAplicaiconProvee() {
		return aplicaiconProvee;
	}

	public void setAplicaiconProvee(Aplicacion aplicaiconProvee) {
		this.aplicaiconProvee = aplicaiconProvee;
	}

	/*public List<Aplicacion> getAplicaiconConsume() {
		return aplicaiconConsume;
	}

	public void setAplicaiconConsume(List<Aplicacion> aplicaiconConsume) {
		this.aplicaiconConsume = aplicaiconConsume;
	}*/

}
