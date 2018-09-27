import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from './login.service';
import { UserModel } from './../model/user.model';
import { AppSessionService } from '../core/app-session.service';
import { environment } from '../core/app-const.module';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers:[LoginService]
})
export class LoginComponent implements OnInit {

  private ses: AppSessionService;
  
  constructor(private loginService:LoginService, private router : Router) {
    console.log("[DVA] Constructor del LoginComponent...");
    console.log("[DVA] Inicializando de la aplicación. Preparando token de inicio.");
    this.ses=new AppSessionService;
    this.ses.setFlagInicioSession(environment.TOKEN_SIN_SESSION);    
  }

  ngOnInit() {
  }

  username : string;
  password : string;
  isValid : boolean=true;
  message: string;
  usuarioLogin:UserModel;

  /**
   * Metodo encargado de hacer login.
   */
  login() : void {

    console.log("[DAV] Usuario y password: " + this.username + " - " + this.password );

    if (this.username==null || this.password==null || this.username == '' || this.password == ''){
      this.isValid=false;
      this.message="Bro!!! Debes ingresar un usuario y password."
      console.log("[DVA] Bro!! Debes ingresar un usuario y password." );
      return;
    }

    //Seteamos el login y password al usuario
    this.usuarioLogin=new UserModel;
    this.usuarioLogin.login=this.username;
    this.usuarioLogin.password=this.password;
    //Invocamos al servicio de login
    console.log("[DVA] Enviamos al el servicio el usuario y passwword.");
    this.loginService.login(this.usuarioLogin).subscribe(respuesta=>{
      if (respuesta.loginOK){
        //Si todo ha ido bien Navegamos al home
        console.log("[DVA] El servicio de login devuelve un TRUE. Login correcto. " + respuesta.tokenType + " Guardamos el token en la session: " + respuesta.accessToken);
        this.ses.setFlagInicioSession(respuesta.accessToken); 
        this.router.navigate(["home"]);
      }else{
        console.log("[DVA] El servicio de login devuelve un FALSE.");
        this.isValid=false;
        this.message="Credenciales incorrectas Bro!!. El usuario/password es admin/123456"
      }
    },
    error=>{
      console.log("Error no controlado. Mensaje devuelto desde el service: " + <any>error );
      this.isValid=false;
      this.message="Error no controlado Bro!! - " + <any>error;
    }
    );
  }

}
