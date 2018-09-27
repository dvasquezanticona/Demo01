import { environment } from '../core/app-const.module';
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

import { AppSessionService } from './app-session.service';

@Injectable({
    providedIn: 'root'
  })
export class CanActivateViaAuthGuard implements CanActivate {

    private appSessionService:AppSessionService;

    constructor(public router : Router) {}

    canActivate():boolean{
        console.log("[DVA] CanActivate Comprobando si existe login." )
        this.appSessionService=new AppSessionService();
        if (this.appSessionService.getToken() == '' || this.appSessionService.getToken().length==0 || this.appSessionService.getToken() == environment.TOKEN_SIN_SESSION){
            console.log("[DVA] CanActivate - No ha iniciado session. TOKEN: " + this.appSessionService.getToken())
            this.router.navigate(['login']);
            return false;
        }
        console.log("[DVA] CanActivate - Se comprueba que ha iniciado session. TOKEN: " + this.appSessionService.getToken())
        return true;
    }

}