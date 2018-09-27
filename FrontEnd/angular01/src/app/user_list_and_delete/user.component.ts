import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { UserModel } from '../model/user.model';
import { UserService } from './user.service';
import { OK } from '../core/httpStatus';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  providers: [UserService]
})
export class UserComponent implements OnInit {

  private users:Array<UserModel>;
  constructor(private userService:UserService,private router:Router) { 
    console.log('[DVA] Constructor del UserComponent. Cargamos los usuarios.');
    this.loadUser();
  }

  ngOnInit() {
    console.log('[DVA] Metodo ngOnInit del UserComponent');    
  }

  private loadUser():void{
    console.log('[DVA] UserComponent.loadUser - Cargando los usuarios');
    this.userService.getUsers().subscribe(res=>{
      this.users=res;
      console.log(res);
    });
  }

  public edit(user:UserModel):void{
    console.log('[DVA] UserComponent.edit - Editando usuario');
    sessionStorage.setItem('user',JSON.stringify(user));
    this.router.navigate(['/createUserComponent']);
  }

  public delete(user:UserModel):void{
    console.log('[DVA] UserComponent.delete - Borrando usuario');
    if(confirm("¿Está seguro de borrar el registro?")){
     /* this.userService.delete(user).subscribe(res=>{
        if(res.responseCode==OK){
          console.log('[DVA] create-user-component. Se ha dado de alta al usuario.');
          this.router.navigate(['/userComponent']);
        }else{
          console.log('[DVA] create-user-component. Ocurrio un error distinto.');
        }
      }); */
      this.userService.delete(user);
      location.reload(); 
    }
  }

  /***
   * Limpiamos lo que tengamos en sesión y regresamos al listado 
   * de usuarios.
   */
  public toHome():void{
    this.router.navigate(['home']);
  }

}
