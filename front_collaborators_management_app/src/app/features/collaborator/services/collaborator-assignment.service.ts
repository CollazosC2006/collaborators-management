import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


export interface AssignmentRequest {
  collaboratorId: number;
  sessionId: number;
}

@Injectable({
  providedIn: 'root'
})
export class CollaboratorAssignmentService {
  private apiUrl = 'http://localhost:8080/assignments';

  constructor(private http: HttpClient) { }

  assign(collaboratorId: number, sessionId: number): Observable<any> {
    const request: AssignmentRequest = { collaboratorId, sessionId };
    return this.http.post(this.apiUrl, request);
  }


  unassign(collaboratorId: number, sessionId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}?collaboratorId=${collaboratorId}&sessionId=${sessionId}`);
  }
}
