import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs/Observable';
import { State } from '../models/state';

@Injectable()
export class RegisterService {

  constructor(private apiService: ApiService, private authService: AuthService) { }

  register(registerObject): Observable<any> {
    const path = '/apt/customer/register';
    return this.apiService.post(path, registerObject, this.authService.getHeaders())
      .map(data => {
        return data;
      });
  }

  getStates(): Observable<any> {
    const path = 'http://services.groupkt.com/state/get/IND/all';
    return this.apiService.getStates(path, this.authService.getHeaders())
  }

}
