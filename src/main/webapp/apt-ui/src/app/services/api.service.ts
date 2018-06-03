import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class ApiService {

  constructor(
    private http: HttpClient
  ) { }

  private formatErrors(error: any) {
    console.log(error.status);
    if (error.status === 401 && location.pathname !== '/login') {
      localStorage.clear();
      location.pathname = '/login';
      location.reload();
    }
    return Observable.throw(error);
  }

  post(path: string, body: Object = {}, headers: HttpHeaders): Observable<any> {
    return this.http.post(`${environment.api_url}${path}`, JSON.stringify(body), { headers: headers })
      .catch(this.formatErrors);
  }

  get(path: string, headers: HttpHeaders): Observable<any> {
    return this.http.get(`${environment.api_url}${path}`, { headers: headers })
      .catch(this.formatErrors);
  }

  getReports(path: string, headers: HttpHeaders): Observable<any> {
    return this.http.get(
      `${environment.api_url}${path}`,
      { headers: headers, responseType: 'blob' }).map(
        res => res
      )
      .catch(this.formatErrors);
  }

}
