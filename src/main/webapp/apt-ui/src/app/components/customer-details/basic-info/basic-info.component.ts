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
  basicData;

  model: any = {
    nameOfTheBussiness: "",
    status: "-1",
    state: "-1",
    customerId: localStorage.getItem("customerId")
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
    // console.log(formValues);
    // this.isEdit = false;
    // this.loading = false;
    this.customerDetailsService.updateBasicInput().subscribe(
      data => {
        this.errorService.success(JSON.parse(data));
        this.isEdit = false;
        this.loading = false;
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
    this.customerDetailsService.addBasicInfo().subscribe(
      data => {
        this.errorService.success(JSON.parse(data));
        this.isEdit = false;
        this.loading = false;
      },
      err => {
        this.isDataAvailable = false;
        this.isEdit = false;
        this.errors = err.error;
        this.errorService.error(this.errors);
      }
    );
  }
}
