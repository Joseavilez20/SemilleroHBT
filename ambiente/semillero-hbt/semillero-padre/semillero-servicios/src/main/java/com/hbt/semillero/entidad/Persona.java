package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "PERSONA")
public class Persona implements Serializable{
	
	private static final long serialVersionUID = 1l;
	
	
	private Long id;
	private String nombre;
	private TipoDocumentoEnum tipoDocumento;
	private String numeroDocumento;
	
	
	
	private LocalDate fechaNacimiento;
	

	/**
	 * Constructor de la clase.
	 */
	public Persona() {

	}
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERSONA_ID_GENERATOR", sequenceName = "SEC_PERSONA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONA_ID_GENERATOR")
	@Column(name = "PER_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PER_NOMBRE")
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "PER_TIPODOCUMENTO")
	@Enumerated(value = EnumType.STRING)
	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Column(name = "PER_NUMERODOCUMENTO")
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	
	//The @JsonFormat annotations not worked for me as I wanted (it has adjusted the timezone to different value)
	//during deserialization (the serialization worked perfect):
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	
	//You need to use custom serializer and custom deserializer instead of the @JsonFormat annotation
	//if you want predicted result.
	//other example:
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = TimeZone.getDefault(), locale = Locale.getDefault())
	//Note that you may need to pass in a different Locale and TimeZone if they should be based on 
	//something other than what the server uses.
	
	//@JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(name = "PER_FECHANACIMIENTO")
	@JsonDeserialize(using =  LocalDateDeserializer.class)
    //@JsonSerialize(using = CustomDateSerializer.class)
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	
	
}
