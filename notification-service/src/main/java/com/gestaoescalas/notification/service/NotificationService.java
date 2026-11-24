package com.gestaoescalas.notification.service;

import com.gestaoescalas.notification.dto.ShiftEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Serviço responsável pelo processamento dos eventos de turno
 * recebidos através do RabbitMQ.
 */
@Slf4j
@Service
public class NotificationService {

    /**
     * Processa um evento de turno e registra suas informações
     * para fins de auditoria.
     *
     * @param event evento recebido do schedule-service.
     */
    public void processEvent(ShiftEventDTO event) {

        log.info(
                "shift event received | Type: {} | Shift ID: {} | Previous Employee: {} | New Employee {} | OccurredAt: {}",
                event.getEventType(),
                event.getShiftId(),
                event.getPreviousEmployeeId(),
                event.getNewEmployeeId(),
                event.getOccurredAt()
        );
    }
}
