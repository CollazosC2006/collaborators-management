import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollaboratorsDashboardComponent } from './collaborators-dashboard.component';

describe('CollaboratorsDashboardComponent', () => {
  let component: CollaboratorsDashboardComponent;
  let fixture: ComponentFixture<CollaboratorsDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CollaboratorsDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CollaboratorsDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
