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
  errors: Errors = new Errors();
  basicData;

  model: any = {
    status: "-1",
    state: "-1",
    customerId: localStorage.getItem("customerId")
  };

  constructor(
    private customerDetailsService: CustomerDetailsService,
    private errorService: ErrorService
  ) {}

  ngOnInit() {
    this.getBasicInput();
  }

  showForm() {
    if(!this.isEdit){
      this.isEdit = true;
      this.model = this.basicData;
    } else{
      this.isEdit = false;
    }
  }

  getBasicInput() {
    this.isDataAvailable = true;
    this.basicData = BASICINFO;
    console.log(this.basicData);
    // this.customerDetailsService.getBasicInput().subscribe(
    //   data => {
    //     if (data.result) {
    //       this.basicData = data.result;
    //       this.isDataAvailable = true;
    //     } else {
    //       this.isDataAvailable = false;
    //     }
    //     //this.dtTrigger.next();
    //   },
    //   err => {
    //     this.isDataAvailable = false;
    //     this.errors = err.error;
    //     this.errorService.error(this.errors);
    //   }
    // );
  }
}
