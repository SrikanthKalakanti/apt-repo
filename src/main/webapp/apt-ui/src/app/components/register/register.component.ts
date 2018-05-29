import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AlertService, UserService } from "../../services/index";
import { State } from "../../models/state";
import { STATES } from "../../mocks/states";
import { ApiService } from "../../services/api.service";
import { RegisterService } from "../../services/register.service";
import { Errors } from "../../shared/models/errors.model";
import { ErrorService } from "../../shared/services/error.service";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"]
})
export class RegisterComponent implements OnInit {
  errors: Errors = new Errors();

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
    private alertService: AlertService
  ) {}

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
}
