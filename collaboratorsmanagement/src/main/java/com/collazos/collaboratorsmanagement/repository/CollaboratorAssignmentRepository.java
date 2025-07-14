package com.collazos.collaboratorsmanagement.repository;

import com.collazos.collaboratorsmanagement.entity.CollaboratorAssignment;
import com.collazos.collaboratorsmanagement.entity.CollaboratorAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CollaboratorAssignmentRepository extends JpaRepository<CollaboratorAssignment, CollaboratorAssignmentId> {
    List<CollaboratorAssignment> findByCollaboratorId(Integer collaboratorId);
    List<CollaboratorAssignment> findById_SessionId(Integer sessionId);

}
