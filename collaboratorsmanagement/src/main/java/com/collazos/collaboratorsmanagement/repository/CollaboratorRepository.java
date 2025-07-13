package com.collazos.collaboratorsmanagement.repository;

import com.collazos.collaboratorsmanagement.entity.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaboratorRepository  extends JpaRepository<Collaborator, Integer> {
}
