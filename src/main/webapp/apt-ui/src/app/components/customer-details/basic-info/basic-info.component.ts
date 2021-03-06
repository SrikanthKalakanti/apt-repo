import { Component, OnInit } from "@angular/core";
import { CustomerDetailsService } from "../../../services/customer-details.service";
import { ErrorService } from "../../../shared/services/error.service";
import { Errors } from "../../../shared/models/errors.model";
import { BASICINFO } from "../../../mocks/basicInfo";

@Component({
  selector: "app-basic-info",
  templateUrl: "./basic-info.component.html",
  styleUrls: ["./basic-info.component.css"]
})
export class BasicInfoComponent implements OnInit {
  isDataAvailable = false;
  isEdit = false;
  loading = false;
  errors: Errors = new Errors();
  minDate = new Date();
  basicData;

  model: any = {
    nameOfTheBussiness: "",
    status: -1,
    state: "-1",
    paymentDate: 0,
    fixedExpensesPerMonth: 0,
    productionPerMonthInUnits: 0,
    numberOfDaysInAMonth: 0,
    customerId: localStorage.getItem("customerId"),
    termLoanFirstDisbursementDate: JSON.parse(localStorage.getItem("clientData")).termLoanFirstDisbursementDate
  };

  constructor(
    private customerDetailsService: CustomerDetailsService,
    private errorService: ErrorService
  ) {
    if (!this.basicData) {
      this.basicData = {};
    }
    if (!this.model) {
      this.model = {};
    }
  }

  ngOnInit() {
    this.getBasicInput();
  }

  showForm() {
    // debugger;
    var termLoanDate = JSON.parse(localStorage.getItem("clientData"));
    console.log(termLoanDate);
    this.model.termLoanFirstDisbursementDate = new Date(JSON.parse(localStorage.getItem("clientData")).dateoffirstditributionoftermloan);
    this.setMinDate(this.model.termLoanFirstDisbursementDate);
    console.log(this.model.termLoanFirstDisbursementDate);
    if (!this.isEdit) {
      this.isEdit = true;
      this.model = this.basicData;
    } else {
      this.isEdit = false;
    }
  }

  cancel() {
    this.isEdit = false;
  }
  setMinDate(value) {
    this.minDate = value;
  }

  onBlurMethod(evn) {
    console.log(evn);
  }

  getBasicInput() {
    this.isDataAvailable = true;
    // this.basicData = BASICINFO;
    // console.log(this.basicData);
    this.customerDetailsService.getBasicInput().subscribe(
      data => {
        if (data.result) {
          this.basicData = data.result;
          this.isDataAvailable = true;
        } else {
          this.isDataAvailable = false;
        }
      },
      err => {
        this.isDataAvailable = false;
        this.errors = err.error;
        this.errorService.error(this.errors);
      }
    );
  }

  updateClient() {
    this.loading = true;
    this.errors = new Errors();
    const formValues = this.model;
    formValues.termLoanDisbursement = formValues.termLoanFirstDisbursementDate;
    formValues.businessCommencement = formValues.businessCommencementDate;
    formValues.status = parseInt(formValues.status);
    formValues.termLoanDisbursement = this.formatDate(
      formValues.termLoanDisbursement
    );
    formValues.termLoanFirstDisbursementDate = this.formatDate(
      formValues.termLoanFirstDisbursementDate
    );
    formValues.businessCommencement = this.formatDate(
      formValues.businessCommencement
    );
    formValues.businessCommencementDate = this.formatDate(
      formValues.businessCommencementDate
    );
    delete formValues.businessCommencementDate;
    delete formValues.termLoanFirstDisbursementDate;
    this.customerDetailsService.updateBasicInput(formValues).subscribe(
      data => {
        this.errorService.success(data);
        this.isEdit = false;
        this.loading = false;
        this.isDataAvailable = true;
        this.basicData.termLoanFirstDisbursementDate =
          formValues.termLoanDisbursement;
        this.basicData.businessCommencementDate =
          formValues.businessCommencement;
      },
      err => {
        this.isDataAvailable = false;
        this.isEdit = false;
        this.errors = err.error;
        this.errorService.error(this.errors);
      }
    );
  }
  addBasicInfo() {
    this.errors = new Errors();
    const formValues = this.model;
    formValues.termLoanDisbursement = formValues.termLoanFirstDisbursementDate;
    formValues.businessCommencement = formValues.businessCommencementDate;
    formValues.status = parseInt(formValues.status);
    formValues.termLoanDisbursement = this.formatDate(
      formValues.termLoanDisbursement
    );
    formValues.termLoanFirstDisbursementDate = this.formatDate(
      formValues.termLoanFirstDisbursementDate
    );
    formValues.businessCommencement = this.formatDate(
      formValues.businessCommencement
    );
    formValues.businessCommencementDate = this.formatDate(
      formValues.businessCommencementDate
    );
    delete formValues.businessCommencementDate;
    delete formValues.termLoanFirstDisbursementDate;
    this.customerDetailsService.addBasicInfo(formValues).subscribe(
      data => {
        this.errorService.success(data);
        this.isEdit = false;
        this.loading = false;
        this.isDataAvailable = true;
      },
      err => {
        this.isDataAvailable = false;
        this.isEdit = false;
        this.errors = err.error;
        this.errorService.error(this.errors);
      }
    );
  }

  formatDate(value) {
    var d = new Date(value);
    var dt = d.getDate();
    var mn = d.getMonth();
    var mn = mn + 1;
    var yyyy = d.getFullYear();
    return dt + "/" + mn + "/" + yyyy;
  }
}
