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


import com.hbt.semillero.dto.CompraDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.interfaz.IGestionarCompraLocal;

/**
 * <b>Descripción:<b> Servicio Rest para gestionar compras
 * 
 * @author Jose Aviles
 * 
 */
@Path("/GestionarCompra")
public class GestionarCompraRest {
	
@EJB
private IGestionarCompraLocal gestionarCompraEJB;


/**
 * 
 * Metodo encargado de crear un compraje
 * http://localhost:8085/semillero-servicios/rest/GestionarCompra/crear
 * 
 * 
 */

@POST
@Path("/crear")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public ResultadoDTO crearCompra(CompraDTO compraDTO) {
	ResultadoDTO resultadoDTO = null;
	try {
		gestionarCompraEJB.crearCompra(compraDTO);
		 resultadoDTO = new ResultadoDTO(Boolean.TRUE, "Compra creada exitosamente");
	}catch (Exception e){
		
		resultadoDTO = new ResultadoDTO(Boolean.TRUE, "error al llamar crearCompra ->"+ e.getMessage());
	}
	
	
	return resultadoDTO;

}

/**
 * 
 * Metodo encargado de traer la informacion de los comprajes 
 * http://localhost:8085/semillero-servicios/rest/GestionarCompra/consultarCompras
 * 
 * 
 */

@GET
@Path("/consultarCompras")
@Produces(MediaType.APPLICATION_JSON)
public List<CompraDTO> consultarCompras() {
	return gestionarCompraEJB.consultarCompras();

}

/**
 * 
 * Metodo encargado de traer la informacion de una compra
 * http://localhost:8085/semillero-servicios/rest/GestionarCompra/consultarCompra?idCompra=<idCompra>
 * 
 * 
 */

@GET
@Path("/consultarCompra")
@Produces(MediaType.APPLICATION_JSON)
public List<CompraDTO> consultarCompras(@QueryParam("idCompra") Long idCompra) {
	return gestionarCompraEJB.consultarCompras(idCompra);

}

}
