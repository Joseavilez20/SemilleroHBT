package com.hbt.semillero.interfaz;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.PersonajeDTO;

@Local
public interface IGestionarPersonajeLocal {
	/*
	 * Metodo para la creación de un nuevo personaje
	 */
	public void crearPersonaje(PersonajeDTO personaDTO);
	
	/*
	 * Metodo para la actualización de un personaje
	 */
	public void actualizarPersonaje();
	
	/*
	 * Metodo para la eliminación de un personaje
	 */
	public void eliminarPersonaje(Long idPersona);
	
	/*
	 * Metodo para la consulta de un personaje
	 */
	public List<PersonajeDTO> consultarPersonajes();
	
	/*
	 * Metodo para la consulta de todos los personajes
	 */
	public List<PersonajeDTO> consultarPersonajes(Long idComic);
	
}
