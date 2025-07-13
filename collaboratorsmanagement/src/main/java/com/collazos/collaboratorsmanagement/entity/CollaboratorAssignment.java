package com.collazos.collaboratorsmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "collaboratorassignment") // Corresponde al nombre de tu tabla
public class CollaboratorAssignment {

    @EmbeddedId
    private CollaboratorAssignmentId id;

    @ManyToOne
    @MapsId("collaboratorId") // Mapea el campo 'collaboratorId' del EmbeddedId
    @JoinColumn(name = "collaborator_id")
    private Collaborator collaborator;

    @ManyToOne
    @MapsId("sessionId") // Mapea el campo 'sessionId' del EmbeddedId
    @JoinColumn(name = "session_id")
    private OnBoardingSession onBoardingSession;

    // Getters y Setters
    public CollaboratorAssignmentId getId() {
        return id;
    }

    public void setId(CollaboratorAssignmentId id) {
        this.id = id;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public OnBoardingSession getOnboardingSession() {
        return onBoardingSession;
    }

    public void setOnboardingSession(OnBoardingSession onBoardingSession) {
        this.onBoardingSession = onBoardingSession;
    }
}