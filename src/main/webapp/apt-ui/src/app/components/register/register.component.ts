import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService, UserService } from '../../services/index';
import { State } from '../../models/state';
import { STATES } from '../../mocks/states';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  model: any = {
    namePrefix: "Mr"
  };
    loading = false;
    states = STATES;

    constructor(
        private router: Router,
        private userService: UserService,
        private alertService: AlertService) { }

    register() {
        this.loading = true;
        this.userService.create(this.model)
            .subscribe(
                data => {
                    this.alertService.success('Registration successful', true);
                    this.router.navigate(['/login']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }

  ngOnInit() {
  }

}
