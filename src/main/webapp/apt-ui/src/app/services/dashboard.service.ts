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

  getClientList(): Observable<ClientData> {
    const path = '/apt/client/getallclientsbycustomer' + '?customerId=' + localStorage.getItem('customerId');
    return this.apiService.get(path, this.authService.getHeaders())
      .map(data => {
        return data;
      });
  }

}
