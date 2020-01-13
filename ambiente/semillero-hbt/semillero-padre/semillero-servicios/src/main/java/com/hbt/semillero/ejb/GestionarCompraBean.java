package com.hbt.semillero.ejb;

import com.hbt.semillero.dto.CompraDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Compra;
import com.hbt.semillero.entidad.Persona;
import com.hbt.semillero.interfaz.IGestionarCompraLocal;

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
public class GestionarCompraBean implements IGestionarCompraLocal{

	private static final Logger logger = Logger.getLogger(GestionarCompraBean.class);
	
	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	//@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearCompra(CompraDTO compraDTO) {
		
		logger.debug("inicia  crearCompraje()");
		Compra compra  = convertirCompraDTOToCompra(compraDTO);
		entityManager.persist(compra);
		logger.debug("finaliza  crearCompraje()");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarCompra() {
		
		logger.debug("inicia ");
		logger.debug("finaliza ");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarCompra(Long idCompra) {
		
		logger.debug("inicia ");
		logger.debug("finaliza ");
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CompraDTO> consultarCompras() {
		
		logger.debug("inicia metodo consultarComprajes");
		
		String query = "SELECT compra FROM Compra compra";
		List<Compra> listaCompras = entityManager.createQuery(query).getResultList();
		List<CompraDTO> listaComprasDTO = new ArrayList<>();
		
		for(Compra compra: listaCompras){
			listaComprasDTO.add(convertirCompraToCompraDTO(compra));
		}
		logger.debug("finaliza ");
		return listaComprasDTO;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CompraDTO> consultarCompras(Long idCompra) {
		
		logger.debug("inicia consultarCompras con parametro ");
		String query = "SELECT compra "+"FROM Compra compra "+ "WHERE compra.id= :idCompra";
		List<Compra> listaCompras = entityManager.createQuery(query).setParameter("idCompra", idCompra).getResultList();
		List<CompraDTO> listaComprasDTO  = new ArrayList<>();
		
		for(Compra compra: listaCompras){
			listaComprasDTO.add(convertirCompraToCompraDTO(compra));
		}
		
		logger.debug("finaliza ");
		
		return listaComprasDTO;
	}
	
	/**
	 * 
	 * Metodo encargado de transformar una compra a una compraDTO
	 * 
	 * @param compra
	 * @return compraDTO
	 */
	private CompraDTO convertirCompraToCompraDTO(Compra compra){
		CompraDTO compraDTO = new CompraDTO();
		if(compra.getId()!= null){
			compraDTO.setId(compra.getId());
		}
		
		compraDTO.setIdComic(compra.getComic().getId());
		compraDTO.setIdPersona(compra.getPersona().getId());
		
		compraDTO.setFechaCompra(compra.getFechaCompra());
		
		return compraDTO;
	}
	
	/**
	 * 
	 * Metodo encargado de transformar un compraDTO a una compra
	 * 
	 * @param compraDTO
	 * @return compra
	 */
	private Compra convertirCompraDTOToCompra(CompraDTO compraDTO){
		Compra compra = new Compra();
		if(compraDTO.getId()!= null){
			compra.setId(compraDTO.getId());
		}
		compra.setComic(new Comic());
		compra.getComic().setId(compraDTO.getIdComic());
		compra.setPersona(new Persona());
		compra.getPersona().setId(compraDTO.getIdPersona());
		compra.setFechaCompra(compraDTO.getFechaCompra());
		
		return compra;
	}

	
	
	
}
