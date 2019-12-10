/**
 * @description Clase ExampleDTO que contiene información para conocer la estructura de un DTO
 * 
 * @author jose Aviles Pacheco <joseavilesmnt@gmail.com>
 */

 export class ExampleDTO{
    /**
    * Atributo que identifica cada item
    */
    public  id :number;
     /**
    * Atributo para el nombre 
    */
    public nombre : string;
       /**
    * Atributo para el editorial
    */
    public editorial : string;
       /**
    * Atributo para la tematica a la que pertenece 
    */
    public tematica : string;
       /**
    * Atributo para la cantidad de paginas
    */
    public numeroPaginas : number;
       /**
    * Atributo para el precio o valor de venta 
    */
    public precio : number;
       /**
    * Atributo para los autores , en plural ya que pueden ser varios
    */
    public autores : string;
       /**
    * Atributo para el color
    */
    public acolor : boolean;
       /**
    * Atributo para  la fecha de venta
    */
    public fechaVenta : Date;
       /**
    * Atributo para el estado del item
    */
    public estado : string;

 }