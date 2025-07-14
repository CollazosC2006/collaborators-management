import { Component } from '@angular/core';
import { Collaborator, OnboardingStatus } from '../../models/collaborator.model';
import { OnboardingSession } from '../../../sessions/models/onboarding-session.model';
import { FormsModule } from '@angular/forms';
import { CommonModule, DatePipe } from '@angular/common';
import { CollaboratorDataService } from '../../services/collaborator-data.service';
import { forkJoin, of, Subscription, firstValueFrom} from 'rxjs';
import { Router } from '@angular/router';
import { CollaboratorService } from '../../services/collaborator.service';
import { OnboardingSessionService } from '../../../sessions/services/onboarding-session.service';
import { CollaboratorAssignmentService } from '../../services/collaborator-assignment.service';

@Component({
  selector: 'app-collaborator-form',
  imports: [FormsModule,CommonModule,DatePipe],
  templateUrl: './collaborator-form.component.html',
  styleUrl: './collaborator-form.component.scss'
})
export class CollaboratorFormComponent {

  collaborator: Partial<Collaborator> = {};
  originalCollaborator: Partial<Collaborator> = {};
  generalSessions: OnboardingSession[] = [];
  technicalSessions: OnboardingSession[] = [];
  selectedGeneralSessionId: number | null = null;
  selectedTechnicalSessionId: number | null = null;
  assignmentStatusOptions = [OnboardingStatus.PENDIENTE, OnboardingStatus.COMPLETADO];

  isEditMode = false;
  private subscription: Subscription = new Subscription();

  constructor(
    private collaboratorDataService: CollaboratorDataService,
    private router: Router,
    private collaboratorService: CollaboratorService,
    private sessionService: OnboardingSessionService,
    private assignmentService: CollaboratorAssignmentService
  ) {
        console.log('Constructor del formulario: assignmentService está definido:', !!this.collaboratorService);
  }

  ngOnInit(): void {
    this.loadSessions();

    const editSub = this.collaboratorDataService.collaboratorToEdit$.subscribe(collaboratorToEdit => {
      if (collaboratorToEdit) {
        this.isEditMode = true;
        this.collaborator = { ...collaboratorToEdit };
        this.originalCollaborator = { ...collaboratorToEdit };
        this.selectedGeneralSessionId = this.collaborator.generalSessionId || null;
        this.selectedTechnicalSessionId = this.collaborator.technicalSessionId || null;
      } else {
        this.isEditMode = false;
        this.resetForm();
      }
    });
    this.subscription.add(editSub);
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.collaboratorDataService.setCollaborator(null);
  }

  loadSessions(): void {
    this.sessionService.getSessions().subscribe({
      next: (allSessions) => {
        this.generalSessions = allSessions.filter(s => s.onboardingType.id === 1);
        this.technicalSessions = allSessions.filter(s => s.onboardingType.id !== 1);
      },
      error: (err) => console.error('Error al cargar las sesiones de onboarding', err)
    });
  }

  resetForm(): void {
    this.collaborator = {
      fullName: '',
      email: '',
      startDate: '',
      welcomeStatus: OnboardingStatus.SIN_ASIGNAR,
      technicalStatus: OnboardingStatus.SIN_ASIGNAR,
    };
    this.selectedGeneralSessionId = null;
    this.selectedTechnicalSessionId = null;
  }

  onSessionChange(type: 'general' | 'tecnico'): void {
    if (type === 'general') {
      if (this.selectedGeneralSessionId) {
        this.collaborator.welcomeStatus = OnboardingStatus.PENDIENTE;
      } else {
        this.collaborator.welcomeStatus = OnboardingStatus.SIN_ASIGNAR;
      }
    } else {
      if (this.selectedTechnicalSessionId) {
        this.collaborator.technicalStatus = OnboardingStatus.PENDIENTE;
        const selectedSession = this.technicalSessions.find(s => s.id === Number(this.selectedTechnicalSessionId));
        this.collaborator.technicalOnboardingStartDate = selectedSession?.startDate;
      } else {
        this.collaborator.technicalStatus = OnboardingStatus.SIN_ASIGNAR;
        this.collaborator.technicalOnboardingStartDate = undefined;
      }
    }
  }

  onCancel(): void {
    this.router.navigate(['/collaborators']);
  }

  onSubmit(): void {
    console.log('Colaborador a guardar:', this.collaborator);
    if (!this.assignmentService) {
      console.error("Error Crítico: El servicio 'assignmentService' no está disponible al momento de hacer submit.");
      return;
    }

    if (this.isEditMode && this.collaborator.id) {
      this.handleUpdate();
    } else {
      this.handleCreate();
    }

  }

  private async handleCreate(): Promise<void> {
    try {
      const newCollaborator = await firstValueFrom(this.collaboratorService.createCollaborator(this.collaborator));

      const assignmentObservables = [];
      if (this.selectedGeneralSessionId) {
        assignmentObservables.push(this.assignmentService.assign(newCollaborator.id, this.selectedGeneralSessionId));
      }
      if (this.selectedTechnicalSessionId) {
        assignmentObservables.push(this.assignmentService.assign(newCollaborator.id, this.selectedTechnicalSessionId));
      }

      if (assignmentObservables.length > 0) {
        await firstValueFrom(forkJoin(assignmentObservables));
      }

      console.log('Colaborador y asignaciones creadas exitosamente.');
      this.router.navigate(['/collaborators']);

    } catch (err) {
      console.error('Error en el proceso de creación:', err);
    }
  }


  private async handleUpdate(): Promise<void> {
    try {
      const collaboratorId = this.collaborator.id!;
      const assignmentObservables = [];

      const originalGeneralId = this.originalCollaborator.generalSessionId;
      const newGeneralId = this.selectedGeneralSessionId;
      if (originalGeneralId !== newGeneralId) {
        if (originalGeneralId) assignmentObservables.push(this.assignmentService.unassign(collaboratorId, originalGeneralId));
        if (newGeneralId) assignmentObservables.push(this.assignmentService.assign(collaboratorId, newGeneralId));
      }

      const originalTechnicalId = this.originalCollaborator.technicalSessionId;
      const newTechnicalId = this.selectedTechnicalSessionId;
      if (originalTechnicalId !== newTechnicalId) {
        if (originalTechnicalId) assignmentObservables.push(this.assignmentService.unassign(collaboratorId, originalTechnicalId));
        if (newTechnicalId) assignmentObservables.push(this.assignmentService.assign(collaboratorId, newTechnicalId));
      }

      if (assignmentObservables.length > 0) {
        await firstValueFrom(forkJoin(assignmentObservables));
      }

      await firstValueFrom(this.collaboratorService.updateCollaborator(collaboratorId, this.collaborator));

      console.log('Colaborador y asignaciones actualizadas exitosamente.');
      this.router.navigate(['/collaborators']);

    } catch (err) {
      console.error('Error en el proceso de actualización:', err);
    }
  }


}
