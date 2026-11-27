package com.gestaoescalas.schedule.exception;

/**
 * Exceção específica lançada quando um colaborador já possui
 * um turno ativo que conflita com o período solicitado.
 *
 * @see ScheduleException
 */
public class ShiftConflictException extends ScheduleException {

    public ShiftConflictException(String message) {
        super(message);
    }
}
