package com.collazos.collaboratorsmanagement.dto;

public class AssignmentRequestDTO {
    private Integer collaboratorId;
    private Integer sessionId;

    public Integer getCollaboratorId() { return collaboratorId; }
    public void setCollaboratorId(Integer collaboratorId) { this.collaboratorId = collaboratorId; }
    public Integer getSessionId() { return sessionId; }
    public void setSessionId(Integer sessionId) { this.sessionId = sessionId; }
}
