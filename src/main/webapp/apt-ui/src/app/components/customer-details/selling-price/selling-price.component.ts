import { Component, OnInit } from "@angular/core";
import { Errors } from "../../../shared/models/errors.model";
import { CustomerDetailsService } from "../../../services/customer-details.service";
import { ErrorService } from "../../../shared/services/error.service";

@Component({
  selector: "app-selling-price",
  templateUrl: "./selling-price.component.html",
  styleUrls: ["./selling-price.component.scss"]
})
export class SellingPriceComponent implements OnInit {
  isEdit = false;
  isDataAvailable = false;
  model: any = {
    nomenclature: "",
    cmaNomenclature: "Selling Price",
    expenditurePer: "Unit",
    customerId: localStorage.getItem("customerId")
  };
  sellingPriceData;
  isUpdate = false;
  errors: Errors = new Errors();

  constructor(
    private customerDetailsService: CustomerDetailsService,
    private errorService: ErrorService
  ) {}

  ngOnInit() {}

  showForm() {
    // debugger;
    if (!this.isEdit) {
      this.isEdit = true;
      // this.model = this.sellingPriceData;
    } else {
      this.isEdit = false;
    }
  }

  addExpenses() {
    this.errors = new Errors();
    const formValues = this.model;
    // if (this.isUpdate) {
    //   this.customerDetailsService.updateExpense(formValues).subscribe(
    //     data => {
    //       this.errorService.success(data);
    //       this.isDataAvailable = true;
    //       // this.isFormAvailable = false;
    //       this.isUpdate = false;
    //     },
    //     err => {
    //       this.isDataAvailable = false;
    //       this.errors = err.error;
    //       this.errorService.error(this.errors);
    //     }
    //   );
    // } else {
    console.log(formValues);
    this.customerDetailsService.addExpenses(formValues).subscribe(
      data => {
        this.errorService.success(data);
        this.isDataAvailable = true;
        // this.isFormAvailable = false;
        // this.getAllExpenses();
      },
      err => {
        this.isDataAvailable = false;
        this.errors = err.error;
        this.errorService.error(this.errors);
      }
    );
    // }
  }

  cancel() {
    this.isEdit = false;
  }
}
