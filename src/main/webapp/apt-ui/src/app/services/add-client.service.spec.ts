import { TestBed, inject } from '@angular/core/testing';

import { AddClientService } from './add-client.service';

describe('AddClientService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AddClientService]
    });
  });

  it('should be created', inject([AddClientService], (service: AddClientService) => {
    expect(service).toBeTruthy();
  }));
});
