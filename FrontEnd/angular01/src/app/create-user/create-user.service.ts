import { UserModel } from '../model/user.model';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { RestResponse } from '../model/restResponse.model';
import { HttpClient } from "@angular/common/http";
import { UserLogin } from '../model/userLogin.model';

@Injectable({
  providedIn: 'root'
})
export class CreateUserService {

  constructor(private http: HttpClient) { 

  }

  /**
   * Metodo que valida campos obligatorios
   * @param user
   */
  public validate(user:UserModel,userLogin:UserLogin):boolean{
    let isValid =true;

    if (!user.firstName){
      isValid=false;
    }
    if (!user.firstSurname){
      isValid=false;
    }
    if (!user.address){
      isValid=false;
    }
    if (!user.login){
      isValid=false;
    }
    if(!userLogin.password){
      isValid=false;
    }
    if(!userLogin.password2){
      isValid=false;
    }
    return isValid;
  }

  /**
   * Metodo que valida si las dos password son diferentes
   * @param user
   */
  public validatePass(user:UserLogin):boolean{
    let isValid =true;

    if (user.password != user.password2){
      isValid=false;
    }
    return isValid;
  }

  /**
   * Metodo que invoca el webservice REST del backend
   * @param user 
   */
  public saveOrUpdate(user:UserModel):Observable<RestResponse>{
    /* Llamamos al babckend */
    return this.http.post<RestResponse>('http://localhost:8080/saveOrUpdate',JSON.stringify(user));
  }
}
