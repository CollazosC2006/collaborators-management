import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalendarUpdateService {

  private calendarUpdateSource = new Subject<void>();

  calendarUpdate$ = this.calendarUpdateSource.asObservable();

  requestUpdate(): void {
    this.calendarUpdateSource.next();
  }
}
