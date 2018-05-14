import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Http } from '@angular/http';
import { Errors } from '../../shared/models/errors.model';
import { ErrorService } from '../../shared/services/error.service';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs/Subject';
import { CLIENTDETAILS } from '../../mocks/clientList';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  errors: Errors = new Errors();
  isDataAvailable = false;
  data;
  dtOptions: any = {};
  dtTrigger: Subject<any> = new Subject();

  constructor(
    private router: Router,
    private http: HttpClient,
    private errorService: ErrorService
  ) { }

  ngOnInit() {
    const self = this;
    this.dtOptions = {
      pageLength: 50,
      order: ['clientId', 'desc'],
      dom: 'Blfrtip',
      bRetrieve: true,
      bootstrap: true,
      bStateSave: true,
      // buttons: [
      //   {
      //     text: 'Refresh',
      //     key: '1',
      //     action: function (e, dt, node, config) {
      //       dt.destroy();
      //       self.data = [];
      //       self.ngOnInit();
      //     }
      //   }
      // ]
    };
    this.getAllClientList();
  }

  addClient() {
    this.router.navigateByUrl('/addclient');
  }

  getAllClientList() {
    this.isDataAvailable = true;
    this.data = [CLIENTDETAILS];
    // [[{
//       'clientId': 1,
//       'clientName': 'Sampath Kumar',
//       'clientStatus': 'Propreitorship',
//       'address': 'XYZ',
//       'phone': '1234567890',
//       'email': 'sampath@gmail.com',
//       'loa': 'Manufacturing',
//       'termLoadDate': '01/20/2018'
//   },
//   {
//       'clientId': 2,
//       'clientName': 'Sampreeth Kumar',
//       'clientStatus': 'Propreitorship',
//       'address': 'XYZ',
//       'phone': '123456789',
//       'email': 'sampath@gmail.com',
//       'loa': 'Manufacturing',
//       'termLoadDate': '01/20/2018'
//   },
//   {
//       'clientId': 3,
//       'clientName': 'Murty Kumar',
//       'clientStatus': 'Propreitorship',
//       'address': 'XYZ',
//       'phone': '123456789',
//       'email': 'sampath@gmail.com',
//       'loa': 'Manufacturing',
//       'termLoadDate': '01/20/2018'
//   },
//   {
//       'clientId': 4,
//       'clientName': 'Srikanth',
//       'clientStatus': 'Propreitorship',
//       'address': 'XYZ',
//       'phone': '123456789',
//       'email': 'sampath@gmail.com',
//       'loa': 'Manufacturing',
//       'termLoadDate': '01/20/2018'
//   },
//   {
//       'clientId': 5,
//       'clientName': 'Laxmi',
//       'clientStatus': 'Propreitorship',
//       'address': 'XYZ',
//       'phone': '123456789',
//       'email': 'sampath@gmail.com',
//       'loa': 'Manufacturing',
//       'termLoadDate': '01/20/2018'
//   },
//   {
//       'clientId': 6,
//       'clientName': 'Naveen',
//       'clientStatus': 'Propreitorship',
//       'address': 'XYZ',
//       'phone': '123456789',
//       'email': 'sampath@gmail.com',
//       'loa': 'Manufacturing',
//       'termLoadDate': '01/20/2018'
//   },
//   {
//       'clientId': 7,
//       'clientName': 'Ramu',
//       'clientStatus': 'Propreitorship',
//       'address': 'XYZ',
//       'phone': '123456789',
//       'email': 'sampath@gmail.com',
//       'loa': 'Manufacturing',
//       'termLoadDate': '01/20/2018'
//   },
//   {
//       'clientId': 8,
//       'clientName': 'Kishore',
//       'clientStatus': 'Propreitorship',
//       'address': 'XYZ',
//       'phone': '123456789',
//       'email': 'sampath@gmail.com',
//       'loa': 'Manufacturing',
//       'termLoadDate': '01/20/2018'
//   },
//   {
//       'clientId': 9,
//       'clientName': 'Ramya',
//       'clientStatus': 'Propreitorship',
//       'address': 'XYZ',
//       'phone': '123456789',
//       'email': 'sampath@gmail.com',
//       'loa': 'Manufacturing',
//       'termLoadDate': '01/20/2018'
//   },
//   {
//       'clientId': 10,
//       'clientName': 'Manoj',
//       'clientStatus': 'Propreitorship',
//       'address': 'XYZ',
//       'phone': '123456789',
//       'email': 'sampath@gmail.com',
//       'loa': 'Manufacturing',
//       'termLoadDate': '01/20/2018'
//   },
//   {
//       'clientId': 11,
//       'clientName': 'Swathi',
//       'clientStatus': 'Propreitorship',
//       'address': 'XYZ',
//       'phone': '123456789',
//       'email': 'sampath@gmail.com',
//       'loa': 'Manufacturing',
//       'termLoadDate': '01/20/2018'
//   }]];
  this.dtTrigger.next();
    // this.http.get('clientList.json')
    //             .subscribe(data => {
    //               this.isDataAvailable = true;
    //               this.data = data;
    //             },
    //             err => {
    //               this.errors = err.error;
    //               this.errorService.error(this.errors);
    //             });
  }

}
