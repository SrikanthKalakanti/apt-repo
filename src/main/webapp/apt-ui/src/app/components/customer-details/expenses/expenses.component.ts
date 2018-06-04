import { Component, OnInit } from "@angular/core";
import { Errors } from "../../../shared/models/errors.model";
import { CustomerDetailsService } from "../../../services/customer-details.service";
import { ErrorService } from "../../../shared/services/error.service";

@Component({
  selector: "app-expenses",
  templateUrl: "./expenses.component.html",
  styleUrls: ["./expenses.component.scss"]
})
export class ExpensesComponent implements OnInit {
  isDataAvailable = false;
  isFormAvailable = false;
  isUpdate = false;
  errors: Errors = new Errors();
  expensesData;
  model: any = {
    expenditurePer: "-1",
    cmaNomenclature: "-1",
    customerId: localStorage.getItem("customerId")
  };

  constructor(
    private customerDetailsService: CustomerDetailsService,
    private errorService: ErrorService
  ) {}

  ngOnInit() {
    this.getAllExpenses();
  }

  //this is the code for ag-Grid
  columnDefs = [
    {
      headerName: "Actions",
      suppressMenu: true,
      suppressSorting: true,
      template: `<button type="button" style="padding: 0px 5px 0px 5px !important;line-height: 1.123 !important;font-size: 15px !important;" data-action-type="view" class="btn btn-primary">
               Update
             </button>

            <button type="button" style="padding: 0px 5px 0px 5px !important;line-height: 1.123 !important;font-size: 15px !important;" data-action-type="remove" class="btn btn-danger">
               Remove
            </button>`,
      pinned: true,
      suppressSizeToFit: true,
      width: 220
    },
    { headerName: "Nomenclature", field: "nomenclature", pinned: true },
    { headerName: "CMA Nomenclature", field: "cmaNomenclature" },
    { headerName: "Periodicity", field: "expenditurePer" },
    { headerName: "Amount", field: "amountInINR" }
  ];

  private CurrencyCellRenderer(params: any) {
    var usdFormate = new Intl.NumberFormat("en-IN", {
      style: "currency",
      currency: "INR",
      minimumFractionDigits: 2
    });
    return usdFormate.format(params.value);
  }
  private PecentageCellRenderer(params: any) {
    var usdFormate = new Intl.NumberFormat("en-IN", {
      style: "percent",
      minimumFractionDigits: 2
    });
    return usdFormate.format(params.value / 100);
  }
  public onRowClicked(e) {
    if (e.event.target !== undefined) {
      let data = e.data;
      let actionType = e.event.target.getAttribute("data-action-type");

      switch (actionType) {
        case "view":
          return this.onActionViewClick(data);
        case "remove":
          return this.onActionRemoveClick(data);
      }
    }
  }

  public onActionViewClick(data: any) {
    console.log("View action clicked", data);
    console.log(data);
    this.isDataAvailable = true;
    this.isFormAvailable = true;
    this.isUpdate = true;
    this.model = data;
  }

  public onActionRemoveClick(data: any) {
    this.removeExpenses(data);
  }

  showForm() {
    this.isFormAvailable = true;
  }

  addExpenses() {
    this.errors = new Errors();
    const formValues = this.model;
    if (this.isUpdate) {
      this.customerDetailsService.updateExpense(formValues).subscribe(
        data => {
          this.errorService.success(data);
          this.isDataAvailable = true;
          this.isFormAvailable = false;
          this.isUpdate = false;
          this.getAllExpenses();
        },
        err => {
          this.isDataAvailable = false;
          this.errors = err.error;
          this.errorService.error(this.errors);
        }
      );
    } else {
      this.customerDetailsService.addExpenses(formValues).subscribe(
        data => {
          this.errorService.success(data);
          this.isDataAvailable = true;
          this.isFormAvailable = false;
          this.getAllExpenses();
        },
        err => {
          this.isDataAvailable = false;
          this.errors = err.error;
          this.errorService.error(this.errors);
        }
      );
    }
  }

  getAllExpenses() {
    // this.isDataAvailable = true;
    // this.assetData = ASSETDATA;
    this.customerDetailsService.getAllExpenses().subscribe(
      data => {
        if (data.result) {
          this.expensesData = data.result;
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

  removeExpenses(data) {
    this.customerDetailsService.removeExpenses(data).subscribe(
      data => {
        this.errorService.success(data);
        this.isDataAvailable = true;
        this.isFormAvailable = false;
        this.getAllExpenses();
      },
      err => {
        this.isDataAvailable = false;
        this.errors = err.error;
        this.errorService.error(this.errors);
      }
    );
  }
}
