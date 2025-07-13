package com.collazos.collaboratorsmanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "collaborator")
public class Collaborator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "welcome_status")
    private String welcomeStatus;

    @Column(name = "technical_status")
    private String technicalStatus;

    // Getters y Setters
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
}