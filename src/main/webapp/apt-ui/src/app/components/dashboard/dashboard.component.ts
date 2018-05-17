import { Component, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { Http } from "@angular/http";
import { Errors } from "../../shared/models/errors.model";
import { ErrorService } from "../../shared/services/error.service";
import { HttpClient } from "@angular/common/http";
import { Subject } from "rxjs/Subject";
import { CLIENTDETAILS } from "../../mocks/clientList";
import { DashboardService } from "../../services/dashboard.service";
import { SharedService } from "../../shared/services/shared.service";
import { AgGridNg2 } from "ag-grid-angular";

@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.css"]
})
export class DashboardComponent implements OnInit {
  errors: Errors = new Errors();
  isDataAvailable = false;
  data;
  dtOptions: any = {};
  dtTrigger: Subject<any> = new Subject();
  @ViewChild("agGrid") agGrid: AgGridNg2;

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
            </button>
            
            <button type="button" style="padding: 0px 5px 0px 5px !important;line-height: 1.123 !important;font-size: 15px !important;" data-action-type="report" class="btn btn-success">
               Report
            </button>`,
      pinned: true,
      suppressSizeToFit: true,
      width: 220
    },
    { headerName: "ID", field: "clientId", pinned: true },
    { headerName: "Name", field: "name", pinned: true },
    { headerName: "Status", field: "status" },
    { headerName: "Address", field: "address" },
    { headerName: "Mobile", field: "mobile" },
    { headerName: "Email", field: "email" },
    { headerName: "Line Of Activity", field: "lineofactivity" },
    {
      headerName: "Date of First Term Loan Ditribution",
      field: "dateoffirstditributionoftermloan"
    }
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
    this.sharedService.setClientData(data);
    localStorage.setItem("clientData", JSON.stringify(data));
    this.router.navigateByUrl("/customerDetails");
  }

  public onActionRemoveClick(data: any) {
    console.log("Remove action clicked", data);
  }

  public onActionReportClick(data: any) {
    console.log("Report action clicked", data);
  }

  constructor(
    private router: Router,
    private http: HttpClient,
    private errorService: ErrorService,
    private dashBoardService: DashboardService,
    private sharedService: SharedService
  ) {}

  ngOnInit() {
    // const self = this;
    // this.dtOptions = {
    //   pageLength: 50,
    //   order: [0, "desc"],
    //   dom: "Blfrtip",
    //   aoColumnDefs: [{ bSortable: false, aTargets: [2, 3, 4, 5, 7] }],
    //   bRetrieve: true,
    //   bootstrap: true
    //   //bStateSave: true,
    //   // buttons: [
    //   //   {
    //   //     text: 'Refresh',
    //   //     key: '1',
    //   //     action: function (e, dt, node, config) {
    //   //       dt.destroy();
    //   //       self.data = [];
    //   //       self.ngOnInit();
    //   //     }
    //   //   }
    //   // ]
    // };

    this.getAllClientList();
  }

  addClient() {
    this.router.navigateByUrl("/addclient");
  }

  openCustomerDetails(data) {
    console.log(data);
    this.sharedService.setClientData(data);
    localStorage.setItem("clientData", JSON.stringify(data));
    this.router.navigateByUrl("/customerDetails");
  }

  getAllClientList() {
    this.isDataAvailable = true;
    //this.data = CLIENTDETAILS;
    this.dashBoardService.getClientList().subscribe(
      data => {
        if (data.result) {
          this.data = data.result;
          this.isDataAvailable = true;
        } else {
          this.isDataAvailable = false;
        }
        this.dtTrigger.next();
      },
      err => {
        this.isDataAvailable = false;
        this.errors = err.error;
        this.errorService.error(this.errors);
      }
    );
  }
}
