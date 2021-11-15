package uy.bse.catalogoaplicaciones.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
//@Table(name = "Componente_Software") por defecto le pone de nombre ComponenteSoftware
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ComponenteSoftware extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@NotNull
	private String identificador;

	@Lob
	@NotNull
	@Size(min = 4)
	@Basic(fetch = FetchType.LAZY)
	private String descripcion;
	
	@ManyToMany(mappedBy = "componentesSoftware")
	private Set<Rol> roles = new HashSet<Rol>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	public Set<Rol> getRoles() {
		return roles;
	}

	
	public void addRoles(Rol rol) {
		this.roles.add(rol);
	}

}
