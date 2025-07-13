package com.collazos.collaboratorsmanagement.repository;

import com.collazos.collaboratorsmanagement.entity.CollaboratorAssignment;
import com.collazos.collaboratorsmanagement.entity.CollaboratorAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaboratorAssignmentRepository extends JpaRepository<CollaboratorAssignment, CollaboratorAssignmentId> {
}
