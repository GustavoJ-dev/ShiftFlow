package com.gestaoescalas.schedule.enums;

/**
 * Define os possíveis estados de uma solicitação de troca de turno.
 */
public enum SwapStatus {

    /** Indica que a solicitação aguarda a análise de um administrador. */
    PENDING,

    /** Indica que a solicitação foi aprovada por um administrador. */
    APPROVED,

    /** Indica que a solicitação foi recusada. */
    REJECTED
}
