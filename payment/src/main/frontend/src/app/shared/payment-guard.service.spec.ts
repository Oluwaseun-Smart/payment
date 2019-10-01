import { TestBed, inject } from '@angular/core/testing';

import { PaymentGuardService } from './payment-guard.service';

describe('PaymentGuardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PaymentGuardService]
    });
  });

  it('should be created', inject([PaymentGuardService], (service: PaymentGuardService) => {
    expect(service).toBeTruthy();
  }));
});
