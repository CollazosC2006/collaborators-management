package com.collazos.collaboratorsmanagement.controller;

import com.collazos.collaboratorsmanagement.dto.AssignmentRequestDTO;
import com.collazos.collaboratorsmanagement.entity.CollaboratorAssignment;
import com.collazos.collaboratorsmanagement.service.CollaboratorAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assignments")
public class CollaboratorAssignmentController {

    @Autowired
    private CollaboratorAssignmentService assignmentService;

    // POST /api/assignments -> Asignar un colaborador a una sesión
    @PostMapping
    public ResponseEntity<CollaboratorAssignment> assignCollaborator(@RequestBody AssignmentRequestDTO request) {
        try {
            CollaboratorAssignment newAssignment = assignmentService.assignCollaboratorToSession(
                    request.getCollaboratorId(),
                    request.getSessionId()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(newAssignment);
        } catch (RuntimeException e) {
            // Esto podría ser un 404 si el colaborador/sesión no existe, o 400 Bad Request
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE /api/assignments -> Desasignar un colaborador de una sesión
    @DeleteMapping
    public ResponseEntity<Void> removeAssignment(@RequestParam Integer collaboratorId, @RequestParam Integer sessionId) {
        try {
            assignmentService.removeCollaboratorFromSession(collaboratorId, sessionId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}