package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.interfaz.IGestionarPersonajeLocal;

/**
 * <b>Descripción:<b> Servicio Rest para gestionar personajes
 * 
 * @author Jose Aviles
 * 
 */
@Path("/GestionarPersonaje")
public class GestionarPersonajeRest {
	
@EJB
private IGestionarPersonajeLocal gestionarPersonajeEJB;


/**
 * 
 * Metodo encargado de crear un personaje
 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/crear
 * 
 * 
 */

@POST
@Path("/crear")
//@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public void crearPersonaje(PersonajeDTO personajeDTO) {
	gestionarPersonajeEJB.crearPersonaje(personajeDTO);

}

/**
 * 
 * Metodo encargado de traer la informacion de los personajes 
 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/consultarPersonajes
 * 
 * 
 */

@GET
@Path("/consultarPersonajes")
@Produces(MediaType.APPLICATION_JSON)
public List<PersonajeDTO> consultarPersonajes() {
	return gestionarPersonajeEJB.consultarPersonajes();

}

/**
 * 
 * Metodo encargado de traer la informacion de los personajes de un comic
 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/consultarPersonajesPorId?idComic=<idComic>
 * 
 * 
 */

@GET
@Path("/consultarPersonajesPorId")
@Produces(MediaType.APPLICATION_JSON)
public List<PersonajeDTO> consultarPersonajes(@QueryParam("idComic") Long idComic) {
	return gestionarPersonajeEJB.consultarPersonajes(idComic);

}

}
