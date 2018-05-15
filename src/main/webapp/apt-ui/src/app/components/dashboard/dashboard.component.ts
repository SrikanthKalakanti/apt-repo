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

  openCustomerDetails() {
    this.router.navigateByUrl('/customerDetails');
  }

  getAllClientList() {
    this.isDataAvailable = true;
    this.data = [CLIENTDETAILS];
  this.dtTrigger.next();
    this.http.get('clientList.json')
                .subscribe(data => {
                  this.isDataAvailable = true;
                  this.data = data;
                },
                err => {
                  this.errors = err.error;
                  this.errorService.error(this.errors);
                });
  }

}
