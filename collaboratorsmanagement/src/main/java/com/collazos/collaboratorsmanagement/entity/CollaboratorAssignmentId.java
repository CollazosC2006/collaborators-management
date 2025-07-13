package com.collazos.collaboratorsmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CollaboratorAssignmentId implements Serializable {

    @Column(name = "collaborator_id")
    private Integer collaboratorId;

    @Column(name = "session_id")
    private Integer sessionId;

    // Es crucial tener un constructor vacío, getters/setters, y los métodos equals y hashCode

    public CollaboratorAssignmentId() {}

    public CollaboratorAssignmentId(Integer collaboratorId, Integer sessionId) {
        this.collaboratorId = collaboratorId;
        this.sessionId = sessionId;
    }

    // Getters y Setters
    public Integer getCollaboratorId() { return collaboratorId; }
    public void setCollaboratorId(Integer collaboratorId) { this.collaboratorId = collaboratorId; }
    public Integer getSessionId() { return sessionId; }
    public void setSessionId(Integer sessionId) { this.sessionId = sessionId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollaboratorAssignmentId that = (CollaboratorAssignmentId) o;
        return Objects.equals(collaboratorId, that.collaboratorId) &&
                Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collaboratorId, sessionId);
    }
}