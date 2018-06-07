import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AlertService, UserService } from "../../services/index";
import { State } from "../../models/state";
import { STATES } from "../../mocks/states";
import { ApiService } from "../../services/api.service";
import { RegisterService } from "../../services/register.service";
import { Errors } from "../../shared/models/errors.model";
import { ErrorService } from "../../shared/services/error.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"]
})
export class RegisterComponent implements OnInit {
  errors: Errors = new Errors();

  registrationForm: FormGroup;

  model: any = {
    namePrefix: "Mr",
    status: "Individual",
    state: "-1",
    profession: "CA"
  };
  loading = false;
  states = STATES;

  constructor(
    private router: Router,
    private errorService: ErrorService,
    private registerService: RegisterService,
    private alertService: AlertService,
    private fb: FormBuilder
  ) {
    this.registrationForm = this.fb.group({
      namePrefix: ["Mr", Validators.required],
      firstName: ["", Validators.required],
      lastName: ["", Validators.required],
      status: ["Individual", Validators.required],
      doorNumber: ["", Validators.required],
      roadNumber: ["", Validators.nullValidator],
      locality: ["", Validators.nullValidator],
      landmark: ["", Validators.nullValidator],
      town: ["", Validators.nullValidator],
    });
  }

  register() {
    this.loading = true;
    this.errors = new Errors();
    const formValues = this.model;
    console.log(formValues);
    this.registerService.register(formValues).subscribe(
      data => {
        this.router.navigateByUrl("/login");
      },
      err => {
        this.errors = err.error;
        this.errorService.error(this.errors);
      }
    );
    this.loading = false;
  }

  ngOnInit() {}

  // Form getter for namePrefix
  get namePrefix() {
    return this.registrationForm.get("namePrefix");
  }

  // Form getter for firstName
  get firstName() {
    return this.registrationForm.get("firstName");
  }
  get lastName() {
    return this.registrationForm.get("lastName");
  }
  get status() {
    return this.registrationForm.get("status");
  }
  get doorNumber() {
    return this.registrationForm.get("doorNumber");
  }
  get roadNumber() {
    return this.registrationForm.get("roadNumber");
  }
  get locality() {
    return this.registrationForm.get("locality");
  }
  get landmark() {
    return this.registrationForm.get("landmark");
  }
  get town() {
    return this.registrationForm.get("town");
  }
}
