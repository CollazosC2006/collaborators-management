import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { OnboardingType } from '../../models/onboarding-type.model';
import { OnboardingSessionService } from '../../services/onboarding-session.service';
import { CalendarUpdateService } from '../../services/calendar-update.service';

interface CreateSessionPayload {
  startDate: string;
  endDate: string;
  capacity: number;
  onboardingType: {
    id: number;
  };
}

@Component({
  selector: 'app-onboarding-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './onboarding-form.component.html',
  styleUrls: ['./onboarding-form.component.scss'],
})
export class OnboardingFormComponent implements OnInit {

  isFormVisible = false;

  selectedCategory: 'general' | 'tecnico' | '' = '';
  selectedTechnicalTypeId: number | null = null;
  selectedDuration: number | null = 5;
  startDate: string = '';

  technicalTypes: OnboardingType[] = [];
  durationOptions = [5, 6, 7];
  minDate: string = '';

  isStartDateInvalid = false;

  constructor(
    private sessionService: OnboardingSessionService,
    private calendarUpdateService: CalendarUpdateService
  ) {}

  ngOnInit(): void {
    this.technicalTypes = [
      { id: 2, name: 'Cap Devops', category: 'tecnico' },
      { id: 3, name: 'Cap Developer', category: 'tecnico' },
      { id: 4, name: 'Cap Test', category: 'tecnico' },
      { id: 5, name: 'Journey To Cloud', category: 'tecnico' },
    ];

    const today = new Date();
    today.setDate(today.getDate() + 1);
    this.minDate = today.toISOString().split('T')[0];
  }

  showForm(): void {
    this.isFormVisible = true;
  }

  cancel(): void {
    this.isFormVisible = false;
    this.resetForm();
  }

  resetForm(): void {
    this.selectedCategory = '';
    this.selectedTechnicalTypeId = null;
    this.selectedDuration = 5;
    this.startDate = '';
    this.isStartDateInvalid = false;
  }


  validateStartDate(): void {
    if (!this.startDate) {
      this.isStartDateInvalid = false;
      return;
    }
    const date = new Date(this.startDate + 'T00:00:00');
    const day = date.getDay(); // 0 = Sunday, 6 = Staurday
    console.log('Día seleccionado:', day);
    this.isStartDateInvalid = (day === 6 || day === 0);
  }

  onSubmit(): void {
    this.validateStartDate();
    if (!this.startDate || !this.selectedCategory || this.isStartDateInvalid) return;

    const startDateObj = new Date(this.startDate + 'T00:00:00');
    let endDateObj: Date;
    let payload: CreateSessionPayload;

    if (this.selectedCategory === 'general') {
      endDateObj = this.calculateEndDate(startDateObj, 3);
      payload = {
        startDate: this.startDate,
        endDate: endDateObj.toISOString().split('T')[0],
        capacity: 50,
        onboardingType: { id: 1 }
      };
    } else {
      if (!this.selectedTechnicalTypeId || !this.selectedDuration) return;
      endDateObj = this.calculateEndDate(startDateObj, this.selectedDuration);
      payload = {
        startDate: this.startDate,
        endDate: endDateObj.toISOString().split('T')[0],
        capacity: 10,
        onboardingType: { id: this.selectedTechnicalTypeId }
      };
    }

    console.log('Enviando al backend:', payload);

    this.sessionService.createSession(payload).subscribe({
      next: (newSession) => {
        console.log('Sesión creada:', newSession);
        alert('La sesión de onboarding ha sido creada exitosamente.');
        this.cancel();
        this.calendarUpdateService.requestUpdate();
      },
      error: (err) => {
        console.error('Error al crear la sesión:', err);
        alert('Ocurrió un error al crear la sesión. Por favor, revise la consola para más detalles.');
      }
    });
  }

  private calculateEndDate(startDate: Date, durationInDays: number): Date {
    let remainingDays = durationInDays - 1;
    let currentDate = new Date(startDate.valueOf());

    while (remainingDays > 0) {
      currentDate.setDate(currentDate.getDate() + 1);
      const dayOfWeek = currentDate.getDay();
      if (dayOfWeek !== 0 && dayOfWeek !== 6) {
        remainingDays--;
      }
    }
    return currentDate;
  }
}
