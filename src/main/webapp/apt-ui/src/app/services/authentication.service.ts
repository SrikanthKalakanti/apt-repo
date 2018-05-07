import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { HttpHeaders } from '@angular/common/http';
import { User } from '../shared/models/user.model';
import { ApiService } from './api.service';

@Injectable()
export class AuthService {

  constructor(private router: Router, private apiService: ApiService) { }

  // Create expiration from current time and store jwtToken and UserDisplayName in Local Storage
  setSession(user: User) {
    const expiration = moment().add(29, 'minutes').format('MMMM D YYYY hh:mm:ss a');
    localStorage.setItem('expiration', expiration);
    localStorage.setItem('jwtToken', user.jwtToken);
    localStorage.setItem('userDisplayName', user.userDisplayName);
  }

  private headers(body: Object = {}) {
    let headersConfig = {};
    const defaultConfig = {
      'Content-Type': 'application/json',
      'Accept': '*/*',
      'Access-Control-Allow-Origin': '*'
    };

    if (this.getToken()) {
      defaultConfig['Authorization'] = `JWT ${this.getToken()}`;
    }

    headersConfig = { ...defaultConfig, ...body };

    return new HttpHeaders(headersConfig);
  }

  getHeaders(body: Object = {}) {
    return this.headers(body);
  }

  // Check if user is already logged in and return token if true
  getToken(): String {
    if (this.isLoggedIn()) {
      return localStorage['jwtToken'];
    }
  }

  // Remove all info from Local Storage
  logout() {
    this.invalidateToken().subscribe(
      data => {
        console.log(data);
      },
      err => {
        console.log(err);
      });
    localStorage.clear();
    this.router.navigate(['/SCC/login']);
  }

  invalidateToken() {
    const path = 'logout';
    return this.apiService.get(path, this.getHeaders()).map(data => {
      return data;
    });
  }

  // Check if jwtToken exists and compare it's expiration date with current date & time
  public isLoggedIn() {
    const now = moment().format('MMMM D YYYY hh:mm:ss a');
    if (!(localStorage.jwtToken && moment(now).isBefore(this.getExpiration()))) {
      return false;
    } else {
      return true;
    }
  }

  isLoggedOut() {
    return !this.isLoggedIn();
  }

  // Get expiration from browser's Local Storage and return it as moment object
  getExpiration() {
    const expiration = localStorage.getItem('expiration');
    return moment(expiration);
  }

}
