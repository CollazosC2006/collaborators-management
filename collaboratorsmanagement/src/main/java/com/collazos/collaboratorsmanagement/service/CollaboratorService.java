package com.collazos.collaboratorsmanagement.service;

import com.collazos.collaboratorsmanagement.dto.CollaboratorDetailDTO;
import com.collazos.collaboratorsmanagement.entity.Collaborator;
import com.collazos.collaboratorsmanagement.entity.CollaboratorAssignment;
import com.collazos.collaboratorsmanagement.repository.CollaboratorAssignmentRepository;
import com.collazos.collaboratorsmanagement.repository.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CollaboratorService {

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @Autowired
    private CollaboratorAssignmentRepository assignmentRepository;

    // CREATE
    public Collaborator createCollaborator(Collaborator collaborator) {
        return collaboratorRepository.save(collaborator);
    }

    // READ
    public List<CollaboratorDetailDTO> getAllCollaborators() {
        List<Collaborator> collaborators = collaboratorRepository.findAll();
        List<CollaboratorAssignment> allAssignments = assignmentRepository.findAll();

        Map<Integer, List<CollaboratorAssignment>> assignmentsByCollaboratorId = allAssignments.stream()
                .collect(Collectors.groupingBy(assignment -> assignment.getCollaborator().getId()));

        return collaborators.stream().map(collaborator -> {
            CollaboratorDetailDTO dto = new CollaboratorDetailDTO();
            dto.setId(collaborator.getId());
            dto.setFullName(collaborator.getFullName());
            dto.setEmail(collaborator.getEmail());
            dto.setStartDate(collaborator.getStartDate());
            dto.setWelcomeStatus(collaborator.getWelcomeStatus());
            dto.setTechnicalStatus(collaborator.getTechnicalStatus());

            // Busca las asignaciones para el colaborador actual en el mapa
            List<CollaboratorAssignment> collaboratorAssignments = assignmentsByCollaboratorId.get(collaborator.getId());

            if (collaboratorAssignments != null) {
                for (CollaboratorAssignment assignment : collaboratorAssignments) {
                    String category = assignment.getOnboardingSession().getOnboardingType().getCategory();
                    if ("general".equalsIgnoreCase(category)) {
                        dto.setGeneralSessionId(assignment.getOnboardingSession().getId());
                    } else if ("tecnico".equalsIgnoreCase(category)) {
                        dto.setTechnicalSessionId(assignment.getOnboardingSession().getId());
                        dto.setTechnicalOnboardingStartDate(assignment.getOnboardingSession().getStartDate());
                        dto.setTechnicalOnboardingEndDate(assignment.getOnboardingSession().getEndDate());
                    }
                }
            }
            return dto;
        }).collect(Collectors.toList());
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

    public Optional<CollaboratorDetailDTO> getCollaboratorDetailsById(Integer id) {
        Optional<Collaborator> collaboratorOpt = collaboratorRepository.findById(id);
        if (collaboratorOpt.isEmpty()) {
            return Optional.empty();
        }

        Collaborator collaborator = collaboratorOpt.get();
        List<CollaboratorAssignment> assignments = assignmentRepository.findByCollaboratorId(id);

        // Crear el DTO y copiar las propiedades básicas
        CollaboratorDetailDTO dto = new CollaboratorDetailDTO();
        dto.setId(collaborator.getId());
        dto.setFullName(collaborator.getFullName());
        dto.setEmail(collaborator.getEmail());
        dto.setStartDate(collaborator.getStartDate());
        dto.setWelcomeStatus(collaborator.getWelcomeStatus());
        dto.setTechnicalStatus(collaborator.getTechnicalStatus());


        // Buscar y asignar los IDs de las sesiones
        for (CollaboratorAssignment assignment : assignments) {
            String category = assignment.getOnboardingSession().getOnboardingType().getCategory();
            if ("general".equalsIgnoreCase(category)) {
                dto.setGeneralSessionId(assignment.getOnboardingSession().getId());
            } else if ("tecnico".equalsIgnoreCase(category)) {
                dto.setTechnicalSessionId(assignment.getOnboardingSession().getId());
            }
        }

        return Optional.of(dto);
    }
}