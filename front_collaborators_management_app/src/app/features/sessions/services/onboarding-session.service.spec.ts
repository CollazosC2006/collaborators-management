import { TestBed } from '@angular/core/testing';

import { OnboardingSessionService } from './onboarding-session.service';

describe('OnboardingSessionService', () => {
  let service: OnboardingSessionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnboardingSessionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
