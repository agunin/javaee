package uy.bse.catalogoaplicaciones.rest.services;

import java.net.URI;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import uy.bse.catalogoaplicaciones.domain.Aplicacion;
import uy.bse.catalogoaplicaciones.domain.AplicacionLenguaje;
import uy.bse.catalogoaplicaciones.domain.ComponenteSoftware;
import uy.bse.catalogoaplicaciones.domain.Interface;
import uy.bse.catalogoaplicaciones.domain.RolTipo;
import uy.bse.catalogoaplicaciones.domain.Usuario;
import uy.bse.catalogoaplicaciones.ejbs.AplicacionService;
import uy.bse.catalogoaplicaciones.ejbs.UsuarioService;
import uy.bse.catalogoaplicaciones.exception.CiDuplicadaException;
import uy.bse.catalogoaplicaciones.exception.EmailDuplicadoException;

@Stateless
@LocalBean
//@WebsService //endpointinterface path al isoap, portname 
public class CatalogoService implements ICatalogoRest {

	@Inject
	private UsuarioService usuarioService;
	@Inject
	private AplicacionService aplicacionService;

	@Context
	UriInfo uriInfo;

	@Override
	public Response getComponenteSofwareByUserCi(String ci) {
		try {
			List<ComponenteSoftware> componentesSoftware = usuarioService.getComponenteSofwareByUserCi(ci,
					RolTipo.DESARROLLADOR);
			if (componentesSoftware == null  || componentesSoftware.size() == 0) {
				return Response.status(Response.Status.NOT_FOUND)
						.entity("No se obtuvo resultado para los datos ingresados").build();
			}
			return Response.ok(componentesSoftware).build();

		} catch (Exception e) {

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

		}
	}

	@Override
	public Response getInterfacesByAplicacionIdentificador(String identificador) {
		try {
			List<Interface> interfacesProvistas = aplicacionService
					.getInterfacesProvistasByAplicacionIdentificador(identificador);
			if (interfacesProvistas == null  || interfacesProvistas.size() == 0) {
				return Response.status(Response.Status.NOT_FOUND)
						.entity("No se obtuvo resultado para los datos ingresados").build();
			}
			return Response.ok(interfacesProvistas).build();

		} catch (Exception e) {

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

		}

	}

	@Override
	public Response createUsuario(Usuario usuario) {
		// Si no se recibe factura se lanza exception con un 400 - Bad Request
		if (usuario == null) {
			throw new BadRequestException();
		}

		try {

			usuarioService.actualizar(usuario);
			URI usuarioUri = uriInfo.getAbsolutePathBuilder().path(usuario.getDocumentoIdentidad()).build();
			return Response.created(usuarioUri).build();

		} catch (CiDuplicadaException e) {

			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Documento duplicado: " + usuario.getDocumentoIdentidad()).build();

		} catch (EmailDuplicadoException e) {

			return Response.status(Response.Status.BAD_REQUEST).entity("Email duplicado: " + usuario.getEmail())
					.build();

		} catch (Exception e) {

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

		}

	}
}
