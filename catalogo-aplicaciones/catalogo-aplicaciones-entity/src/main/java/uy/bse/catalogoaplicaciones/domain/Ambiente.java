package uy.bse.catalogoaplicaciones.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Ambiente extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idAmbiente")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ambienteTipo", nullable = false)
	@Enumerated(value = EnumType.STRING)
	@NotNull
	private AmbienteTipo ambienteTipo;

	@NotNull
	private Integer puerto;

	@NotNull
	private String url;

	public Ambiente() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AmbienteTipo getAmbienteTipo() {
		return ambienteTipo;
	}

	public void setAmbienteTipo(AmbienteTipo ambienteTipo) {
		this.ambienteTipo = ambienteTipo;
	}

	public Integer getPuerto() {
		return puerto;
	}

	public void setPuerto(Integer puerto) {
		this.puerto = puerto;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
