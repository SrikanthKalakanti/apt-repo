import { Component, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';

import { Errors } from '../../models/errors.model';
import { ErrorService } from '../../services/error.service';

@Component({
  selector: 'app-list-errors',
  templateUrl: './list-errors.component.html',
  styleUrls: ['./list-errors.component.css']
})
export class ListErrorsComponent implements OnDestroy {
  private subscription: Subscription;
  formattedError: string;
  errorList: Errors;

  constructor(private errorService: ErrorService) {
    // subscribe to alert messages
    this.subscription = errorService.getMessage()
      .subscribe(errorList => { this.errorList = errorList; });
  }

  ngOnDestroy(): void {
    // unsubscribe on destroy to prevent memory leaks
    this.subscription.unsubscribe();
  }
}
