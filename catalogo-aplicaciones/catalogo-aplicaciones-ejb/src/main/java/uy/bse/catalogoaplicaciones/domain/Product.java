package uy.bse.catalogoaplicaciones.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String tipoComponente;
	private String identificador;
	private String descripcion;

	private List<ComponenteSoftware> componentes;
	private List<Rol> roles;

	public Product() {
		componentes = new ArrayList<ComponenteSoftware>();
	}

	public Product(Long id, String tipoComponente, String identificador, String descripcion) {
		this.id = id;
		this.tipoComponente = tipoComponente;
		this.identificador = identificador;
		this.descripcion = descripcion;
		this.componentes = new ArrayList<ComponenteSoftware>();
	}

	@Override
	public Product clone() {
		return new Product(getId(), getTipoComponente(), getIdentificador(), getDescripcion());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoComponente() {
		return tipoComponente;
	}

	public void setTipoComponente(String tipoComponente) {
		this.tipoComponente = tipoComponente;
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

	public List<ComponenteSoftware> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<ComponenteSoftware> componentes) {
		this.componentes = componentes;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

}
