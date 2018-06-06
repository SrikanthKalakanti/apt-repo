import { Component, OnInit } from "@angular/core";
import { STATES } from '../../../mocks/states';
import { DatepickerOptions } from "ng2-datepicker";
import * as frLocale from 'date-fns/locale/fr';

@Component({
  selector: "app-client-info",
  templateUrl: "./client-info.component.html",
  styleUrls: ["./client-info.component.scss"]
})
export class ClientInfoComponent implements OnInit {
  isFormAvailable = false;
  clientInfoData = {
    state: "-1",
    dateoffirstditributionoftermloan: new Date()
  };
  states = STATES;

  constructor() {}

  datepickeroptions: DatepickerOptions = {
    displayFormat: 'MM/DD/YYYY',
    barTitleFormat: 'MMMM YYYY',
    dayNamesFormat: 'dd',
    firstCalendarDay: 0, // 0 - Sunday, 1 - Monday
    barTitleIfEmpty: 'Click to select a date'
  };

  ngOnInit() {
    this.clientInfoData = JSON.parse(localStorage.getItem("clientData"));
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
}
