package com.collazos.collaboratorsmanagement.service;

import com.collazos.collaboratorsmanagement.entity.OnBoardingSession;
import com.collazos.collaboratorsmanagement.repository.OnBoardingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OnBoardingSessionService {

    @Autowired
    private OnBoardingSessionRepository onBoardingSessionRepository;

    // CREATE
    public OnBoardingSession createSession(OnBoardingSession session) {
        // La lógica para asociar el OnboardingType se manejaría aquí o en el controlador
        return onBoardingSessionRepository.save(session);
    }

    // READ
    public List<OnBoardingSession> getAllSessions() {
        return onBoardingSessionRepository.findAll();
    }

    public Optional<OnBoardingSession> getSessionById(Integer id) {
        return onBoardingSessionRepository.findById(id);
    }

    // UPDATE
    public OnBoardingSession updateSession(Integer id, OnBoardingSession sessionDetails) {
        OnBoardingSession session = onBoardingSessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found with id: " + id));

        session.setStartDate(sessionDetails.getStartDate());
        session.setEndDate(sessionDetails.getEndDate());
        session.setCapacity(sessionDetails.getCapacity());
        session.setOnboardingType(sessionDetails.getOnboardingType());

        return onBoardingSessionRepository.save(session);
    }

    // DELETE
    public void deleteSession(Integer id) {
        if (!onBoardingSessionRepository.existsById(id)) {
            throw new RuntimeException("Session not found with id: " + id);
        }
        onBoardingSessionRepository.deleteById(id);
    }
}