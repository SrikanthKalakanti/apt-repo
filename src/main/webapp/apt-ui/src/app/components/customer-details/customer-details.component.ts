import { Component, OnInit } from "@angular/core";
import { SharedService } from "../../shared/services/shared.service";
import { BasicInfoComponent } from "./basic-info/basic-info.component";
import { Observable } from "rxjs/Observable";
import { AssetComponent } from "./asset/asset.component";
import { ExpensesComponent } from "./expenses/expenses.component";
import { GrowthAndInflationComponent } from "./growth-and-inflation/growth-and-inflation.component";
import { SellingPriceComponent } from "./selling-price/selling-price.component";
import { ClientInfoComponent } from "./client-info/client-info.component";

@Component({
  selector: "app-customer-details",
  templateUrl: "./customer-details.component.html",
  styleUrls: ["./customer-details.component.css"]
})
export class CustomerDetailsComponent implements OnInit {
  data;

  basicInfoComponent = BasicInfoComponent;
  assetComponent = AssetComponent;
  expensesComponent = ExpensesComponent;
  growthAndInflation = GrowthAndInflationComponent;
  sellingPrice = SellingPriceComponent;
  cilentInfo = ClientInfoComponent;

  //bulkUploadHistoryComponent = BulkUploadHistoryComponent;

  constructor(private sharedService: SharedService) {
    this.data = this.sharedService.getClientData();
    this.data = JSON.parse(localStorage.getItem("clientData"));
    console.log(this.data);
  }

  ngOnInit() {}
}
