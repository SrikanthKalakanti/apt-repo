import { Component, OnInit } from '@angular/core';
import { CustomerDetailsService } from '../../../services/customer-details.service';
import { ErrorService } from '../../../shared/services/error.service';
import { Errors } from '../../../shared/models/errors.model';

@Component({
  selector: 'app-growth-and-inflation',
  templateUrl: './growth-and-inflation.component.html',
  styleUrls: ['./growth-and-inflation.component.scss']
})
export class GrowthAndInflationComponent implements OnInit {

  growthAndInflationData: any = {
    answer1: "yes",
    answer2: "no",
    answer3: "no",
    customerId: localStorage.getItem("customerId")
  };
  loading = false;
  isEdit = false;
  isDataAvailable = false;
  errors: Errors = new Errors();

  constructor(
    private customerDetailsService: CustomerDetailsService,
    private errorService: ErrorService
  ) { }

  ngOnInit() {
    this.getGrowthAndInflation();
  }

  getGrowthAndInflation() {
    this.customerDetailsService.getGrowthAndInflation().subscribe(
      data => {
        if (data.result) {
          this.growthAndInflationData = data.result;
          this.growthAndInflationData.growthAndInflation = this.growthAndInflationData.answer1;
          this.growthAndInflationData.numberOfYears = Math.floor(this.growthAndInflationData.numberOfYears);
          debugger;
          if(this.growthAndInflationData.growthAndInflation === 'yes') {
            this.growthAndInflationData.growthForAllYears = this.growthAndInflationData.array1.split("$")[0];
            this.growthAndInflationData.inflationForAllYears = this.growthAndInflationData.array2.split("$")[0];
          } 
          if(this.growthAndInflationData.answer2 === 'yes') {
            for(var i=1; i<=this.growthAndInflationData.numberOfYears; i++) {
              this.growthAndInflationData["growthYear" + i] = this.growthAndInflationData.array1.split("$")[i-1];
            }
          } 
          if(this.growthAndInflationData.answer3 === 'yes') {
            for(var i=1; i<=this.growthAndInflationData.numberOfYears; i++) {
              this.growthAndInflationData["inflationYear" + i] = this.growthAndInflationData.array2.split("$")[i-1];
            }
          }
          console.log(this.growthAndInflationData);          
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

  addGrowthAndInflation() {
    this.errors = new Errors();
    const formValues = this.growthAndInflationData;
    formValues.answer1 = formValues.growthAndInflation;
    if(formValues.answer1 === 'yes') {
      formValues.numberOfYears = 8;
      formValues.array1 = '';
      formValues.array2 = '';
      var growthString = '';
      var inflationString = '';
      debugger;
      if(formValues.answer2 === 'yes'){
        growthString = growthString + formValues.growthYear1 + '$' + formValues.growthYear2 + '$' + formValues.growthYear3 + '$' + formValues.growthYear4 + '$' + formValues.growthYear5 + '$' + formValues.growthYear6 + '$' + formValues.growthYear7 + '$' + formValues.growthYear8;
        formValues.array1 = growthString;
      } 
      if(formValues.answer3 === 'yes') {
        inflationString = inflationString + formValues.inflationYear1 + '$' + formValues.inflationYear2 + '$' + formValues.inflationYear3 + '$' + formValues.inflationYear4 + '$' + formValues.inflationYear5 + '$' + formValues.inflationYear6 + '$' + formValues.inflationYear7 + '$' + formValues.inflationYear8;
        formValues.array2 = inflationString;
      } 
      if(formValues.answer2 === 'no' && formValues.answer3 === 'no') {
        for(var i=0; i<formValues.numberOfYears; i++) {
          if(i === formValues.numberOfYears-1){
            growthString = growthString + formValues.growthForAllYears;
            inflationString = inflationString + formValues.inflationForAllYears;
          } else {
            growthString = growthString + formValues.growthForAllYears + '$';
            inflationString = inflationString + formValues.inflationForAllYears + '$';
          }        
        }
        formValues.array1 = growthString;
        formValues.array2 = inflationString;
      } else if(formValues.answer2 === 'yes' && formValues.answer3 === 'no') {
        for(var i=0; i<formValues.numberOfYears; i++) {
          if(i === formValues.numberOfYears-1){
            //growthString = growthString + formValues.growthForAllYears;
            inflationString = inflationString + formValues.inflationForAllYears;
          } else {
            //growthString = growthString + formValues.growthForAllYears + '$';
            inflationString = inflationString + formValues.inflationForAllYears + '$';
          }        
        }
        //formValues.array1 = growthString;
        formValues.array2 = inflationString;
      } else if(formValues.answer2 === 'no' && formValues.answer3 === 'yes') {
        for(var i=0; i<formValues.numberOfYears; i++) {
          if(i === formValues.numberOfYears-1){
            growthString = growthString + formValues.growthForAllYears;
            //inflationString = inflationString + formValues.inflationForAllYears;
          } else {
            growthString = growthString + formValues.growthForAllYears + '$';
            //inflationString = inflationString + formValues.inflationForAllYears + '$';
          }        
        }
        formValues.array1 = growthString;
        //formValues.array2 = inflationString;
      }
    } else {
      formValues.numberOfYears = 0;
    }
    delete formValues.growthAndInflation;
    delete formValues.growthForAllYears;
    delete formValues.inflationForAllYears;
    delete formValues.customerId;
    delete formValues.inflationYear1;
    delete formValues.inflationYear2;
    delete formValues.inflationYear3;
    delete formValues.inflationYear4;
    delete formValues.inflationYear5;
    delete formValues.inflationYear6;
    delete formValues.inflationYear7;
    delete formValues.inflationYear8;
    delete formValues.growthYear1;
    delete formValues.growthYear2;
    delete formValues.growthYear3;
    delete formValues.growthYear4;
    delete formValues.growthYear5;
    delete formValues.growthYear6;
    delete formValues.growthYear7;
    delete formValues.growthYear8;
    console.log(JSON.stringify(formValues));
    this.customerDetailsService.addGrowthAndInflation(formValues).subscribe(
      data => {
        this.errorService.success(data);
        this.isEdit = false;
        this.loading = false;
        this.isDataAvailable = true;
        this.growthAndInflationData.growthAndInflation = formValues.answer1;
        if(this.growthAndInflationData.growthAndInflation === 'yes') {
            this.growthAndInflationData.growthForAllYears = this.growthAndInflationData.array1.split("$")[0];
            this.growthAndInflationData.inflationForAllYears = this.growthAndInflationData.array2.split("$")[0];
          } 
          if(this.growthAndInflationData.answer2 === 'yes') {
            for(var i=1; i<=this.growthAndInflationData.numberOfYears; i++) {
              this.growthAndInflationData["growthYear" + i] = this.growthAndInflationData.array1.split("$")[i-1];
            }
          } 
          if(this.growthAndInflationData.answer3 === 'yes') {
            for(var i=1; i<=this.growthAndInflationData.numberOfYears; i++) {
              this.growthAndInflationData["inflationYear" + i] = this.growthAndInflationData.array2.split("$")[i-1];
            }
          }
      },
      err => {
        this.isDataAvailable = false;
        this.isEdit = false;
        this.errors = err.error;
        this.errorService.error(this.errors);
      }
    );
  }

  updateGrowthAndInflation() {
    this.errors = new Errors();
    const formValues = this.growthAndInflationData;
    formValues.answer1 = formValues.growthAndInflation;
    if(formValues.answer1 === 'yes') {
      formValues.numberOfYears = 8;
      formValues.array1 = '';
      formValues.array2 = '';
      var growthString = '';
      var inflationString = '';
      if(formValues.answer2 === 'yes'){
        growthString = growthString + formValues.growthYear1 + '$' + formValues.growthYear2 + '$' + formValues.growthYear3 + '$' + formValues.growthYear4 + '$' + formValues.growthYear5 + '$' + formValues.growthYear6 + '$' + formValues.growthYear7 + '$' + formValues.growthYear8;
        formValues.array1 = growthString;
      } 
      if(formValues.answer3 === 'yes') {
        inflationString = inflationString + formValues.inflationYear1 + '$' + formValues.inflationYear2 + '$' + formValues.inflationYear3 + '$' + formValues.inflationYear4 + '$' + formValues.inflationYear5 + '$' + formValues.inflationYear6 + '$' + formValues.inflationYear7 + '$' + formValues.inflationYear8;
        formValues.array2 = inflationString;
      } 
      if(formValues.answer2 === 'no' && formValues.answer3 === 'no') {
        for(var i=0; i<formValues.numberOfYears; i++) {
          if(i === formValues.numberOfYears-1){
            growthString = growthString + formValues.growthForAllYears;
            inflationString = inflationString + formValues.inflationForAllYears;
          } else {
            growthString = growthString + formValues.growthForAllYears + '$';
            inflationString = inflationString + formValues.inflationForAllYears + '$';
          }        
        }
        formValues.array1 = growthString;
        formValues.array2 = inflationString;
      } else if(formValues.answer2 === 'yes' && formValues.answer3 === 'no') {
        for(var i=0; i<formValues.numberOfYears; i++) {
          if(i === formValues.numberOfYears-1){
            //growthString = growthString + formValues.growthForAllYears;
            inflationString = inflationString + formValues.inflationForAllYears;
          } else {
            //growthString = growthString + formValues.growthForAllYears + '$';
            inflationString = inflationString + formValues.inflationForAllYears + '$';
          }        
        }
        //formValues.array1 = growthString;
        formValues.array2 = inflationString;
      } else if(formValues.answer2 === 'no' && formValues.answer3 === 'yes') {
        for(var i=0; i<formValues.numberOfYears; i++) {
          if(i === formValues.numberOfYears-1){
            growthString = growthString + formValues.growthForAllYears;
            //inflationString = inflationString + formValues.inflationForAllYears;
          } else {
            growthString = growthString + formValues.growthForAllYears + '$';
            //inflationString = inflationString + formValues.inflationForAllYears + '$';
          }        
        }
        formValues.array1 = growthString;
        //formValues.array2 = inflationString;
      }
    } else {
      formValues.numberOfYears = 0;
    }
    delete formValues.growthAndInflation;
    delete formValues.growthForAllYears;
    delete formValues.inflationForAllYears;
    delete formValues.customerId;
    delete formValues.inflationYear1;
    delete formValues.inflationYear2;
    delete formValues.inflationYear3;
    delete formValues.inflationYear4;
    delete formValues.inflationYear5;
    delete formValues.inflationYear6;
    delete formValues.inflationYear7;
    delete formValues.inflationYear8;
    delete formValues.growthYear1;
    delete formValues.growthYear2;
    delete formValues.growthYear3;
    delete formValues.growthYear4;
    delete formValues.growthYear5;
    delete formValues.growthYear6;
    delete formValues.growthYear7;
    delete formValues.growthYear8;
    console.log(JSON.stringify(formValues));
    this.customerDetailsService.updateGrowthAndInflation(formValues).subscribe(
      data => {
        this.errorService.success(data);
        this.isEdit = false;
        this.loading = false;
        this.isDataAvailable = true;
        this.growthAndInflationData.growthAndInflation = formValues.answer1;
        if(this.growthAndInflationData.growthAndInflation === 'yes') {
            this.growthAndInflationData.growthForAllYears = this.growthAndInflationData.array1.split("$")[0];
            this.growthAndInflationData.inflationForAllYears = this.growthAndInflationData.array2.split("$")[0];
          } 
          if(this.growthAndInflationData.answer2 === 'yes') {
            for(var i=1; i<=this.growthAndInflationData.numberOfYears; i++) {
              this.growthAndInflationData["growthYear" + i] = this.growthAndInflationData.array1.split("$")[i-1];
            }
          } 
          if(this.growthAndInflationData.answer3 === 'yes') {
            for(var i=1; i<=this.growthAndInflationData.numberOfYears; i++) {
              this.growthAndInflationData["inflationYear" + i] = this.growthAndInflationData.array2.split("$")[i-1];
            }
          }
                    
      },
      err => {
        this.isDataAvailable = false;
        this.isEdit = false;
        this.errors = err.error;
        this.errorService.error(this.errors);
      }
    );
  }

  cancel() {
    this.isEdit = false;
  }

  showForm() {
    // debugger;
    if (!this.isEdit) {
      this.isEdit = true;
      this.growthAndInflationData.growthAndInflation = this.growthAndInflationData.answer1;
      // this.growthAndInflationData = this.growthAndInflationData;
    } else {
      this.isEdit = false;
    }
  }

}
