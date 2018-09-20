import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

import { UserModel } from '../model/user.model';
import { RestResponseLogin } from '../model/restResponseLogin.model';
import { environment } from '../core/app-const.module';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { 
    console.log("Iniciando constructor del login service.");
  }

  
  login(loginObj: UserModel): Observable<RestResponseLogin> {
    console.log("Invocando al apirest desde el servicio.");
    return this.http.post<RestResponseLogin>( environment.URL_WEBSERVICE_REST + '/login',JSON.stringify(loginObj)).pipe(
      catchError(this.handleErrorObservable)
    );
  }
/*
  logout(): Observable<Boolean> {
    //return this.http.post(this.basePath + 'logout', {}).map(this.extractData);
    return null;
  }

  private extractData(res: Response) {
    let body = res.json();
    return body;
  }
*/
  private handleErrorObservable (error: Response | any) {
    console.log("Mensaje de error desde el handleErrorObservable. Devolvemos un Observable.throw. " + error.message + " - " + error.status)

    if (error.status == 0){
      return throwError("El servicio no está disponible");
    }

    return throwError("Error al realizar el login. " + error.message);
  }

}
