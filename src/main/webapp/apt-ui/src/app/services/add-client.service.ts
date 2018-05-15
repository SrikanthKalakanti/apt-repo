import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { ApiService } from './api.service';
import { AuthService } from './auth.service';

@Injectable()
export class AddClientService {

  constructor(
    private apiService: ApiService,
    private authService: AuthService
  ) { }

  addClient(clientObject): Observable<any> {
    const path = '/apt/client/createclient';
    return this.apiService.post(path, clientObject, this.authService.getHeaders())
      .map(data => {
        return data;
      });
  }

}
