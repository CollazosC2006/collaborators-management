package com.collazos.collaboratorsmanagement.repository;

import com.collazos.collaboratorsmanagement.entity.OnBoardingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OnBoardingSessionRepository extends JpaRepository<OnBoardingSession, Integer> {
    List<OnBoardingSession> findByStartDate(LocalDate startDate);
}
