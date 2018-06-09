import { Component, OnInit } from '@angular/core';
import { State } from '../../models/state';
import { STATES } from '../../mocks/states';
import { Errors } from '../../shared/models/errors.model';
import { AddClientService } from '../../services/add-client.service';
import { Router } from '@angular/router';
import { ErrorService } from '../../shared/services/error.service';
import { DatepickerOptions } from 'ng2-datepicker';
import * as frLocale from 'date-fns/locale/fr';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  errors: Errors = new Errors();

  model: any = {
    namePrefix: 'Mr',
    status: 'Propreitorship',
    state: '-1',
    lineofactivity: 'Manufacturing',
    customerId: localStorage.getItem('customerId'),
    dateoffirstditributionoftermloan: new Date()
  };
  loading = false;
  states = STATES;

  datepickeroptions: DatepickerOptions = {
    displayFormat: 'MM/DD/YYYY',
    barTitleFormat: 'MMMM YYYY',
    dayNamesFormat: 'dd',
    firstCalendarDay: 0, // 0 - Sunday, 1 - Monday
    barTitleIfEmpty: 'Click to select a date'
  };


  constructor(
    private addClientService: AddClientService,
    private router: Router,
    private errorService: ErrorService
  ) { }

  ngOnInit() {
  }

  addClient() {
    this.loading = true;
    this.errors = new Errors();
    const formValues = this.model;
    console.log(formValues);
    this.addClientService.addClient(formValues).subscribe(
      data => {
        this.router.navigateByUrl('/dashboard');
      },
      err => {
        this.errors = err.error;
        this.errorService.error(this.errors);
      });
    this.loading = false;
  }

}
