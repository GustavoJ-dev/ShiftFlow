
package com.gestaoescalas.notification.dto;

import com.gestaoescalas.notification.enums.ShiftEventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Representa os dados de um evento relacionado aos turnos,
 * recebido pelo notification-service através do RabbitMQ.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShiftEventDTO {

    /** Tipo de evento que identifica a operação realizada no turno. */
    private ShiftEventType eventType;

    /** Identificador do turno relacionado ao evento. */
    private Long shiftId;

    /** Identificador do colaborador anteriormente associado ao turno. */
    private Long previousEmployeeId;

    /** Identificador do colaborador atualmente associado ao turno. */
    private Long newEmployeeId;

    /** Data e horário de início do turno. */
    private LocalDateTime startAt;

    /** Data e horário de término do turno. */
    private LocalDateTime endAt;

    /** Data e horário em que o evento ocorreu. */
    private LocalDateTime occurredAt;
}
