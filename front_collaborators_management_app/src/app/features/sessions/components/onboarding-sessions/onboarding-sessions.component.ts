import { Component } from '@angular/core';
import { OnboardingCalendarComponent } from '../onboarding-calendar/onboarding-calendar.component';
import { OnboardingFormComponent } from '../onboarding-form/onboarding-form.component';

@Component({
  selector: 'app-onboarding-sessions',
  imports: [OnboardingCalendarComponent,OnboardingFormComponent],
  templateUrl: './onboarding-sessions.component.html',
  styleUrl: './onboarding-sessions.component.scss'
})
export class OnboardingSessionsComponent {

}
