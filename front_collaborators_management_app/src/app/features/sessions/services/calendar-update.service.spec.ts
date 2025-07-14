import { TestBed } from '@angular/core/testing';

import { CalendarUpdateService } from './calendar-update.service';

describe('CalendarUpdateService', () => {
  let service: CalendarUpdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CalendarUpdateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
