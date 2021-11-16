package uy.bse.catalogoaplicaciones.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Rol extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idRol")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "rolTipo", nullable = false)
	@Enumerated(value = EnumType.STRING)
	@NotNull
	private RolTipo rolTipo;
	
	
	//ManytoOne a Usuario
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fk_usuario")
	private Usuario usuario;

	//ManytoOne a ComponenteSoftware
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fk_csoftware")
	private ComponenteSoftware componenteSoftware;
	
	
	
	public Rol() {
		super();
	}
	
	

	public Rol(RolTipo rolTipo) {
		super();
		this.rolTipo = rolTipo;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RolTipo getRolTipo() {
		return rolTipo;
	}

	public void setRolTipo(RolTipo rolTipo) {
		this.rolTipo = rolTipo;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public ComponenteSoftware getComponenteSoftware() {
		return componenteSoftware;
	}

	public void setComponenteSoftware(ComponenteSoftware componenteSoftware) {
		this.componenteSoftware = componenteSoftware;
		
	}

}
