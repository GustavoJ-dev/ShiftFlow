package com.gestaoescalas.schedule.exception;

/**
 * Exceção base para representar erros relacionados às regras
 * de negócio do schedule-service.
 *
 * @see InvalidShiftPeriodException
 * @see EmployeeNotActiveException
 * @see ShiftConflictException
 */
public class ScheduleException extends RuntimeException {

    public ScheduleException(String message) {
        super(message);
    }
}
