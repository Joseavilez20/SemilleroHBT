package com.hbt.semillero.dto;

import java.io.Serializable;
import com.hbt.semillero.entidad.EstadoEnum;
public class PersonajeDTO implements Serializable{
	
	private static final long  serialVersionUID = 1L;
	
	private Long id;
	private Long idComic;
	private String nombre;
	private EstadoEnum estado;
	private String superPoder;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdComic() {
		return idComic;
	}
	public void setIdComic(Long idComic) {
		this.idComic = idComic;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public EstadoEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}
	public String getSuperPoder() {
		return superPoder;
	}
	public void setSuperPoder(String superPoder) {
		this.superPoder = superPoder;
	}

}
