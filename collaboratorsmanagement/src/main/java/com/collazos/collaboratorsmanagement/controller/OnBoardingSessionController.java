package com.collazos.collaboratorsmanagement.controller;

import com.collazos.collaboratorsmanagement.entity.OnBoardingSession;
import com.collazos.collaboratorsmanagement.service.OnBoardingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sessions")
@CrossOrigin(origins = "http://localhost:4200")
public class OnBoardingSessionController {

    @Autowired
    private OnBoardingSessionService sessionService;

    // POST /api/sessions -> Crear una nueva sesión
    @PostMapping
    public ResponseEntity<OnBoardingSession> createSession(@RequestBody OnBoardingSession session) {
        // Asegúrate de que el JSON incluya el objeto onboardingType con su ID.
        // Ejemplo: { ..., "onBoardingType": {"id": 1} }
        OnBoardingSession newSession = sessionService.createSession(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSession);
    }

    // GET /api/sessions -> Obtener todas las sesiones
    @GetMapping
    public ResponseEntity<List<OnBoardingSession>> getAllSessions() {
        List<OnBoardingSession> sessions = sessionService.getAllSessions();
        return ResponseEntity.ok(sessions);
    }

    // GET /api/sessions/{id} -> Obtener una sesión por ID
    @GetMapping("/{id}")
    public ResponseEntity<OnBoardingSession> getSessionById(@PathVariable Integer id) {
        return sessionService.getSessionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/sessions/{id} -> Actualizar una sesión existente
    @PutMapping("/{id}")
    public ResponseEntity<OnBoardingSession> updateSession(@PathVariable Integer id, @RequestBody OnBoardingSession sessionDetails) {
        try {
            OnBoardingSession updatedSession = sessionService.updateSession(id, sessionDetails);
            return ResponseEntity.ok(updatedSession);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/sessions/{id} -> Eliminar una sesión
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Integer id) {
        try {
            sessionService.deleteSession(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}