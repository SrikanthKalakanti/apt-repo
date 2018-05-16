import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Http } from '@angular/http';
import { Errors } from '../../shared/models/errors.model';
import { ErrorService } from '../../shared/services/error.service';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs/Subject';
import { CLIENTDETAILS } from '../../mocks/clientList';
import { DashboardService } from '../../services/dashboard.service';
import { SharedService } from '../../shared/services/shared.service';

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
    private errorService: ErrorService,
    private dashBoardService: DashboardService,
    private sharedService: SharedService
  ) { }

  ngOnInit() {
    const self = this;
    this.dtOptions = {
      pageLength: 50,
      order: [0, 'desc'],
      dom: 'Blfrtip',
      aoColumnDefs: [{ bSortable: false, aTargets: [2,3,4,5,7] }],
      bRetrieve: true,
      bootstrap: true,
      //bStateSave: true,
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

  openCustomerDetails(data) {
    console.log(data);
    this.sharedService.setClientData(data);
    localStorage.setItem('clientData', JSON.stringify(data));
    this.router.navigateByUrl('/customerDetails');
  }

  getAllClientList() {
    this.isDataAvailable = true;
    //this.data = CLIENTDETAILS;
    this.dashBoardService.getClientList().subscribe(
      data => {
        if(data.result){
          this.data = data.result;
          this.isDataAvailable = true;
        } else {
          this.isDataAvailable = false;
        }
        this.dtTrigger.next();
      },
      err => {
        this.isDataAvailable = false;
        this.errors = err.error;
        this.errorService.error(this.errors);
      });
  }

}
