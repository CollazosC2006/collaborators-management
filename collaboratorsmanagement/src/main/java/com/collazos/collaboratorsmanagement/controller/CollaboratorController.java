package com.collazos.collaboratorsmanagement.controller;

import com.collazos.collaboratorsmanagement.entity.Collaborator;
import com.collazos.collaboratorsmanagement.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/collaborators")
public class CollaboratorController {

    @Autowired
    private CollaboratorService collaboratorService;

    // POST /api/collaborators -> Crear un nuevo colaborador
    @PostMapping
    public ResponseEntity<Collaborator> createCollaborator(@RequestBody Collaborator collaborator) {
        Collaborator newCollaborator = collaboratorService.createCollaborator(collaborator);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCollaborator);
    }

    // GET /api/collaborators -> Obtener todos los colaboradores
    @GetMapping
    public ResponseEntity<List<Collaborator>> getAllCollaborators() {
        List<Collaborator> collaborators = collaboratorService.getAllCollaborators();
        return ResponseEntity.ok(collaborators);
    }

    // GET /api/collaborators/{id} -> Obtener un colaborador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Collaborator> getCollaboratorById(@PathVariable Integer id) {
        return collaboratorService.getCollaboratorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/collaborators/{id} -> Actualizar un colaborador existente
    @PutMapping("/{id}")
    public ResponseEntity<Collaborator> updateCollaborator(@PathVariable Integer id, @RequestBody Collaborator collaboratorDetails) {
        try {
            Collaborator updatedCollaborator = collaboratorService.updateCollaborator(id, collaboratorDetails);
            return ResponseEntity.ok(updatedCollaborator);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/collaborators/{id} -> Eliminar un colaborador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollaborator(@PathVariable Integer id) {
        try {
            collaboratorService.deleteCollaborator(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}