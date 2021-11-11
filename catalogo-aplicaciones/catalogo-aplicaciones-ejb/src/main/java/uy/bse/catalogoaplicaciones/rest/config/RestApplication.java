package uy.bse.catalogoaplicaciones.rest.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Definimos el "root" path para los servicios REST
 * @author juan
 *
 */
@ApplicationPath("rest")
public class RestApplication extends Application {
}