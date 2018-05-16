import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class DashboardService {

  constructor(
    private apiService: ApiService,
    private authService: AuthService
  ) { }

  getClientList(): Observable<any> {
    const path = '/apt/client/getallclientsbycustomer';// + '?customerId=' + localStorage.getItem('customerId');
    const obj = {customerId: localStorage.getItem('customerId')};
    return this.apiService.post(path, obj, this.authService.getHeaders())
      .map(data => {
        return data;
      });
  }

}
