package com.hbt.semillero.ejb;

import com.hbt.semillero.dto.PersonaDTO;

import com.hbt.semillero.entidad.Persona;
import com.hbt.semillero.interfaz.IGestionarPersonaLocal;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonaBean implements IGestionarPersonaLocal{

	private static final Logger logger = Logger.getLogger(GestionarPersonaBean.class);
	
	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	//@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersona(PersonaDTO personaDTO) {
		
		logger.debug("inicia  crearPersonaje()");
		Persona persona  = convertirPersonaDTOToPersona(personaDTO);
		entityManager.persist(persona);
		logger.debug("finaliza  crearPersonaje()");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarPersona() {
		
		logger.debug("inicia ");
		logger.debug("finaliza ");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPersona(Long idPersona) {
		
		logger.debug("inicia ");
		logger.debug("finaliza ");
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonaDTO> consultarPersonas() {
		
		logger.debug("inicia metodo consultarPersonajes");
		
		String query = "SELECT persona FROM Persona persona";
		List<Persona> listaPersonas = entityManager.createQuery(query).getResultList();
		List<PersonaDTO> listaPersonasDTO = new ArrayList<>();
		
		for(Persona persona: listaPersonas){
			listaPersonasDTO.add(convertirPersonaToPersonaDTO(persona));
		}
		logger.debug("finaliza ");
		return listaPersonasDTO;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonaDTO> consultarPersonas(Long idPersona) {
		
		logger.debug("inicia consultarPersonas con parametro ");
		String query = "SELECT persona "+"FROM Persona persona "+ "WHERE persona.id= :idPersona";
		List<Persona> listaPersonas = entityManager.createQuery(query).setParameter("idPersona", idPersona).getResultList();
		List<PersonaDTO> listaPersonasDTO  = new ArrayList<>();
		
		for(Persona persona: listaPersonas){
			listaPersonasDTO.add(convertirPersonaToPersonaDTO(persona));
		}
		
		logger.debug("finaliza ");
		
		return listaPersonasDTO;
	}
	
	/**
	 * 
	 * Metodo encargado de transformar una persona a una personaDTO
	 * 
	 * @param persona
	 * @return personaDTO
	 */
	private PersonaDTO convertirPersonaToPersonaDTO(Persona persona){
		PersonaDTO personaDTO = new PersonaDTO();
		if(persona.getId()!= null){
			personaDTO.setId(persona.getId().toString());
		}
		
		personaDTO.setNombre(persona.getNombre());
		personaDTO.setTipoDocumento(persona.getTipoDocumento());
		personaDTO.setNumeroDocumento(persona.getNumeroDocumento());
		personaDTO.setFechaNacimiento(persona.getFechaNacimiento());
		
		return personaDTO;
	}
	
	/**
	 * 
	 * Metodo encargado de transformar un personaDTO a una persona
	 * 
	 * @param personaDTO
	 * @return persona
	 */
	private Persona convertirPersonaDTOToPersona(PersonaDTO personaDTO){
		Persona persona = new Persona();
		if(personaDTO.getId()!= null){
			persona.setId(Long.parseLong(personaDTO.getId()));
		}
		
		persona.setNombre(personaDTO.getNombre());
		persona.setTipoDocumento(personaDTO.getTipoDocumento());
		persona.setNumeroDocumento(personaDTO.getNumeroDocumento());
		
		persona.setFechaNacimiento(personaDTO.getFechaNacimiento());
		
		return persona;
	}

	
	
	
}
