import { Injectable } from '@angular/core';

@Injectable()
export class AuthService {

  isLoggedIn = false;

  redirectUrl: string;

  authToken: string;

  login(username, password) {
    this.authToken = btoa(`${username}:${password}`);
    this.isLoggedIn = true;
  }

  logout(): void {
    this.isLoggedIn = false;
  }

}
