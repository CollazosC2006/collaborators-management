export enum OnboardingStatus {
  SIN_ASIGNAR = 'Sin asignar',
  PENDIENTE = 'Pendiente',
  COMPLETADO = 'Completado',
}


export interface Collaborator {
  id: number;
  fullName: string;
  email: string;
  startDate: string;
  welcomeStatus: OnboardingStatus;
  technicalStatus: OnboardingStatus;
  technicalOnboardingStartDate?: string;
  technicalOnboardingEndDate?: string;

  generalSessionId?: number;
  technicalSessionId?: number;
}
