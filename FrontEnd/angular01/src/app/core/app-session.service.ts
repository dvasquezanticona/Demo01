import { Injectable } from '@angular/core';
import { environment } from './app-const.module';

@Injectable()
export class AppSessionService {

  private url: string = 'api/auth';
  private headers = new Headers({ 'Content-Type': 'application/json' });

  public getToken(): string {
    //return localStorage.getItem(environment.TOKEN_NAME);
    return sessionStorage.getItem(environment.TOKEN_NAME);
  }

  public setToken(token: string): void {
    //localStorage.setItem(environment.TOKEN_NAME, token);
    sessionStorage.setItem(environment.TOKEN_NAME, token);
  }

  public setFlagInicioSession(token: string): void {
    //localStorage.setItem(environment.TOKEN_NAME, token);
    sessionStorage.setItem(environment.TOKEN_NAME, token);
  }

  public get authHeader(): string {
    return `Bearer ${sessionStorage.getItem('token')}`;
  }

  public deleteToken(): void { //TODO: hay que revisar si sería bueno borrar esta propiedad
    sessionStorage.removeItem('token');
  }

}