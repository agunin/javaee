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
	
	
	//ManytoMany a Usuario
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="rol_usuario",
	joinColumns = @JoinColumn(name="fk_usuario"),
	inverseJoinColumns = @JoinColumn(name="fk_rol"))
	private Set<Usuario> usuarios = new HashSet<Usuario>();

	//ManytoMany a ComponenteSoftware
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="rol_csoftware",
	joinColumns = @JoinColumn(name="fk_csoftware"),
	inverseJoinColumns = @JoinColumn(name="fk_rol"))
	private Set<ComponenteSoftware> componentesSoftware = new HashSet<ComponenteSoftware>();
	
	
	
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
	

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}


	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
		//usuario.addRoles(this);
	}


	public Set<ComponenteSoftware> getComponentesSoftware() {
		return componentesSoftware;
	}

	public void addComponenteSoftware(ComponenteSoftware componenteSoftware) {
		this.componentesSoftware.add(componenteSoftware);
		//componenteSoftware.addRoles(this);
	}

}
