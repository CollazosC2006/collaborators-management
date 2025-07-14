package com.collazos.collaboratorsmanagement.service;

import com.collazos.collaboratorsmanagement.entity.CollaboratorAssignment;
import com.collazos.collaboratorsmanagement.entity.OnBoardingSession;
import com.collazos.collaboratorsmanagement.repository.CollaboratorAssignmentRepository;
import com.collazos.collaboratorsmanagement.repository.OnBoardingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private OnBoardingSessionRepository sessionRepository;

    @Autowired
    private CollaboratorAssignmentRepository assignmentRepository;


    @Scheduled(cron = "0 42 1 * * ?",zone = "America/Bogota")
    public void checkUpcomingOnboardingsAndSendAlerts() {
        System.out.println("----------------------------------------------------");
        System.out.println("Ejecutando verificación de alertas de onboarding...");

        LocalDate targetDate = LocalDate.now().plusDays(7);
        System.out.println("Buscando sesiones para la fecha: " + targetDate);

        List<OnBoardingSession> upcomingSessions = sessionRepository.findByStartDate(targetDate);

        if (upcomingSessions.isEmpty()) {
            System.out.println("No se encontraron sesiones programadas para dentro de 7 días.");
            System.out.println("----------------------------------------------------");
            return;
        }

        System.out.println("Se encontraron " + upcomingSessions.size() + " sesiones próximas.");

        for (OnBoardingSession session : upcomingSessions) {
            System.out.println("Procesando sesión: '" + session.getOnboardingType().getName() + "' (ID: " + session.getId() + ")");

            List<CollaboratorAssignment> assignments = assignmentRepository.findById_SessionId(session.getId());

            if (assignments.isEmpty()) {
                System.out.println(" -> No hay colaboradores inscritos en esta sesión.");
                continue;
            }

            System.out.println(" -> Se encontraron " + assignments.size() + " colaboradores inscritos. Enviando alertas...");

            for (CollaboratorAssignment assignment : assignments) {
                String recipientEmail = assignment.getCollaborator().getEmail();
                String collaboratorName = assignment.getCollaborator().getFullName();
                String sessionName = session.getOnboardingType().getName();

                // Este es el mensaje que aparecerá en la consola de tu servidor Spring Boot
                System.out.printf(
                        " -> [ALERTA SIMULADA] Enviando correo a: %s. Asunto: Recordatorio de Onboarding. Mensaje: Hola %s, te recordamos que tu sesión de '%s' comienza en una semana, el %s.%n",
                        recipientEmail,
                        collaboratorName,
                        sessionName,
                        session.getStartDate()
                );
            }
        }
        System.out.println("Verificación de alertas completada.");
        System.out.println("----------------------------------------------------");
    }
}