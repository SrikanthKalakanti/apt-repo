import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs/Observable';

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

}
