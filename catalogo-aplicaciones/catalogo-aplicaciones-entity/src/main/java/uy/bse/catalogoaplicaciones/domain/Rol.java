package uy.bse.catalogoaplicaciones.domain;

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
	@ManyToOne
	@JoinTable(name="rol_usuario", joinColumns = @JoinColumn(name="fk_usuario"), inverseJoinColumns = @JoinColumn(name="fk_rol"))
	private Usuario usuario;

	
	//ManytoOne a ComponenteSoftware
	@ManyToOne
	@JoinTable(name="rol_csoftware", joinColumns = @JoinColumn(name="fk_csoftware"), inverseJoinColumns = @JoinColumn(name="fk_rol"))
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

}
