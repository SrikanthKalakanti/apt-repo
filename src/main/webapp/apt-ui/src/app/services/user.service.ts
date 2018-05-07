import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { Observable } from 'rxjs/Observable';

import { ApiService } from './api.service';
import { AuthService } from './authentication.service';
import { User } from '../shared/models/user.model';

@Injectable()
export class UserService {

  constructor(
    private apiService: ApiService,
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) { }

  populate() {
    // If JWT detected, reroute to fileupload screen
    if (this.authService.getToken()) {
      this.router.navigateByUrl('/fileupload');
    }
  }

  setAuth(user: User) {
    // Save response Object sent from server in localstorage
    this.authService.setSession(user);
  }

  // Make modified post request using Api Service
  attemptAuth(credentials): Observable<User> {
    const path = 'login';
    return this.apiService.post(path, credentials, this.authService.getHeaders())
      .map(data => {
        this.setAuth(data);
        return data;
      });
  }

}
