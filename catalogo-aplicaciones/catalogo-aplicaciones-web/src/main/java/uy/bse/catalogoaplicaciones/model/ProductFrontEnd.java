package uy.bse.catalogoaplicaciones.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.primefaces.model.TreeNode;

import uy.bse.catalogoaplicaciones.domain.ComponenteSoftware;
import uy.bse.catalogoaplicaciones.domain.Rol;

public class ProductFrontEnd implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String tipoComponente;
	private String identificador;
	private String descripcion;

	private List<ComponenteSoftware> componentes;
	private List<Rol> roles;

	private TreeNode root3;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductFrontEnd p = (ProductFrontEnd) o;
		return id == p.id && tipoComponente == p.tipoComponente && identificador == p.identificador
				&& Objects.equals(componentes, p.componentes) && Objects.equals(root3, p.root3);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tipoComponente, identificador, componentes, root3);
	}

	public ProductFrontEnd() {
		componentes = new ArrayList<ComponenteSoftware>();
	}

	public ProductFrontEnd(Long id, String tipoComponente, String identificador, String descripcion) {
		this.id = id;
		this.tipoComponente = tipoComponente;
		this.identificador = identificador;
		this.descripcion = descripcion;
		this.componentes = new ArrayList<ComponenteSoftware>();
	}

	@Override
	public ProductFrontEnd clone() {
		return new ProductFrontEnd(getId(), getTipoComponente(), getIdentificador(), getDescripcion());
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

	public TreeNode getRoot3() {
		return root3;
	}

	public void setRoot3(TreeNode root3) {
		this.root3 = root3;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

}
