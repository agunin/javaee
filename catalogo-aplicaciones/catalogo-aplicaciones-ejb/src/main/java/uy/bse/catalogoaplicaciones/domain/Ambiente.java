package uy.bse.catalogoaplicaciones.domain;




//@Entity
public class Ambiente {
	
	//@Id
	//@GeneratedValue
	private Integer id;
	
	//@Enumerated
	private AmbienteTipo tipo;
	
	private Integer puerto;
	
	private String url;
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the tipo
	 */
	public AmbienteTipo getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(AmbienteTipo tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the puerto
	 */
	public Integer getPuerto() {
		return puerto;
	}

	/**
	 * @param puerto the puerto to set
	 */
	public void setPuerto(Integer puerto) {
		this.puerto = puerto;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public Ambiente() {
		super();
	}

	
	
	
	

	

}
