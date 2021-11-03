package uy.bse.catalogoaplicaciones.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Aplicacion extends ComponenteSoftware {

	private static final long serialVersionUID = 1L;

	/*
	 * @Id
	 * 
	 * @Column(name = "idAplicacion")
	 * 
	 * @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
	 */

	@Column(name = "aplicacionLenguaje", nullable = false)
	@Enumerated(value = EnumType.STRING)
	@NotNull
	private AplicacionLenguaje aplicacionLenguaje;

	//@OneToMany(mappedBy = "aplicaiconProvee", cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany // 1 - N (Aplicacion --> Interface) - provee
	private List<Interface> proveeInterface;

	/*@OneToMany(mappedBy = "idInterface", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Interface> consumeInterface;*/
	
	@OneToMany // 1 - N (Aplicacion --> Interface) - consume
	private List<Interface> consumeInterface;
	
	
	public Aplicacion() {
		super();
	}

	public Aplicacion(String identificador,String descripcion,AplicacionLenguaje aplicacionLenguaje, List<Interface> proveeInterface,
			List<Interface> consumeInterface) {
		super();
		this.setIdentificador(identificador);
		this.setDescripcion(descripcion);
		this.aplicacionLenguaje = aplicacionLenguaje;
		this.proveeInterface = proveeInterface;
		this.consumeInterface = consumeInterface;
	}

	/*
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 */
	
	public AplicacionLenguaje getAplicacionLenguaje() {
		return aplicacionLenguaje;
	}

	public void setAplicacionLenguaje(AplicacionLenguaje aplicacionLenguaje) {
		this.aplicacionLenguaje = aplicacionLenguaje;
	}
	
	public List<Interface> getProveeInterface() {
		return proveeInterface;
	}

	public void setProveeInterface(List<Interface> proveeInterface) {
		this.proveeInterface = proveeInterface;
	}
	
	public List<Interface> getConsumeInterface() {
		return consumeInterface;
	}

	public void setConsumeInterface(List<Interface> consumeInterface) {
		this.consumeInterface = consumeInterface;
	}

}
