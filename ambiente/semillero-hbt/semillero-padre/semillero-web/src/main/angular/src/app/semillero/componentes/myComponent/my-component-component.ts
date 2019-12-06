import { Component, OnInit } from '@angular/core';


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
   * Evento angular que se ejecuta al iniciar el componente
   */
    ngOnInit(): void {
        this.nombre = 'Jose Aviles Pacheco';
        this.ciudad = 'Montería';
    }

}