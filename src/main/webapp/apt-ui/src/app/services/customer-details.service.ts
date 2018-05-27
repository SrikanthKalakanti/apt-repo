import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { ApiService } from "./api.service";
import { AuthService } from "./auth.service";

@Injectable()
export class CustomerDetailsService {
  constructor(
    private apiService: ApiService,
    private authService: AuthService
  ) {}

  getBasicInput(): Observable<any> {
    const path = "/apt/client/basicinput/getallbyclient"; // + '?customerId=' + localStorage.getItem('customerId');
    const data = JSON.parse(localStorage.getItem("clientData"));
    console.log(data.clientId);
    const obj = { clientId: data.clientId };
    return this.apiService
      .post(path, obj, this.authService.getHeaders())
      .map(data => {
        return data;
      });
  }

  updateBasicInput(basicInputObj): Observable<any> {
    const path = "/apt/client/basicinput/update"; // + '?customerId=' + localStorage.getItem('customerId');
    return this.apiService
      .post(path, basicInputObj, this.authService.getHeaders())
      .map(data => {
        return data;
      });
  }
  addBasicInfo(addBasicInfoObj): Observable<any> {
    const path = "/apt/client/basicinput/create"; // + '?customerId=' + localStorage.getItem('customerId');
    const data = JSON.parse(localStorage.getItem("clientData"));
    console.log(data.clientId);
    addBasicInfoObj.clientId = data.clientId;
    return this.apiService
      .post(path, addBasicInfoObj, this.authService.getHeaders())
      .map(data => {
        return data;
      });
  }
  getAllAssets(): Observable<any> {
    const path = "/apt/client/asset/getallbyclient"; // + '?customerId=' + localStorage.getItem('customerId');
    const data = JSON.parse(localStorage.getItem("clientData"));
    console.log(data.clientId);
    const obj = { clientId: data.clientId };
    return this.apiService
      .post(path, obj, this.authService.getHeaders())
      .map(data => {
        return data;
      });
  }
  addAsset(addAssetObj): Observable<any> {
    const path = "/apt/client/asset/create"; // + '?customerId=' + localStorage.getItem('customerId');
    const data = JSON.parse(localStorage.getItem("clientData"));
    console.log(data.clientId);
    addAssetObj.clientId = data.clientId;
    return this.apiService
      .post(path, addAssetObj, this.authService.getHeaders())
      .map(data => {
        return data;
      });
  }
  updateAsset(updateAssetObj): Observable<any> { 
    const path = "/apt/client/asset/update"; // + '?customerId=' + localStorage.getItem('customerId');
    const data = JSON.parse(localStorage.getItem("clientData"));
    console.log(data.clientId);
    updateAssetObj.clientId = data.clientId;
    return this.apiService
      .post(path, updateAssetObj, this.authService.getHeaders())
      .map(data => {
        return data;
      });
  }
  removeAsset(removeAssetObj): Observable<any> {
    const path = "/apt/client/asset/delete"; // + '?customerId=' + localStorage.getItem('customerId');
    const data = JSON.parse(localStorage.getItem("clientData"));
    var obj = {
      clientId: data.clientId,
      assetId: removeAssetObj.assetId
    }
    return this.apiService
      .post(path, obj, this.authService.getHeaders())
      .map(data => {
        return data;
      });
  }
}
