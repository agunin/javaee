package uy.bse.catalogoaplicaciones.rest.services;


import java.net.URI;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
		
		List<ComponenteSoftware>componentesSoftware = usuarioService.getComponenteSofwareByUserCi(ci,RolTipo.DESARROLLADOR);
		return Response.ok(componentesSoftware).build();
	}

	@Override
	public Response getInterfacesByAplicacionIdentificador(String identificador) {
		List<Interface>interfacesProvistas = aplicacionService.getInterfacesProvistasByAplicacionIdentificador(identificador);
		return Response.ok(interfacesProvistas).build();
	}

	
	@Override
	public Response createUsuario(Usuario usuario) {
		//Si no se recibe factura se lanza exception con un 400 - Bad Request
		if (usuario == null) {
			throw new BadRequestException();
		}
				
		usuarioService.create(usuario);
		URI usuarioUri = uriInfo.getAbsolutePathBuilder().path(usuario.getDocumentoIdentidad()).build();
		return Response.created(usuarioUri).build();
		
	}

	 
}
