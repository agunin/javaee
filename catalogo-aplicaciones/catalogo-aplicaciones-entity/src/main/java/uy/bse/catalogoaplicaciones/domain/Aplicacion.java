package uy.bse.catalogoaplicaciones.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


@Entity
public class Aplicacion extends ComponenteSoftware {

	private static final long serialVersionUID = 1L;

	@Column(name = "aplicacionLenguaje", nullable = false)
	@Enumerated(value = EnumType.STRING)
	@NotNull
	private AplicacionLenguaje aplicacionLenguaje;
	
	@OneToMany(fetch = FetchType.EAGER) 
	@JoinTable(name = "Aplicacion_PInterfaces")
	private Set<Interface> proveeInterface = new HashSet<Interface>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Aplicacion_CInterfaces")
	private Set<Interface> consumeInterface = new HashSet<Interface>();
	
	
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
	
	public Set<Interface> getProveeInterface() {
		return proveeInterface;
	}

	public void addProveeInterface(Interface p) {
		this.proveeInterface.add(p);
	}
	
	public Set<Interface> getConsumeInterface() {
		return consumeInterface;
	}

	public void addConsumeInterface(Interface c) {
		this.consumeInterface.add(c);
	}

	public void setProveeInterface(Set<Interface> proveeInterface) {
		this.proveeInterface = proveeInterface;
	}

	public void setConsumeInterface(Set<Interface> consumeInterface) {
		this.consumeInterface = consumeInterface;
	}

}
