import { AppErrorComponent } from './../app-error/app-error.component';
import { LoginComponent } from '../login/login.component';
import { RouterModule, Routes} from '@angular/router';
import { NgModule } from '@angular/core';

import { AppComponent } from '../app.component';
import { UserComponent } from '../user_list_and_delete/user.component';
import { CreateUserComponent } from '../user_create/create-user.component';
import { HomeComponent } from '../home/home.component';
import { CanActivateViaAuthGuard } from './CanActivateViaAuthGuard.service';

/**
 * Esta constante contiene todos los paths que 
 * soporta la aplicación.
 */

const rutas: Routes = [
    /*{path: '',redirectTo:'/userComponent',pathMatch:'full'},*/    
    {path: '' ,component:LoginComponent },
    {path: 'login',component:LoginComponent} ,
    {path: 'error',component:AppErrorComponent} ,
    {path: 'home',component:HomeComponent,canActivate:[CanActivateViaAuthGuard]} ,    
    {path: 'appComponent',component:AppComponent,canActivate:[CanActivateViaAuthGuard]},
    {path: 'userComponent',component:UserComponent,canActivate:[CanActivateViaAuthGuard]},
    {path: 'createUserComponent',component:CreateUserComponent,canActivate:[CanActivateViaAuthGuard]}
]    ;

@NgModule({
    imports: [RouterModule.forRoot(rutas)],
    exports: [RouterModule],
})
export class AppRoutingModule { }