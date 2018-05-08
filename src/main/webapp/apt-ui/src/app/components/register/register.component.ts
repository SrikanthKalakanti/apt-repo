import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService, UserService } from '../../services/index';
import { State } from '../../models/state';
import { STATES } from '../../mocks/states';
import { ApiService } from '../../services/api.service';
import { RegisterService } from '../../services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  model: any = {
    namePrefix: 'Mr',
    status: 'Individual',
    state: '-1',
    profession: 'CA'
  };
    loading = false;
    states = STATES;

    constructor(
        private router: Router,
        private registerService: RegisterService,
        private alertService: AlertService) { }

    register() {
        this.loading = true;
        const formValues = this.model;
        console.log(formValues);
        this.registerService.register(formValues);
        this.loading = false;
    }

  ngOnInit() {
  }

}
