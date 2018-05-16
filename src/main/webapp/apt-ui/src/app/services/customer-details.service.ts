import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { ApiService } from './api.service';
import { AuthService } from './auth.service';

@Injectable()
export class CustomerDetailsService {

  constructor(
    private apiService: ApiService,
    private authService: AuthService
  ) { }

  getBasicInput(): Observable<any> {
    const path = '/apt/client/basicinput';// + '?customerId=' + localStorage.getItem('customerId');
    const data = JSON.parse(localStorage.getItem('clientData'));
    console.log(data.clientId);
    const obj = {clientId: data.clientId};
    return this.apiService.post(path, obj, this.authService.getHeaders())
      .map(data => {
        return data;
      });
  }

}
