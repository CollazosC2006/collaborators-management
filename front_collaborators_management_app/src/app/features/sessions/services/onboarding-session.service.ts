import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OnboardingSession } from '../models/onboarding-session.model';

export interface CreateSessionPayload {
  startDate: string;
  endDate: string;
  capacity: number;
  onboardingType: {
    id: number;
  };
}

@Injectable({
  providedIn: 'root'
})
export class OnboardingSessionService {

  private apiUrl = 'http://localhost:8080/sessions';

  constructor(private http: HttpClient) { }

  getSessions(): Observable<OnboardingSession[]> {
    return this.http.get<OnboardingSession[]>(this.apiUrl);
  }

  createSession(payload: CreateSessionPayload): Observable<OnboardingSession> {
    return this.http.post<OnboardingSession>(this.apiUrl, payload);
  }
}
