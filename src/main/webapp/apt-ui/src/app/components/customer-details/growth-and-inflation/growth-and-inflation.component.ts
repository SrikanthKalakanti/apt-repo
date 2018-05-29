import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-growth-and-inflation',
  templateUrl: './growth-and-inflation.component.html',
  styleUrls: ['./growth-and-inflation.component.scss']
})
export class GrowthAndInflationComponent implements OnInit {

  isEdit = false;
  growthAndInflationData;
  model: any = {
    answer: "yes",
    customerId: localStorage.getItem("customerId")
  };

  constructor() { }

  ngOnInit() {
  }

  showForm() {
    // debugger;
    if (!this.isEdit) {
      this.isEdit = true;
      this.model = this.growthAndInflationData;
    } else {
      this.isEdit = false;
    }
  }

}
