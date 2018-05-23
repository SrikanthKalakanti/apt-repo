import { Component, OnInit } from '@angular/core';
import { ErrorService } from '../../../shared/services/error.service';
import { CustomerDetailsService } from '../../../services/customer-details.service';
import { Errors } from '../../../shared/models/errors.model';
import { ASSETDATA } from '../../../mocks/assetsList';

@Component({
  selector: 'app-asset',
  templateUrl: './asset.component.html',
  styleUrls: ['./asset.component.scss']
})
export class AssetComponent implements OnInit {

  isDataAvailable = false;
  isFormAvailable = false;
  errors: Errors = new Errors();
  assetData;

  constructor(
    private customerDetailsService: CustomerDetailsService,
    private errorService: ErrorService
  ) { }

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
    { headerName: "ID", field: "assetId", pinned: true },
    { headerName: "Name", field: "name", pinned: true },
    { headerName: "Value", field: "value" },
    { headerName: "Depreciation Rate", field: "depreciationRate" },
    { headerName: "Promoter Margin", field: "promoterMargin" }
  ];

  public onRowClicked(e) {
    if (e.event.target !== undefined) {
      let data = e.data;
      let actionType = e.event.target.getAttribute("data-action-type");

      switch (actionType) {
        case "view":
          return this.onActionViewClick(data);
        case "remove":
          return this.onActionRemoveClick(data);
        case "report":
          return this.onActionReportClick(data);
      }
    }
  }

  public onActionViewClick(data: any) {
    console.log("View action clicked", data);
    console.log(data);
    // localStorage.setItem("clientData", JSON.stringify(data));
    // this.router.navigateByUrl("/customerDetails");
  }

  public onActionRemoveClick(data: any) {
    console.log("Remove action clicked", data);
  }

  public onActionReportClick(data: any) {
    console.log("Report action clicked", data);
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

  showForm() {
    this.isFormAvailable = true;
  }

}
