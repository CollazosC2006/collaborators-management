import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollaboratorsDataComponent } from './collaborators-data.component';

describe('CollaboratorsDataComponent', () => {
  let component: CollaboratorsDataComponent;
  let fixture: ComponentFixture<CollaboratorsDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CollaboratorsDataComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CollaboratorsDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
