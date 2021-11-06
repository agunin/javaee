package uy.bse.catalogoaplicaciones.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
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
	private String identificador;

	
	@Lob
	@NotNull
	@Size(min = 2)
	@Basic(fetch = FetchType.LAZY)
	private String descripcion;
	
	//@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany // 1 - N (Solucion --> ComponenteSoftware)
	private List<ComponenteSoftware> componentesSoftware;

	public Solucion() {
		super();
		componentesSoftware = new ArrayList<ComponenteSoftware>();
		
	}
	
	

	public Solucion(String identificador, String descripcion) {
		super();
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

	public List<ComponenteSoftware> getComponentesSoftware() {
		return componentesSoftware;
	}

	public void setComponentesSoftware(List<ComponenteSoftware> componentesSoftware) {
		this.componentesSoftware = componentesSoftware;
	}
	
	
	public void addComponentesSoftware(ComponenteSoftware componentesSoftware) {
		this.componentesSoftware.add(componentesSoftware);
	}

}

