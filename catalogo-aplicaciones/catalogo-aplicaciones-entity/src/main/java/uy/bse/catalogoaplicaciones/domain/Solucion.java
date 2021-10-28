package uy.bse.catalogoaplicaciones.domain;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Solucion extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idSolucion")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String identificador;

	@Lob
	@NotNull
	@Size(min = 60)
	@Basic(fetch = FetchType.LAZY)
	private String descripcion;
	
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ComponenteSoftware> componentesSoftware;

	public Solucion() {
		super();
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

	public List<ComponenteSoftware> getComponentesSoftware() {
		return componentesSoftware;
	}

	public void setComponentesSoftware(List<ComponenteSoftware> componentesSoftware) {
		this.componentesSoftware = componentesSoftware;
	}

}
