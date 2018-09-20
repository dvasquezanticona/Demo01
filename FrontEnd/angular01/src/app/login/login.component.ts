import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from './login.service';
import { UserModel } from './../model/user.model';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers:[LoginService]
})
export class LoginComponent implements OnInit {

  constructor(private loginService:LoginService, private router : Router) {
    console.log("Construimos un objeto LoginComponent...");
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

    console.log("Usuario y password: " + this.username + " - " + this.password );

    if (this.username==null || this.password==null || this.username == '' || this.password == ''){
      this.isValid=false;
      this.message="Bro!!! Debes ingresar un usuario y password."
      console.log("Bro!! Debes ingresar un usuario y password." );
      return;
    }

    //Seteamos el login y password al usuario
    this.usuarioLogin=new UserModel;
    this.usuarioLogin.login=this.username;
    this.usuarioLogin.password=this.password;
    //Invocamos al servicio de login
    console.log("Enviamos al el servicio el usuario y passwword.");
    this.loginService.login(this.usuarioLogin).subscribe(respuesta=>{
      if (respuesta.loginOK){
        //Si todo ha ido bien Navegamos al home
        console.log("El servicio de login devuelve un TRUE. Login correcto. " + respuesta.tokenType + " El token es: " + respuesta.accessToken);
        this.router.navigate(["home"]);
      }else{
        console.log("El servicio de login devuelve un FALSE.");
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
