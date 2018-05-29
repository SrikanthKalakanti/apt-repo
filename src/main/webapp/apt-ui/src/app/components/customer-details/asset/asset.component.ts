import { Component, OnInit } from "@angular/core";
import { ErrorService } from "../../../shared/services/error.service";
import { CustomerDetailsService } from "../../../services/customer-details.service";
import { Errors } from "../../../shared/models/errors.model";
import { ASSETDATA } from "../../../mocks/assetsList";

@Component({
  selector: "app-asset",
  templateUrl: "./asset.component.html",
  styleUrls: ["./asset.component.scss"]
})
export class AssetComponent implements OnInit {
  isDataAvailable = false;
  isFormAvailable = false;
  isUpdate = false;
  errors: Errors = new Errors();
  assetData;

  model: any = {
    name: "",
    customerId: localStorage.getItem("customerId")
  };

  constructor(
    private customerDetailsService: CustomerDetailsService,
    private errorService: ErrorService
  ) {}

  ngOnInit() {
    this.getAllAssets();
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
    { headerName: "Name", field: "name", pinned: true, editable: true },
    {
      headerName: "Value",
      field: "value",
      editable: true,
      cellRenderer: this.CurrencyCellRenderer
    },
    {
      headerName: "Depreciation Rate",
      field: "depreciationRate",
      editable: true,
      cellRenderer: this.PecentageCellRenderer
    },
    { headerName: "Promoter Margin", field: "promoterMargin", editable: true }
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
    this.removeAsset(data);
  }

  getAllAssets() {
    // this.isDataAvailable = true;
    // this.assetData = ASSETDATA;
    this.customerDetailsService.getAllAssets().subscribe(
      data => {
        if (data.result) {
          this.assetData = data.result;
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

  addAsset() {
    this.errors = new Errors();
    const formValues = this.model;
    if (this.isUpdate) {
      this.customerDetailsService.updateAsset(formValues).subscribe(
        data => {
          this.errorService.success(data);
          this.isDataAvailable = true;
          this.isFormAvailable = false;
          this.isUpdate = false;
          this.getAllAssets();
        },
        err => {
          this.isDataAvailable = false;
          this.errors = err.error;
          this.errorService.error(this.errors);
        }
      );
    } else {
      this.customerDetailsService.addAsset(formValues).subscribe(
        data => {
          this.errorService.success(data);
          this.isDataAvailable = true;
          this.isFormAvailable = false;
          this.getAllAssets();
        },
        err => {
          this.isDataAvailable = false;
          this.errors = err.error;
          this.errorService.error(this.errors);
        }
      );
    }
  }

  removeAsset(data) {
    this.customerDetailsService.removeAsset(data).subscribe(
      data => {
        this.errorService.success(data);
        this.isDataAvailable = true;
        this.isFormAvailable = false;
        this.getAllAssets();
      },
      err => {
        this.isDataAvailable = false;
        this.errors = err.error;
        this.errorService.error(this.errors);
      }
    );
  }

  showForm() {
    this.isFormAvailable = true;
  }

  cancel() {
    this.isFormAvailable = false;
    // this.getAllAssets();
  }
}
