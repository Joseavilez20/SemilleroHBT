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


import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.interfaz.IGestionarPersonaLocal;

/**
 * <b>Descripción:<b> Servicio Rest para gestionar personas
 * 
 * @author Jose Aviles
 * 
 */
@Path("/GestionarPersona")
public class GestionarPersonaRest {
	
@EJB
private IGestionarPersonaLocal gestionarPersonaEJB;


/**
 * 
 * Metodo encargado de crear un personaje
 * http://localhost:8085/semillero-servicios/rest/GestionarPersona/crear
 * 
 * 
 */

@POST
@Path("/crear")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public ResultadoDTO crearPersona(PersonaDTO personaDTO) {
	ResultadoDTO resultadoDTO = null;
	try {
		gestionarPersonaEJB.crearPersona(personaDTO);
		 resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Persona creada exitosamente");
	}catch (Exception e){
		
		resultadoDTO = new ResultadoDTO(Boolean.TRUE, "error al llamar crearPersona ->"+ e.getMessage());
	}
	
	
	return resultadoDTO;

}

/**
 * 
 * Metodo encargado de traer la informacion de los personajes 
 * http://localhost:8085/semillero-servicios/rest/GestionarPersona/consultarPersonas
 * 
 * 
 */

@GET
@Path("/consultarPersonas")
@Produces(MediaType.APPLICATION_JSON)
public List<PersonaDTO> consultarPersonas() {
	return gestionarPersonaEJB.consultarPersonas();

}

/**
 * 
 * Metodo encargado de traer la informacion de los personajes de un comic
 * http://localhost:8085/semillero-servicios/rest/GestionarPersona/consultarPersonaPorId?idPersona=<idPersona>
 * 
 * 
 */

@GET
@Path("/consultarPersonaPorId")
@Produces(MediaType.APPLICATION_JSON)
public List<PersonaDTO> consultarPersonas(@QueryParam("idPersona") Long idPersona) {
	return gestionarPersonaEJB.consultarPersonas(idPersona);

}

}
