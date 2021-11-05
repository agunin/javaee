package uy.bse.catalogoaplicaciones.domain;

import javax.persistence.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	//ManytoOne a ComponenteSoftware

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
