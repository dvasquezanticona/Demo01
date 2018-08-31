import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { UserModel } from '../model/user.model';
import { UserService } from './user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  providers: [UserService]
})
export class UserComponent implements OnInit {

  private users:Array<UserModel>;
  constructor(private userService:UserService,private router:Router) { }

  ngOnInit() {
    console.log('[DVA] UserComponent.ngOnInit del user.component');
    this.loadUser();
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
      this.userService.delete(user);
      location.reload(); 
    }
    
  }
}
