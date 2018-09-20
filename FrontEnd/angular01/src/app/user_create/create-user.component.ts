import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { UserModel } from '../model/user.model';
import { CreateUserService } from './create-user.service';
import { OK } from '../core/httpStatus';
import { UserLogin } from '../model/userLogin.model';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css'],
  providers:[CreateUserService]
})

/**
 * Esta clase Componente se leerá desde la pagina html.
 */
export class CreateUserComponent implements OnInit {

  private user:UserModel;
  private userLogin:UserLogin;
  private isValid:boolean= true;
  private message:String;

  constructor(private createUserService:CreateUserService, private router:Router) { 
    if (sessionStorage.getItem('user')){
      this.user=JSON.parse(sessionStorage.getItem('user'));
      this.userLogin=new UserLogin;
      this.userLogin.password=this.user.password;
      this.userLogin.password2=this.user.password;
    }else{
      this.user=new UserModel;
      this.userLogin=new UserLogin;
    }
  }

  ngOnInit() {
  }

  public saveOrUpdate():void{
    this.isValid=this.createUserService.validate(this.user,this.userLogin);
    if (this.isValid){
      if (!this.createUserService.validatePass(this.userLogin)){
        console.log('[DVA] create-user-component.saveOrUpdate - La password no coincide.');
        this.message="La password no coincide."
        this.isValid=false;
      } else{
        //Si la validación del password es correcta lo añadimos al modelo
        this.user.password=this.userLogin.password;
        this.createUserService.saveOrUpdate(this.user).subscribe(res=>{
          if(res.responseCode==OK){
            console.log('[DVA] create-user-component. Se ha dado de alta al usuario.');
            this.router.navigate(['/userComponent']);
          }else{
            console.log('[DVA] create-user-component. Ocurrio un error distinto.');
          }
        });
      }  
    }else{
      console.log('[DVA] create-user-component.saveOrUpdate - Faltan campos por informar.');
      this.message="Los campos con * son obligatorios."
    }
    sessionStorage.clear();
  }

  /***
   * Limpiamos lo que tengamos en sesión y regresamos al listado 
   * de usuarios.
   */
  public cancelSaveOrUpdate():void{
    sessionStorage.clear();
    this.router.navigate(['/userComponent']);
  }
}
