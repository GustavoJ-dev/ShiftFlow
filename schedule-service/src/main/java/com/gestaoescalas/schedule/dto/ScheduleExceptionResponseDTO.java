package com.gestaoescalas.schedule.dto;

/**
 * DTO responsável por representar o corpo das respostas de erro
 * retornadas pelo ScheduleExceptionHandler.
 *
 * @param message descrição do erro ocorrido
 * @param status código numérico do status HTTP retornado
 */
public record ScheduleExceptionResponseDTO(

        String message,
        int status
) {
}
