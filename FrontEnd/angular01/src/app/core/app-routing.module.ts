import { LoginComponent } from '../login/login.component';
import {RouterModule, Routes} from '@angular/router';
import { NgModule } from '@angular/core';

import { AppComponent } from '../app.component';
import { UserComponent } from '../user/user.component';
import { CreateUserComponent } from '../create-user/create-user.component';
import { HomeComponent } from '../home/home.component';

/**
 * Esta constante contiene todos los paths que 
 * soporta la aplicación.
 */

const rutas: Routes = [
    /*{path: '',redirectTo:'/userComponent',pathMatch:'full'},*/    
    {path: '',component:LoginComponent},
    {path: 'home',component:HomeComponent},
    {path: 'login',component:LoginComponent},
    {path: 'appComponent',component:AppComponent},
    {path: 'userComponent',component:UserComponent},
    {path: 'createUserComponent',component:CreateUserComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(rutas)],
    exports: [RouterModule],
})
export class AppRoutingModule { }