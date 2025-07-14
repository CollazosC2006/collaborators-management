import { TestBed } from '@angular/core/testing';

import { CollaboratorDataService } from './collaborator-data.service';

describe('CollaboratorDataService', () => {
  let service: CollaboratorDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CollaboratorDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
