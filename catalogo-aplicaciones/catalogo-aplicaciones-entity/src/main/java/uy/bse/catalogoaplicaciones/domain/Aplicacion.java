package uy.bse.catalogoaplicaciones.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Aplicacion extends ComponenteSoftware {

	private static final long serialVersionUID = 1L;

	@Column(name = "aplicacionLenguaje", nullable = false)
	@Enumerated(value = EnumType.STRING)
	@NotNull
	private AplicacionLenguaje aplicacionLenguaje;
	
	@OneToMany // 1 - N (Aplicacion --> Interface) - provee
	@JoinTable(name = "Aplicacione_PInterfaces")
	private List<Interface> proveeInterface = new ArrayList<Interface>();
	
	@OneToMany // 1 - N (Aplicacion --> Interface) - consume
	@JoinTable(name = "Aplicacione_CInterfaces")
	private List<Interface> consumeInterface = new ArrayList<Interface>();;
	
	
	public Aplicacion() {
		super();
	}

	public Aplicacion(String identificador,String descripcion,AplicacionLenguaje aplicacionLenguaje) {
		super();
		this.setIdentificador(identificador);
		this.setDescripcion(descripcion);
		this.setAplicacionLenguaje(aplicacionLenguaje);	
	}
	
	public AplicacionLenguaje getAplicacionLenguaje() {
		return aplicacionLenguaje;
	}

	public void setAplicacionLenguaje(AplicacionLenguaje aplicacionLenguaje) {
		this.aplicacionLenguaje = aplicacionLenguaje;
	}
	
	public List<Interface> getProveeInterface() {
		return proveeInterface;
	}

	public void addProveeInterface(Interface p) {
		this.proveeInterface.add(p);
	}
	
	public List<Interface> getConsumeInterface() {
		return consumeInterface;
	}

	public void addConsumeInterface(Interface c) {
		this.consumeInterface.add(c);
	}

}
