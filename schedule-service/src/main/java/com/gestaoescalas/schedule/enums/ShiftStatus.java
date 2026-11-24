package com.gestaoescalas.schedule.enums;

/**
 * Define os possíveis estados de um turno dentro do sistema.
 */
public enum ShiftStatus {

    /** Indica que o turno está ativo e permanece válido na escala. */
    ACTIVE,

    /** Indica que o turno foi cancelado e não está mais vigente. */
    CANCELLED
}
