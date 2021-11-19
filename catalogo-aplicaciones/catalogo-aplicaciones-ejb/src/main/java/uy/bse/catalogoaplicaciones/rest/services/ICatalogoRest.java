package uy.bse.catalogoaplicaciones.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uy.bse.catalogoaplicaciones.domain.Usuario;
import uy.bse.catalogoaplicaciones.exception.CiDuplicadaException;
import uy.bse.catalogoaplicaciones.exception.EmailDuplicadoException;




@Path("catalogo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ICatalogoRest {

	@GET
	@Path("/usuario/componentes/{ci}")
	Response getComponenteSofwareByUserCi(@PathParam("ci") String ci);


	@GET
	@Path("/aplicacion/interfacesprovee/{identificador}")
	Response getInterfacesByAplicacionIdentificador(@PathParam("identificador") String identificador);

	
	@POST
	//@Path("/usuario")
	Response createUsuario(Usuario usuario);

	
}