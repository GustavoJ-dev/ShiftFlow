package com.gestaoescalas.notification.consumer;

import com.gestaoescalas.notification.dto.ShiftEventDTO;
import com.gestaoescalas.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * Consumidor responsável por receber os eventos de turno
 * publicados pelo schedule-service através do RabbitMQ.
 */
@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    /**
     * Recebe mensagens da fila configurada e encaminha
     * os eventos para processamento.
     *
     * @param event evento de turno recebido do RabbitMQ.
     */
    @RabbitListener(queues = "${app.messaging.queue}")
    public void receiveEvent(ShiftEventDTO event){

        notificationService.processEvent(event);
    }
}
