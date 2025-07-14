import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CollaboratorsDashboardComponent } from './components/collaborators-dashboard/collaborators-dashboard.component';

const routes: Routes = [
  {
    path: '',
    component: CollaboratorsDashboardComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CollaboratorsRoutingModule { }
