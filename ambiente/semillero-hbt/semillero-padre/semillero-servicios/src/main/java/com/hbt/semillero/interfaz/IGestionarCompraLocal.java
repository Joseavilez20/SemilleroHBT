package com.hbt.semillero.interfaz;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.CompraDTO;

@Local
public interface IGestionarCompraLocal {
	/*
	 * Metodo para la creación de una nueva compra
	 */
	public void crearCompra(CompraDTO CompraDTO);
	
	/*
	 * Metodo para la actualización de una compra
	 */
	public void actualizarCompra();
	
	/*
	 * Metodo para la eliminación de una Compra
	 */
	public void eliminarCompra(Long idCompra);
	
	/*
	 * Metodo para la consulta de una Compra
	 */
	public List<CompraDTO> consultarCompras();
	
	/*
	 * Metodo para la consulta de todos las Compras
	 */
	public List<CompraDTO> consultarCompras(Long idCompra);
	
}
