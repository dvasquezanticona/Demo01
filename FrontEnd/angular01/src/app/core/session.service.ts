// session.service.ts
import { Injectable } from '@angular/core';
import { environment } from './app-const.module';

@Injectable()
export class TokenService {

  private url: string = 'api/auth';
  private headers = new Headers({ 'Content-Type': 'application/json' });

  getToken(): string {
    return localStorage.getItem(environment.TOKEN_NAME);
  }

  setToken(token: string): void {
    localStorage.setItem(environment.TOKEN_NAME, token);
  }

  public get authHeader(): string {
    return `Bearer ${sessionStorage.getItem('token')}`;
  }
}