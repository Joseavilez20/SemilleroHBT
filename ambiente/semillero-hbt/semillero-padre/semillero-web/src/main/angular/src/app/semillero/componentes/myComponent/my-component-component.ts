import { Component, OnInit } from '@angular/core';
import {ExampleDTO} from '../../dto/example.dto';

/**
 * @description La clase MyComponent permite crear un componente para mostrar un nombre y la ciudad
 * @author Jose Aviles Pacheco <joseavilesmnt@gmail.com>
 */
@Component({
    selector: 'my-component',
    templateUrl: './my-component-component.html'
})

export class MyComponent implements OnInit{

/** 
 * Un atributo para el nombre de la persona
 * @type {string}
 * @access (public)
 */
    public nombre : string;

    /** 
 * Un atributo para la ciudad de la persona
 * @type {string}
 * @access (public)
 */
    public ciudad : string;

      /** 
 * Un atributo que contiene la informacion de ExampleDTO
 * @type {ExampleDTO}
 * @access (public)
 */

    public itemDTO : ExampleDTO;

     /**
     * Atributo que contendra la lista de ExampleDTO 
     */
    public listaItems : Array<ExampleDTO>;

    /**
     * Atributo que contará los items agregados a la lista 
     */
    public idItems : number = 0;

    /**
     * Atributo que guarda un objeto ExampleDTO en objeto tipo JSON
     */

     public JsonDTO : string;
     /**
      * Atributo para guardar el nombre del item eliminado
      */
     public itemEliminado : string;
    /**
   * Evento angular que se ejecuta al iniciar el componente
   */
    ngOnInit(): void {
        //Asignación de un valor a la variable nombre
        this.nombre = 'Jose Aviles Pacheco';
         //Asinación de un valor a la variable ciudad
        this.ciudad = 'Montería';

        //Instaciación de un objeto ExampleDTO
        this.itemDTO = new ExampleDTO();
        //Creacion de la lista para items ExampleDTO
        this.listaItems = new Array<ExampleDTO>();
        this.llenarLista();
    }

    public llenarLista() : void {
    
    for (let item = 0; item < 5; item++) {
        
        this.idItems++;    
        this.itemDTO = new ExampleDTO();
        this.itemDTO.id = this.idItems;
        this.itemDTO.nombre = 'name'+this.idItems;
        this.itemDTO.editorial = 'ed'+this.idItems;
        this.itemDTO.tematica = 'topic'+this.idItems;
        this.itemDTO.numeroPaginas = this.idItems;
        this.itemDTO.precio = this.idItems * 1000;
        this.itemDTO.autores = 'actor actor';
        this.itemDTO.acolor = true;
        this.itemDTO.fechaVenta = new Date();
        this.itemDTO.estado = 'Available';
        this.listaItems.push(this.itemDTO);

    }

    

    }

    deleteItem(param : number){
       let removed = this.listaItems.splice(param, 1);
       console.log('->',removed);
       //this.itemEliminado = removed.nombre;
    }

}