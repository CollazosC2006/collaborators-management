import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnboardingSessionsComponent } from './onboarding-sessions.component';

describe('OnboardingSessionsComponent', () => {
  let component: OnboardingSessionsComponent;
  let fixture: ComponentFixture<OnboardingSessionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnboardingSessionsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OnboardingSessionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
