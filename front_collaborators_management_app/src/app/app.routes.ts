import { Routes } from '@angular/router';
import { CollaboratorsDashboardComponent } from './features/collaborator/components/collaborators-dashboard/collaborators-dashboard.component';
import { OnboardingCalendarComponent } from './features/sessions/components/onboarding-calendar/onboarding-calendar.component';
import { CollaboratorFormComponent } from './features/collaborator/components/collaborator-form/collaborator-form.component';
import { OnboardingSessionsComponent } from './features/sessions/components/onboarding-sessions/onboarding-sessions.component';

export const appRoutes: Routes = [
  {
    path: 'collaborators',
    component: CollaboratorsDashboardComponent
  },
  {
    path: 'collaborator-form',
    component: CollaboratorFormComponent
  },
  {
    path: 'sessions',
    component: OnboardingSessionsComponent
  },
  {
    path: '',
    redirectTo: '/collaborators',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: '/collaborators'
  }

];
