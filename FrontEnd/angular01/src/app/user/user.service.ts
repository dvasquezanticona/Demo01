import { UserModel } from '../model/user.model';
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

  private users:Array<UserModel>;

  constructor(private http:HttpClient) { }

  public getUsers():Observable<UserModel[]>{
    console.log('[DVA] UserService.getUsers - Obteniendo usuarios desde el servicio REST'); 
    return this.http.get<UserModel[]>("http://localhost:8080/getUsers");
  }

  public delete(user:UserModel):void{
    console.log('[DVA] UserService.delete - Invocamos al servicio REST para borrar usuario'); 
    this.http.post("http://localhost:8080/deleteUser",JSON.stringify(user)).subscribe();
  }
}
