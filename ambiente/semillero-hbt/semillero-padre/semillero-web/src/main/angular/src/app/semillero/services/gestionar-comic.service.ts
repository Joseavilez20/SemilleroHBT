import { Injectable } from '@angular/core';
import { Injector } from "@angular/core";
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import { AbstractService } from './template.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ComicDTO } from '../dto/comic.dto';
import { PersonaDTO} from '../dto/persona.dto';

/**
 * Servicio encargado de llamar a los servicios REST de
 * ejemplo
 */
@Injectable({
  providedIn: 'root'
})
export class GestionarComicService extends AbstractService {

  /**
   * Constructor
   */
  constructor(injector: Injector, private httpClient : HttpClient) {
    super(injector);
  }

  
  public consultarComics(): Observable<any> {
    return this.httpClient.get('http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComics');
  }

  public crearComic(comicDTO : ComicDTO): Observable<any> {
    return this.httpClient.post('http://localhost:8085/semillero-servicios/rest/GestionarComic/crear',comicDTO);
  }


  /***
   * REST PARA COMPONENTE GESTIONARPERSONAS
   */

  public consultarPersonas(): Observable<any> {
    return this.httpClient.get('http://localhost:8085/semillero-servicios/rest/GestionarPersona/consultarPersonas');
  }
  public crearPersona(personaDTO : PersonaDTO): Observable<any> {
    return this.httpClient.post('http://localhost:8085/semillero-servicios/rest/GestionarPersona/crear',personaDTO);
  }
}
