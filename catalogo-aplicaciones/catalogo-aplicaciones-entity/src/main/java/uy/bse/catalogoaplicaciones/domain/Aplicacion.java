package uy.bse.catalogoaplicaciones.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Aplicacion extends ComponenteSoftware {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idAplicacion")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "aplicaiconProvee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Interface> proveeInterface;

	/*@OneToMany(mappedBy = "idInterface", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Interface> consumeInterface;*/

	public Aplicacion() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Interface> getProveeInterface() {
		return proveeInterface;
	}

	public void setProveeInterface(List<Interface> proveeInterface) {
		this.proveeInterface = proveeInterface;
	}
/*
	public List<Interface> getConsumeInterface() {
		return consumeInterface;
	}

	public void setConsumeInterface(List<Interface> consumeInterface) {
		this.consumeInterface = consumeInterface;
	}
*/
}
