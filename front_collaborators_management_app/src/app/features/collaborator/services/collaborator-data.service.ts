import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Collaborator } from '../models/collaborator.model';

@Injectable({
  providedIn: 'root'
})
export class CollaboratorDataService {
  private collaboratorToEditSource = new BehaviorSubject<Collaborator | null>(null);

  collaboratorToEdit$ = this.collaboratorToEditSource.asObservable();

  constructor() { }

  setCollaborator(collaborator: Collaborator | null) {
    this.collaboratorToEditSource.next(collaborator);
  }
}
