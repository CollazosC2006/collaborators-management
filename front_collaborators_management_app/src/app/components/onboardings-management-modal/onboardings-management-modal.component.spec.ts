import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnboardingsManagementModalComponent } from './onboardings-management-modal.component';

describe('OnboardingsManagementModalComponent', () => {
  let component: OnboardingsManagementModalComponent;
  let fixture: ComponentFixture<OnboardingsManagementModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnboardingsManagementModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OnboardingsManagementModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
