import { Component, OnInit } from '@angular/core';
import { PersonaDTO } from '../../dto/persona.dto';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {Router} from '@angular/router';
import {GestionarPersonaService} from '../../services/gestionar-persona.service'

/**
 * @description La clase CrearPersonaComponent permite crear personas
 * @author Jose Aviles Pacheco
 */
@Component({
    selector: 'gestionar-persona',
    templateUrl: './gestionar-persona.html',
    styleUrls: ['./gestionar-persona.css']
})
export class GestionarPersonaComponent implements OnInit{
    
    
   /**
     * Atributo que contiene los controles del formulario
     */
    public gestionarPersonaForm : FormGroup;
   
/**
     * Atributo que contendra la informacion de la persona
     */
    public persona: PersonaDTO;

      /**
     * Atributo que contendra la lista de Personas creadas
     */
    public listaPersonas : Array<PersonaDTO>;


    /**
     * Atributo que indica si se envio a validar el formulario
     */
    public submitted : boolean;

    public mostrarMensaje : boolean;

    public mensajeConsulta : string;

    
    /**
     * @description Este es el constructor del componente GestionarPersonaComponent
     * @author Jose Aviles
     */
    constructor(private fb : FormBuilder,
        private router : Router,
        private gestionarPersonaService: GestionarPersonaService) {
        this.gestionarPersonaForm = this.fb.group({
            nombre : [null, Validators.required],
            tipoDocumento : [null],
            numeroDocumento : [null],
            fechaNacimiento : [null]
            
        });
    }

    ngOnInit() : void {
        
        this.persona = new PersonaDTO();
        this.listaPersonas = new Array<PersonaDTO>();
        this.consultarPersonas();
        

    }

    private consultarPersonas() : void{
        this.gestionarPersonaService.consultarPersonas().subscribe(respuesta => {
            console.log('respuesta-->',respuesta);
            this.listaPersonas = respuesta;
          },err => {
              console.log('error consultarPersonas');
          }
          
          );
    }

    /**
     * @description Metodo que permite validar el formulario y crear o actualizar una persona
     */
    public crearActualizarPersona() : void {
        this.submitted = true;
        console.log('se llamo metodo crearPersona()');
        if(this.gestionarPersonaForm.invalid) {
            return;
        }
        
        this.persona = new PersonaDTO();
        this.persona.nombre = this.gestionarPersonaForm.controls.nombre.value;
        this.persona.tipoDocumento = this.gestionarPersonaForm.controls.tipoDocumento.value;
        this.persona.numeroDocumento = this.gestionarPersonaForm.controls.numeroDocumento.value;
        this.persona.fechaNacimiento = this.gestionarPersonaForm.controls.fechaNacimiento.value;
        
        
        
        console.log('persona-->',this.persona);
        this.gestionarPersonaService.crearPersona(this.persona).subscribe(res => {
            this.mensajeConsulta = res.mensajeEjecucion;
            console.log('MensajeConsulta-->',this.mensajeConsulta);
            if(res.exitoso){
                this.limpiarFormulario();
                this.consultarPersonas();
                this.mostrarMensaje = true;
            }
            console.log('se llamo metodo asincronico crearPersona()');

        }, err => {
            console.log('error en la consulta crear comic->',err);
        })
        //this.listaComics.push(this.comic);
     
        
    }

    

    

    /**
     * Metodo que permite consultar una persona de la tabla y sus detalles e inhabilitar el formulario
     * @param posicion en la lista  personas seleccionado
     */
    public consultarPersona(posicion : number) : void {
        let persona = this.listaPersonas[posicion];
        this.gestionarPersonaForm.controls.nombre.setValue(persona.nombre);
        this.gestionarPersonaForm.controls.tipoDocumento.setValue(persona.tipoDocumento);
        this.gestionarPersonaForm.controls.numeroDocumento.setValue(persona.numeroDocumento);
        this.gestionarPersonaForm.controls.fechaNacimiento.setValue(persona.fechaNacimiento);
        
        this.gestionarPersonaForm.controls.nombre.disable();
        this.gestionarPersonaForm.controls.tipoDocumento.disable();
        this.gestionarPersonaForm.controls.numeroDocumento.disable();
        this.gestionarPersonaForm.controls.fechaNacimiento.disable();
      
    }

    
    private limpiarFormulario() : void {
        this.submitted = false;
        this.gestionarPersonaForm.controls.nombre.setValue(null);
        this.gestionarPersonaForm.controls.tipoDocumento.setValue(null);
        this.gestionarPersonaForm.controls.numeroDocumento.setValue(null);
        this.gestionarPersonaForm.controls.fechaNacimiento.setValue(null);
       
    }
     /**
     * @description Metodo que obtiene los controles y sus propiedades
     * 
     */
    get f() { 
        return this.gestionarPersonaForm.controls;
    }

   
}