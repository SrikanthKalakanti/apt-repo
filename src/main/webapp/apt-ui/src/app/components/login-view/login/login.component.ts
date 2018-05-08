import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Errors } from '../../../shared/models/errors.model';
import { ErrorService } from '../../../shared/services/error.service';
import { UserService } from '../../../services';
import { Router } from '@angular/router';

interface ItemsResponse {
  obj: string[];
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  errors: Errors = new Errors();

  constructor(
    private fb: FormBuilder,
    private errorService: ErrorService,
    private userService: UserService,
    private router: Router
  ) { 
    this.form = this.fb.group({
      'userName': ['', Validators.required],
      'password': ['', Validators.required],
    });
  }

  ngOnInit() {
  }

  login() {
    this.errors = new Errors();
    const credentials = this.form.value;

    // Clear out all Local Storage before submitting POST request
    localStorage.clear();
    // Submit POST request and redirect upon successful login
    this.userService.attemptAuth(credentials)
      .subscribe(
        data => {
          this.router.navigateByUrl('/SCC/fileupload');
        },
        err => {
          this.errors = err.error;
          this.errorService.error(this.errors);
        });
  }

  // Form getter for userName
  get userName() {
    return this.form.get('userName');
  }

  // Form getter for password
  get password() {
    return this.form.get('password');
  }

}
