/**
 * GestionarComicBean.java
 */
package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.interfaz.IGestionarComicLocal;

//Import log4j classes.
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.BasicConfigurator;  
/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los comics
 * 
 * @author ccastano
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal {
	// Define a static logger variable so that it references the
    // Logger instance named "MyApp".
    private static final Logger logger = LogManager.getLogger(GestionarComicBean.class);
   
	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * 
	 * @see com.hbt.semillero.interfaz.IGestionarComicLocal#crearComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearComic(ComicDTO comicNuevo) {
		// Entidad nueva
		Comic comic = convertirComicDTOToComic(comicNuevo);
		// Se almacena la informacion y se maneja la enidad comic
		em.persist(comic);
	}

	/**
	 * 
	 * @see com.hbt.semillero.interfaz.IGestionarComicLocal#modificarComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarComic(Long id, String nombre, ComicDTO comicNuevo) {
		Comic comicModificar ;
		if(comicNuevo==null) {
			// Entidad a modificar
			comicModificar = em.find(Comic.class, id);
		}else {
			comicModificar = convertirComicDTOToComic(comicNuevo);
		}
		comicModificar.setNombre(nombre);
		em.merge(comicModificar);
	}

	/**
	 * 
	 * @see com.hbt.semillero.interfaz.IGestionarComicLocal#eliminarComic(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarComic(Long idComic) {
		Comic comicEliminar = em.find(Comic.class, idComic);
		if (comicEliminar != null) {
			em.remove(comicEliminar);
		}
	}

	/**
	 * 
	 * @see com.hbt.semillero.interfaz.IGestionarComicLocal#consultarComic(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ComicDTO consultarComic(String idComic) {
		Comic comic = null;
		comic = new Comic();
		comic = em.find(Comic.class, Long.parseLong(idComic));
		ComicDTO comicDTO = convertirComicToComicDTO(comic);
		return comicDTO;
	}

	/**
	 * 
	 * @see com.hbt.semillero.interfaz.IGestionarComicLocal#consultarComics()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarComics() {
		
		// Set up a simple configuration that logs on the console.
		logger.debug("Metodo consultarComics fue llamado");
		List<ComicDTO> resultadosComicDTO = new ArrayList<ComicDTO>();
		List<Comic> resultados = em.createQuery("select c from Comic c").getResultList();
		for (Comic comic:resultados) {
			resultadosComicDTO.add(convertirComicToComicDTO(comic));
		}
		return resultadosComicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) {
		ComicDTO comicDTO = new ComicDTO();
		if(comic.getId()!=null) {
		 comicDTO.setId(comic.getId().toString());
		}
		comicDTO.setNombre(comic.getNombre());
		comicDTO.setEditorial(comic.getEditorial());
		comicDTO.setTematicaEnum(comic.getTematicaEnum());
		comicDTO.setColeccion(comic.getColeccion());
		comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
		comicDTO.setPrecio(comic.getPrecio());
		comicDTO.setAutores(comic.getAutores());
		comicDTO.setColor(comic.getColor());
		comicDTO.setFechaVenta(comic.getFechaVenta());
		comicDTO.setEstadoEnum(comic.getEstadoEnum());
		comicDTO.setCantidad(comic.getCantidad());
		
		//Atributos para el calculo del total
		comicDTO.setIva(comic.getIva());
		
		return comicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
		Comic comic = new Comic();
		if(comicDTO.getId()!=null) {
			comic.setId(Long.parseLong(comicDTO.getId()));
		}
		comic.setNombre(comicDTO.getNombre());
		comic.setEditorial(comicDTO.getEditorial());
		comic.setTematicaEnum(comicDTO.getTematicaEnum());
		comic.setColeccion(comicDTO.getColeccion());
		comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
		comic.setPrecio(comicDTO.getPrecio());
		comic.setAutores(comicDTO.getAutores());
		comic.setColor(comicDTO.getColor());
		comic.setFechaVenta(comicDTO.getFechaVenta());
		comic.setEstadoEnum(comicDTO.getEstadoEnum());
		comic.setCantidad(comicDTO.getCantidad());
		
		//Atributos para el calculo del total
		comic.setIva(comicDTO.getIva());
		
		return comic;
	}
	/*@Override
	public void calcularTotalIva(){
		logger.debug("Metodo calcularTotalIva fue llamado");
		//List<ComicDTO> resultadosComicDTO = new ArrayList<ComicDTO>();
		List<Comic> resultados = em.createQuery("select c from Comic c").getResultList();
		for (Comic comic:resultados) {
			if(comic.getTematicaEnum().equals("AVENTURAS") || comic.getTematicaEnum().equals("FANTASTICO") || comic.getTematicaEnum().equals("HISTORICO") )
			{
				comic.setIva(5);
				
			}else if(comic.getTematicaEnum().equals("BELICO") || comic.getTematicaEnum().equals("CIENCIA_FICCION") || comic.getTematicaEnum().equals("HORROR"))
			{
				comic.setIva(16);
			}else {
				comic.setIva(10);
			}
			
			
			
		}
		
		
		
	}*/
}
