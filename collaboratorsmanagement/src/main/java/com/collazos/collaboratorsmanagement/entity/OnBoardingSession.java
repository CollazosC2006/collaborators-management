package com.collazos.collaboratorsmanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "onboardingsession")
public class OnBoardingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "onboarding_type_id", nullable = false)
    private OnBoardingType onBoardingType;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public OnBoardingType getOnboardingType() {
        return onBoardingType;
    }

    public void setOnboardingType(OnBoardingType onBoardingType) {
        this.onBoardingType = onBoardingType;
    }
}