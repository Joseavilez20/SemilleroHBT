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
     * 
     * /**
      * Atributo para verificar si fue eliminado un item
      * Este atributo controla si se debe mostrar el mensaje de eliminado , cuando se pulse el boton eliminar
      */
     public statusEliminado : boolean;
     /**
   * Evento angular que se ejecuta al iniciar el componente
   */
    ngOnInit(): void {
        //Asignación de un valor a la variable nombre
        this.nombre = 'Jose Aviles Pacheco';
         //Asinación de un valor a la variable ciudad
        this.ciudad = 'Montería';
        //Inicializo la variable a false, ya que permitirá controlar si se debe mostrar el mensaje en el template
        this.statusEliminado = false;

        //Instaciación de un objeto ExampleDTO
        this.itemDTO = new ExampleDTO();
        //Creacion de la lista para items ExampleDTO
        this.listaItems = new Array<ExampleDTO>();

        //Se llama el metodo para llenar la lista
        this.llenarLista();
    }

     /**
   * Metodo para llenar la lista con 5 objetos 
   */
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

     /**
   * Función para eliminar un item de la lista , recibe como argumento un numero
   */
    deleteItem(param : number){
        //array.splice(param, 1) permite eliminar de la lista un dato en cierta posición
       let removed = this.listaItems.splice(param, 1);
       //se verifica que se haya eliminado un dato de la lista
       if(removed.length > 0){
           //se almacena el nombre del item eliminado
           this.itemEliminado = removed[0].nombre;
           //Asigna al estado verdadero, si se elimino algún item
           this.statusEliminado = true;
           console.log('Removed->',this.itemEliminado);
       }else{
             //Asigna al estado false, si no elimino ningún item
        this.statusEliminado = false;
        console.log('No removed->',removed);
       }
       
    }

}