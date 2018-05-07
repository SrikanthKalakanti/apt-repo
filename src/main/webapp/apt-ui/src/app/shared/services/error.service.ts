import { Injectable } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';

import { Errors } from '../models/errors.model';

@Injectable()
export class ErrorService {

  private subject = new Subject<any>();
  private keepAfterNavigationChange = false;

  constructor(private router: Router) {
    // clear alert message on route change
    router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (this.keepAfterNavigationChange) {
          // only keep for a single location change
          this.keepAfterNavigationChange = false;
        } else {
          // clear alert
          this.subject.next();
        }
      }
    });
  }

  success(errorList: Errors, keepAfterNavigationChange = false) {
    this.keepAfterNavigationChange = keepAfterNavigationChange;
    this.subject.next(
      {
        type: 'success',
        description: (errorList.description) ? errorList.description : errorList.status
      }
    );
  }

  error(errorList: Errors, keepAfterNavigationChange = false) {
    this.keepAfterNavigationChange = keepAfterNavigationChange;
    this.subject.next(
      {
        type: 'error',
        description: `${errorList.status}: ${errorList.description}`
      }
    );
  }

  getMessage(): Observable<any> {
    return this.subject.asObservable();
  }

}
