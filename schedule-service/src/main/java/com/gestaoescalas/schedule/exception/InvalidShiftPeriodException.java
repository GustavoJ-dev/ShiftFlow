package com.gestaoescalas.schedule.exception;

/**
 * Exceção lançada quando o período informado para a criação
 * de um turno é inválido, como quando o horário de término
 * não é posterior ao horário de início.
 *
 * @see ScheduleException
 */
public class InvalidShiftPeriodException extends ScheduleException {

    public InvalidShiftPeriodException(String message) {
        super(message);
    }
}
