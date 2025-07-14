import { Component, Inject, OnDestroy, OnInit, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FullCalendarModule } from '@fullcalendar/angular';
import { CalendarOptions, EventInput } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';

// Importaciones de servicios y modelos
import { OnboardingSessionService } from '../../services/onboarding-session.service';
import { OnboardingSession } from '../../models/onboarding-session.model';
import { CalendarUpdateService } from '../../services/calendar-update.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-onboarding-calendar',
  standalone: true,
  imports: [CommonModule, FullCalendarModule], // Se importa el módulo de FullCalendar
  templateUrl: './onboarding-calendar.component.html',
  styleUrls: ['./onboarding-calendar.component.scss'],
})
export class OnboardingCalendarComponent implements OnInit, OnDestroy {

  calendarOptions: CalendarOptions = {};
  private updateSubscription!: Subscription;

  constructor(
    private sessionService: OnboardingSessionService,
    private calendarUpdateService: CalendarUpdateService,
  ) {
  }



  ngOnInit(): void {
    this.initializeCalendar();
    this.loadCalendarEvents();

    this.updateSubscription = this.calendarUpdateService.calendarUpdate$.subscribe(() => {
      console.log('Recibida solicitud de actualización para el calendario.');
      this.loadCalendarEvents();
    });

  }

  initializeCalendar(): void {
    this.calendarOptions = {
      initialView: 'dayGridMonth',
      plugins: [dayGridPlugin, interactionPlugin],
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,dayGridWeek'
      },
      weekends: true,
      editable: false,
      selectable: true,
      selectMirror: true,
      dayMaxEvents: true,
      events: [],
      height: 'auto',
    };
  }

  ngOnDestroy(): void {
    if (this.updateSubscription) {
      this.updateSubscription.unsubscribe();
    }
  }

  loadCalendarEvents(): void {
    this.sessionService.getSessions().subscribe({
      next: (sessions) => {
        const events = this.mapSessionsToEvents(sessions);
        this.calendarOptions.events = events;
      },
      error: (err) => console.error('Error al cargar las sesiones para el calendario', err)
    });
  }

  private mapSessionsToEvents(sessions: OnboardingSession[]): EventInput[] {
    return sessions.map(session => {

      const endDate = new Date(session.endDate);
      endDate.setDate(endDate.getDate() + 1);

      return {
        id: session.id.toString(),
        title: session.onboardingType.name + " (id: " + session.id + ")",
        start: session.startDate,
        end: endDate.toISOString().split('T')[0], // Format YYYY-MM-DD
        backgroundColor: session.onboardingType.category === 'general'
          ? 'var(--bb-semantic-success-800)'
          : 'var(--bb-blue-midnight-700)',
        borderColor: session.onboardingType.category === 'general'
          ? 'var(--bb-semantic-success-900)'
          : 'var(--bb-blue-midnight-800)',
      };
    });
  }
}
