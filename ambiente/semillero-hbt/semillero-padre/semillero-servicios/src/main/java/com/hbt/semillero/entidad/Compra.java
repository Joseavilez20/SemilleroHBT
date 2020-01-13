package com.hbt.semillero.entidad;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
@Entity
@Table(name = "COMPRA")
public class Compra {
	
	private Long id;
	private Comic comic;
	private Persona persona;
	private LocalDateTime fechaCompra;
	
	public Compra(){
		//constructor 
	}
	

	/**
	 * Metodo encargado de retornar el valor del atributo id
	 * 
	 * @return El id asociado a la clase
	 */
	@Id
	@SequenceGenerator(allocationSize = 1, name = "COMPRA_SCID_GENERATOR", sequenceName = "SEQ_COMPRA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPRA_SCID_GENERATOR")
	@Column(name = "COM_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "COM_ID_COMIC")
	public Comic getComic() {
		return comic;
	}
	public void setComic(Comic comic) {
		this.comic = comic;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "COM_ID_PERSONA")
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	@Column(name = "COM_FECHA_COMPRA")
	//@JsonDeserialize(using =  LocalDateDeserializer.class)
	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(LocalDateTime fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
}
