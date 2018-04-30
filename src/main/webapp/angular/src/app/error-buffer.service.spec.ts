import { TestBed, inject } from '@angular/core/testing';

import { ErrorBufferService } from './error-buffer.service';

describe('ErrorBufferService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ErrorBufferService]
    });
  });

  it('should be created', inject([ErrorBufferService], (service: ErrorBufferService) => {
    expect(service).toBeTruthy();
  }));
});
