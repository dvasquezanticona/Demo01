// token.service.ts
import { Injectable } from '@angular/core';

@Injectable()
export class TokenService {

  public get(): string {
    return sessionStorage.getItem('token');
  }

  public set(token: string): void {
    sessionStorage.setItem('token', token);
  }

  public deleteToken(): void {
    sessionStorage.removeItem('token');
  }
}