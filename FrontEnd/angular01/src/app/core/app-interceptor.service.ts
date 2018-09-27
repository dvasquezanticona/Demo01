import { environment } from './app-const.module';
// token.service.ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { AppSessionService } from './app-session.service';

@Injectable()
export class AppInterceptorService implements HttpInterceptor {

  private sessionService:AppSessionService;

  constructor(){
    console.log("[DVA] broooo!!!! constructor del app-interceptor!!!");
    //Creando un objetoSessionService
    this.sessionService=new AppSessionService;
  }

  public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("[DVA] broooo!!!! te acabo de interceptar!!!");
    if (this.sessionService.getToken()!=environment.TOKEN_SIN_SESSION){
      console.log("[DVA] Se encontró una session activa" + this.sessionService.getToken());
      console.log("Clonando petición para añadir el token");
      const dummyreq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${this.sessionService.getToken()}`
        }
      });
      console.log("Petición clonada");
      console.log(dummyreq);   
      return next.handle(dummyreq);       

      /** 
      const authReq = this.getReqWithAuthorization(req);
      return next.handle(authReq).pipe(tap(null, this.handleError));
      */
    }else{
      console.log("[DVA] Es el inicio, proseguimos. Token: " + this.sessionService.getToken());
      return next.handle(req);
    }
  }

  private getReqWithAuthorization = (req: HttpRequest<any>) =>
  req.clone({
      setHeaders: { Authorization: `Bearer ${ this.sessionService.getToken }` }   //
    });

  private handleError = err => {
    let userMessage = 'Fatal error';
    if (err instanceof HttpErrorResponse) {
      if (err.status === 401) {
        userMessage = 'Authorization needed';
      } else {
        userMessage = 'Comunications error';
      }
    }
    console.log("[DVA] HandleError del interceptor!!!" + userMessage);
  };
}