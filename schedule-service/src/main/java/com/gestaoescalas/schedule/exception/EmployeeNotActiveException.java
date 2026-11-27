package com.gestaoescalas.schedule.exception;

/**
 * Exceção lançada quando o colaborador informado não existe
 * ou não está ativo no auth-service.
 *
 * @see ScheduleException
 */
public class EmployeeNotActiveException extends ScheduleException {

    public EmployeeNotActiveException(String message) {
        super(message);
    }
}
