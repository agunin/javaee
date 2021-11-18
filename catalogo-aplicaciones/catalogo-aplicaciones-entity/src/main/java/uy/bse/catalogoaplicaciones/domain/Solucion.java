package uy.bse.catalogoaplicaciones.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Solucion extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idSolucion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique=true)
	private String identificador;

	@Lob
	@NotNull
	@Size(min = 2)
	@Basic(fetch = FetchType.LAZY)
	private String descripcion;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ComponenteSoftware> componentesSoftware;

	public Solucion() {
		super();
		componentesSoftware = new HashSet<ComponenteSoftware>();
	}

	public Solucion(String identificador, String descripcion) {
		super();
		componentesSoftware = new HashSet<ComponenteSoftware>();
		this.identificador = identificador;
		this.descripcion = descripcion;

	}

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

	public Set<ComponenteSoftware> getComponentesSoftware() {
		return componentesSoftware;
	}

	public void setComponentesSoftware(Set<ComponenteSoftware> componentesSoftware) {
		this.componentesSoftware = componentesSoftware;
	}

	public void addComponentesSoftware(ComponenteSoftware componentesSoftware) {
		this.componentesSoftware.add(componentesSoftware);
	}

	
}
