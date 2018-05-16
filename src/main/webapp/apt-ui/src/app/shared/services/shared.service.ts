import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable()
export class SharedService {

  constructor() { }

  public _clientData;

  setClientData(clientData) {
    console.log(clientData);
    this._clientData = clientData;
  }

  getClientData(): any {
    console.log(this._clientData); 
    return this._clientData; 
  }

}
