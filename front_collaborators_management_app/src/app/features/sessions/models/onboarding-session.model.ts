import { OnboardingType } from "./onboarding-type.model";



export interface OnboardingSession {
  id: number;
  startDate: string;
  endDate: string;
  capacity: number;
  onboardingType: OnboardingType; 
}
