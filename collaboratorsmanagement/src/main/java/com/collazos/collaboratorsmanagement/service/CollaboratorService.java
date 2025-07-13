package com.collazos.collaboratorsmanagement.service;

import com.collazos.collaboratorsmanagement.entity.Collaborator;
import com.collazos.collaboratorsmanagement.repository.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;


@Service
public class CollaboratorService {

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    // CREATE
    public Collaborator createCollaborator(Collaborator collaborator) {
        return collaboratorRepository.save(collaborator);
    }

    // READ
    public List<Collaborator> getAllCollaborators() {
        return collaboratorRepository.findAll();
    }

    public Optional<Collaborator> getCollaboratorById(Integer id) {
        return collaboratorRepository.findById(id);
    }

    // UPDATE
    public Collaborator updateCollaborator(Integer id, Collaborator collaboratorDetails) {
        // Primero busca el colaborador existente
        Collaborator collaborator = collaboratorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Collaborator not found with id: " + id));

        // Actualiza los campos
        collaborator.setFullName(collaboratorDetails.getFullName());
        collaborator.setEmail(collaboratorDetails.getEmail());
        collaborator.setStartDate(collaboratorDetails.getStartDate());
        collaborator.setWelcomeStatus(collaboratorDetails.getWelcomeStatus());
        collaborator.setTechnicalStatus(collaboratorDetails.getTechnicalStatus());

        // Guarda y retorna el colaborador actualizado
        return collaboratorRepository.save(collaborator);
    }

    // DELETE
    public void deleteCollaborator(Integer id) {
        if (!collaboratorRepository.existsById(id)) {
            throw new RuntimeException("Collaborator not found with id: " + id);
        }
        collaboratorRepository.deleteById(id);
    }
}