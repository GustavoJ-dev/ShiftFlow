package com.gestaoescalas.notification.enums;

/**
 * Define os tipos de eventos relacionados ao gerenciamento
 * de turnos que são publicados e processados pelo sistema.
 */
public enum ShiftEventType {

    /** Indica que um novo turno foi criado. */
    SHIFT_CREATED,

    /** Indica que um turno foi cancelado. */
    SHIFT_CANCELLED,

    /** Indica que uma solicitação de troca de turno foi aprovada. */
    SWAP_APPROVED
}
