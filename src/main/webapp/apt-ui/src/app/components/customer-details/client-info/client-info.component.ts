import { Component, OnInit } from "@angular/core";
import { STATES } from '../../../mocks/states';
import { DatepickerOptions } from "ng2-datepicker";
import * as frLocale from 'date-fns/locale/fr';
import { Errors } from "../../../shared/models/errors.model";
import { CustomerDetailsService } from "../../../services/customer-details.service";
import { ErrorService } from "../../../shared/services/error.service";

@Component({
  selector: "app-client-info",
  templateUrl: "./client-info.component.html",
  styleUrls: ["./client-info.component.scss"]
})
export class ClientInfoComponent implements OnInit {
  isFormAvailable = false;
  loading = false;
  errors: Errors = new Errors();
  clientInfoData = {
    state: "-1",
    dateoffirstditributionoftermloan: new Date()
  };
  states = STATES;

  constructor(
    private errorService: ErrorService,
    private customerDetailsService: CustomerDetailsService
  ) {}

  datepickeroptions: DatepickerOptions = {
    displayFormat: 'MM/DD/YYYY',
    barTitleFormat: 'MMMM YYYY',
    dayNamesFormat: 'dd',
    firstCalendarDay: 0, // 0 - Sunday, 1 - Monday
    barTitleIfEmpty: 'Click to select a date'
  };

  ngOnInit() {
    this.clientInfoData = JSON.parse(localStorage.getItem("clientData"));
    this.clientInfoData.dateoffirstditributionoftermloan = new Date(this.clientInfoData.dateoffirstditributionoftermloan);
    console.log(this.clientInfoData);
  }

  showForm() {
    if (!this.isFormAvailable) {
      this.isFormAvailable = true;
      this.clientInfoData.dateoffirstditributionoftermloan = new Date(this.clientInfoData.dateoffirstditributionoftermloan);
    } else {
      this.isFormAvailable = false;
    }
  }
  updateClient() {
    this.loading = true;
    this.errors = new Errors();
    const formValues = this.clientInfoData;
    console.log(formValues);
    this.customerDetailsService.updateClient(formValues).subscribe(
      data => {
        this.isFormAvailable = false;
        this.errorService.success(data);
      },
      err => {
        this.errors = err.error;
        this.errorService.error(this.errors);
      }
    );
    this.loading = false;
  }
  cancel() {
    this.isFormAvailable = false;
  }
}
