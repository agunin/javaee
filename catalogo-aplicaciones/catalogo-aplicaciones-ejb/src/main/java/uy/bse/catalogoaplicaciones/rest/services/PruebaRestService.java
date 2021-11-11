//package uy.bse.catalogoaplicaciones.rest.services;
//
//import java.util.Date;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import uy.org.curso.rest.modelo.Persona;
//
//
//@Path("/prueba")
//public class PruebaRestService {
//	
//	/**
//	 * Los metodos pruebaTextoPlano y pruebaHtml estan anotados ambos con GET
//	 * como se decide cual se invoca desde el cliente?
//	 * Se utiliza el header Accept
//	 * Ej: Accept: text/plain
//	 * 
//	 * Tambien se pueden definir varios mime types en el header:
//	 * Ej: Accept: text/plain, text/html, application/xml
//	 * 
//	 * En este caso podemos utilizar el quality factor para dar preferencia
//	 * Ej: Accept: text/plain; q=0.8, text/html
//	 * 
//	 * En este ejemplo se le da preferencia de un 80% a la representacion en html
//	 * 
//	 * mas info @link{https://restfulapi.net/q-parameter-in-http-accept-header/}
//	 */
//	
//	
//	/**
//	 * Si el header Accept: text/plain se ejecuta este metodo
//	 * @return
//	 */
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public String pruebaTextoPlano() {
//		return "Hola mundo cruel texto plano";
//	}
//	
//	/**
//	 * Si el header Accept: text/html
//	 * @return
//	 */
//	@GET
//	@Produces(MediaType.TEXT_HTML)
//	public String pruebaHtml() {
//		return "<html><body><h1>Hola mundo cruel texto plano</h1></body></html>";
//	}	
//	
//	/*-----------------------------------------------------------------------------------------*/
//	
//	
//	/**
//	 * Prueba de diferentes return types
//	 *  /string retorna un objeto del tipo String
//	 */
//	
//	
//	@GET
//	@Path("string")
//	public String getPersonaAsString() {
//		return new Persona("Juan", "Larrayoz", new Date()).toString();
//	}
//	
//	@GET
//	@Path("long")
//	public Long getPersonaAsLong() {
//		return 123456L;
//	}	
//
//	@GET
//	@Path("xml")
//	@Produces(MediaType.APPLICATION_XML)
//	public Persona getPersonaAsXml() {
//		return new Persona("Juan", "Larrayoz", new Date());
//	}
//	
//	@GET
//	@Path("json")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getPersonaAsJson() {
//		return Response.ok(new Persona("Juan", "Larrayoz", new Date())).build();
//	}
//	
//	@GET
//	@Path("json2")
//	public Response getPersonaAsJson2() {
//		return Response.ok(new Persona("Juan", "Larrayoz", new Date()), MediaType.APPLICATION_JSON).build();
//	}	
//	
//	
//}
