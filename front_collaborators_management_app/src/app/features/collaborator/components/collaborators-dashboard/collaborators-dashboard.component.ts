import { Component, OnInit } from '@angular/core';
import { Collaborator, OnboardingStatus } from '../../models/collaborator.model';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';


import { Router } from '@angular/router';
import { CollaboratorDataService } from '../../services/collaborator-data.service';
import { CollaboratorService } from '../../services/collaborator.service';

@Component({
  selector: 'app-collaborators-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    DatePipe
  ],
  templateUrl: './collaborators-dashboard.component.html',
  styleUrl: './collaborators-dashboard.component.scss'
})
export class CollaboratorsDashboardComponent implements OnInit {
  allCollaborators: Collaborator[] = [];
  filteredCollaborators: Collaborator[] = [];
  statusOptions: String[] = [
    OnboardingStatus.PENDIENTE,
    OnboardingStatus.COMPLETADO];
  filterStatusOptions: OnboardingStatus[] = Object.values(OnboardingStatus);
  generalStatusFilter: OnboardingStatus | '' = '';
  technicalStatusFilter: OnboardingStatus | '' = '';

  selectedCollaborator: Collaborator | null = null;

  public readonly OnboardingStatus = OnboardingStatus;

  constructor(
    private router: Router,
    private collaboratorDataService: CollaboratorDataService,
    private collaboratorService: CollaboratorService
  ) {}


  ngOnInit(): void {
    this.loadCollaborators();
    this.filteredCollaborators = [...this.allCollaborators];
  }

  selectCollaborator(collaborator: Collaborator): void {
    this.selectedCollaborator = this.selectedCollaborator?.id === collaborator.id ? null : collaborator;
  }

  loadCollaborators(): void {
    this.collaboratorService.getCollaborators().subscribe({
      next: (data) => {
        this.allCollaborators = data;
        this.applyFilters();
        this.selectedCollaborator = null;
      },
      error: (err) => console.error('Error al obtener colaboradores', err)
    });
  }

  createCollaborator(): void {
    this.collaboratorDataService.setCollaborator(null);
    this.router.navigate(['/collaborator-form']);
  }

   updateCollaborator(): void {
    if (!this.selectedCollaborator || !this.selectedCollaborator.id) return;

    this.collaboratorService.getCollaboratorById(this.selectedCollaborator.id).subscribe({
      next: (collaboratorToEdit) => {
        this.collaboratorDataService.setCollaborator(collaboratorToEdit);
        this.router.navigate(['/collaborator-form']);
      },
      error: (err) => console.error(`Error al obtener los detalles del colaborador ${this.selectedCollaborator?.id}`, err)
    });
  }

  deleteCollaborator(): void {
    if (!this.selectedCollaborator || !this.selectedCollaborator.id) return;

    if (confirm(`¿Estás seguro de que deseas eliminar a ${this.selectedCollaborator.fullName}?`)) {
      this.collaboratorService.deleteCollaborator(this.selectedCollaborator.id).subscribe({
        next: () => {
          console.log('Colaborador eliminado exitosamente');
          this.loadCollaborators();
        },
        error: (err) => console.error('Error al eliminar colaborador', err)
      });
    }
  }


  applyFilters(): void {
    let tempCollaborators = [...this.allCollaborators];

    if (this.generalStatusFilter) {
      tempCollaborators = tempCollaborators.filter(c => c.welcomeStatus === this.generalStatusFilter);
    }

    if (this.technicalStatusFilter) {
      tempCollaborators = tempCollaborators.filter(c => c.technicalStatus === this.technicalStatusFilter);
    }

    this.filteredCollaborators = tempCollaborators;
  }

  clearFilters(): void {
    this.generalStatusFilter = '';
    this.technicalStatusFilter = '';
    this.applyFilters();
  }

  onStatusChange(collaborator: Collaborator, newStatus: OnboardingStatus, type: 'general' | 'tecnico'): void {
    const originalStatus = type === 'general' ? collaborator.welcomeStatus : collaborator.technicalStatus;

    if (type === 'general') {
      collaborator.welcomeStatus = newStatus;
    } else {
      collaborator.technicalStatus = newStatus;
    }

    console.log(`Actualizando estado de ${collaborator.fullName} a ${newStatus}...`);

    this.collaboratorService.updateCollaborator(collaborator.id, collaborator).subscribe({
      next: () => {
        console.log('Estado actualizado exitosamente en la base de datos.');
      },
      error: (err) => {
        console.error('Error al actualizar el estado:', err);
        if (type === 'general') {
          collaborator.welcomeStatus = originalStatus;
        } else {
          collaborator.technicalStatus = originalStatus;
        }
        alert('No se pudo actualizar el estado. Por favor, intente de nuevo.');
      }
    });
  }

}
