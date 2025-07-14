import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Collaborator } from '../models/collaborator.model';

@Injectable({
  providedIn: 'root' 
})
export class CollaboratorService {

  private apiUrl = 'http://localhost:8080/collaborators';

  constructor(private http: HttpClient) { }

  getCollaborators(): Observable<Collaborator[]> {
    return this.http.get<Collaborator[]>(this.apiUrl);
  }

  getCollaboratorById(id: number): Observable<Collaborator> {
    return this.http.get<Collaborator>(`${this.apiUrl}/${id}`);
  }

  createCollaborator(collaborator: Partial<Collaborator>): Observable<Collaborator> {
    return this.http.post<Collaborator>(this.apiUrl, collaborator);
  }

  updateCollaborator(id: number, collaborator: Partial<Collaborator>): Observable<Collaborator> {
    return this.http.put<Collaborator>(`${this.apiUrl}/${id}`, collaborator);
  }

  deleteCollaborator(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
