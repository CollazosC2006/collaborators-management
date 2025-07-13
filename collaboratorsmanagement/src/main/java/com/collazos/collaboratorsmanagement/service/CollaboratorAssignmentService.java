package com.collazos.collaboratorsmanagement.service;

import com.collazos.collaboratorsmanagement.entity.Collaborator;
import com.collazos.collaboratorsmanagement.entity.CollaboratorAssignment;
import com.collazos.collaboratorsmanagement.entity.CollaboratorAssignmentId;
import com.collazos.collaboratorsmanagement.entity.OnBoardingSession;
import com.collazos.collaboratorsmanagement.repository.CollaboratorAssignmentRepository;
import com.collazos.collaboratorsmanagement.repository.CollaboratorRepository;
import com.collazos.collaboratorsmanagement.repository.OnBoardingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollaboratorAssignmentService {

    @Autowired
    private CollaboratorAssignmentRepository assignmentRepository;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @Autowired
    private OnBoardingSessionRepository sessionRepository;

    /**
     * Asigna un colaborador a una sesión de onboarding.
     */
    public CollaboratorAssignment assignCollaboratorToSession(Integer collaboratorId, Integer sessionId) {
        // 1. Validar que tanto el colaborador como la sesión existen
        Collaborator collaborator = collaboratorRepository.findById(collaboratorId)
                .orElseThrow(() -> new RuntimeException("Collaborator not found with id: " + collaboratorId));

        OnBoardingSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found with id: " + sessionId));

        // 2. Crear el ID compuesto para la asignación
        CollaboratorAssignmentId assignmentId = new CollaboratorAssignmentId(collaboratorId, sessionId);

        // 3. Crear la entidad de asignación
        CollaboratorAssignment newAssignment = new CollaboratorAssignment();
        newAssignment.setId(assignmentId);
        newAssignment.setCollaborator(collaborator);
        newAssignment.setOnboardingSession(session);

        // 4. Guardar la nueva asignación en la base de datos
        return assignmentRepository.save(newAssignment);
    }

    /**
     * Elimina la asignación de un colaborador a una sesión.
     */
    public void removeCollaboratorFromSession(Integer collaboratorId, Integer sessionId) {
        // Crear el ID compuesto para buscar la asignación a eliminar
        CollaboratorAssignmentId assignmentId = new CollaboratorAssignmentId(collaboratorId, sessionId);

        if (!assignmentRepository.existsById(assignmentId)) {
            throw new RuntimeException("Assignment not found for collaborator " + collaboratorId + " in session " + sessionId);
        }

        assignmentRepository.deleteById(assignmentId);
    }
}