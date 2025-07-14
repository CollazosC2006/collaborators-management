import { TestBed } from '@angular/core/testing';

import { CollaboratorAssignmentService } from './collaborator-assignment.service';

describe('CollaboratorAssignmentService', () => {
  let service: CollaboratorAssignmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CollaboratorAssignmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
