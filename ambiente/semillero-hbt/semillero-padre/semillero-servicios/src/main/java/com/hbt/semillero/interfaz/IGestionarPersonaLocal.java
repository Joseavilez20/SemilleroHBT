package com.hbt.semillero.interfaz;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.PersonaDTO;

@Local
public interface IGestionarPersonaLocal {
	/*
	 * Metodo para la creación de una nueva persona
	 */
	public void crearPersona(PersonaDTO personaDTO);
	
	/*
	 * Metodo para la actualización de una persona
	 */
	public void actualizarPersona();
	
	/*
	 * Metodo para la eliminación de una persona
	 */
	public void eliminarPersona(Long idPersona);
	
	/*
	 * Metodo para la consulta de una persona
	 */
	public List<PersonaDTO> consultarPersonas();
	
	/*
	 * Metodo para la consulta de todos las personas
	 */
	public List<PersonaDTO> consultarPersonas(Long idPersona);
	
}
