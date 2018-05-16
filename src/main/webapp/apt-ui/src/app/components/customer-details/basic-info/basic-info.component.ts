import { Component, OnInit } from '@angular/core';
import { CustomerDetailsService } from '../../../services/customer-details.service';
import { ErrorService } from '../../../shared/services/error.service';
import { Errors } from '../../../shared/models/errors.model';

@Component({
  selector: 'app-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['./basic-info.component.css']
})
export class BasicInfoComponent implements OnInit {

  isDataAvailable = false;
  isEdit = false;
  errors: Errors = new Errors();
  basicData;

  model: any = {
    nameOfTheBusiness: 'Accenture',
    status: '-1',
    state: '-1',
    lineofactivity: 'Manufacturing',
    customerId: localStorage.getItem('customerId')
  };

  constructor(
    private customerDetailsService: CustomerDetailsService,
    private errorService: ErrorService
  ) { }

  ngOnInit() {
    this.getBasicInput();
  }

  showForm() {
    this.isEdit = true;
  }

  getBasicInput() {
    //this.isDataAvailable = true;
    this.customerDetailsService.getBasicInput().subscribe(
      data => {
        if(data.result){
          this.basicData = data.result;
          this.isDataAvailable = true;
        } else {
          this.isDataAvailable = false;
        }
        //this.dtTrigger.next();
      },
      err => {
        this.isDataAvailable = false;
        this.errors = err.error;
        this.errorService.error(this.errors);
      });
  }

}
