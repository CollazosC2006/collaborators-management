package com.collazos.collaboratorsmanagement.dto;

import java.time.LocalDate;


public class CollaboratorDetailDTO {

    private Integer id;
    private String fullName;
    private String email;
    private LocalDate startDate;
    private String welcomeStatus;
    private String technicalStatus;

    private Integer generalSessionId;
    private Integer technicalSessionId;

    private LocalDate technicalOnboardingStartDate; // Fecha de inicio de la sesión técnica
    private LocalDate technicalOnboardingEndDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getWelcomeStatus() {
        return welcomeStatus;
    }

    public void setWelcomeStatus(String welcomeStatus) {
        this.welcomeStatus = welcomeStatus;
    }

    public String getTechnicalStatus() {
        return technicalStatus;
    }

    public void setTechnicalStatus(String technicalStatus) {
        this.technicalStatus = technicalStatus;
    }

    public Integer getGeneralSessionId() {
        return generalSessionId;
    }

    public void setGeneralSessionId(Integer generalSessionId) {
        this.generalSessionId = generalSessionId;
    }

    public Integer getTechnicalSessionId() {
        return technicalSessionId;
    }

    public void setTechnicalSessionId(Integer technicalSessionId) {
        this.technicalSessionId = technicalSessionId;
    }

    public LocalDate getTechnicalOnboardingStartDate() {
        return technicalOnboardingStartDate;
    }

    public void setTechnicalOnboardingStartDate(LocalDate technicalOnboardingDate) {
        this.technicalOnboardingStartDate = technicalOnboardingDate;
    }

    public LocalDate getTechnicalOnboardingEndDate() {
        return technicalOnboardingEndDate;
    }

    public void setTechnicalOnboardingEndDate(LocalDate technicalOnboardingDate) {
        this.technicalOnboardingEndDate = technicalOnboardingDate;
    }
}