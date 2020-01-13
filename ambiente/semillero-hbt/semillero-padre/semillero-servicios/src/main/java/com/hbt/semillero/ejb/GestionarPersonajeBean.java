package com.hbt.semillero.ejb;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.interfaz.IGestionarPersonajeLocal;

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
public class GestionarPersonajeBean implements IGestionarPersonajeLocal{

	private static final Logger logger = Logger.getLogger(GestionarPersonajeBean.class);
	
	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	//@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersonaje(PersonajeDTO personajeDTO) {
		
		logger.debug("inicia  crearPersonaje()");
		Personaje personaje  = convertirPersonajeDTOToPersonaje(personajeDTO);
		entityManager.persist(personaje);
		logger.debug("finaliza  crearPersonaje()");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarPersonaje() {
		
		logger.debug("inicia ");
		logger.debug("finaliza ");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarPersonaje(Long idPersona) {
		
		logger.debug("inicia ");
		logger.debug("finaliza ");
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO> consultarPersonajes() {
		
		logger.debug("inicia metodo consultarPersonajes");
		
		String query = "SELECT personaje FROM Personaje personaje";
		List<Personaje> listaPersonajes = entityManager.createQuery(query).getResultList();
		List<PersonajeDTO> listaPersonajesDTO = new ArrayList<>();
		
		for(Personaje personaje: listaPersonajes){
			listaPersonajesDTO.add(convertirPersonajeToPersonajeDTO(personaje));
		}
		logger.debug("finaliza ");
		return listaPersonajesDTO;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO> consultarPersonajes(Long idComic) {
		
		logger.debug("inicia consultarPersonajes con parametro ");
		String query = "SELECT personaje "+"FROM Personaje personaje "+ "WHERE personaje.comic.id= :idComic";
		List<Personaje> listaPersonajes = entityManager.createQuery(query).setParameter("idComic", idComic).getResultList();
		List<PersonajeDTO> listaPersonajesDTO  = new ArrayList<>();
		
		for(Personaje personaje: listaPersonajes){
			listaPersonajesDTO.add(convertirPersonajeToPersonajeDTO(personaje));
		}
		
		logger.debug("finaliza ");
		
		return listaPersonajesDTO;
	}
	
	/**
	 * 
	 * Metodo encargado de transformar un personaje a un personajeDTO
	 * 
	 * @param personaje
	 * @return personajeDTO
	 */
	private PersonajeDTO convertirPersonajeToPersonajeDTO(Personaje personaje){
		PersonajeDTO personajeDTO = new PersonajeDTO();
		if(personaje.getId()!= null){
			personajeDTO.setId(personaje.getId());
		}
		
		personajeDTO.setNombre(personaje.getNombre());
		personajeDTO.setEstado(personaje.getEstado());
		personajeDTO.setSuperPoder(personaje.getSuperpoder());
		personajeDTO.setIdComic(personaje.getComic().getId());
		
		return personajeDTO;
	}
	
	/**
	 * 
	 * Metodo encargado de transformar un personajeDTO a un personaje
	 * 
	 * @param personajeDTO
	 * @return personaje
	 */
	private Personaje convertirPersonajeDTOToPersonaje(PersonajeDTO personajeDTO){
		Personaje personaje = new Personaje();
		if(personajeDTO.getId()!= null){
			personaje.setId(personajeDTO.getId());
		}
		
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setEstado(personajeDTO.getEstado());
		personaje.setSuperpoder(personajeDTO.getSuperPoder());
		personaje.setComic(new Comic()); //al utilizar @ManyToOne(fetch = FetchType.LAZY) en el atributo comic de la entidad
		personaje.getComic().setId(personajeDTO.getIdComic());
		
		return personaje;
	}
	
	
	
}
