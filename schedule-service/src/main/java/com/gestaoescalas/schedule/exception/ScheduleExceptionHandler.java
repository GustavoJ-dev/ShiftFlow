package com.gestaoescalas.schedule.exception;

import com.gestaoescalas.schedule.dto.ScheduleExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Centraliza o tratamento das exceções de negócio do schedule-service,
 * convertendo-as em respostas HTTP padronizadas.
 */
@RestControllerAdvice
public class ScheduleExceptionHandler {

    //Representa um HttpStatusCode
    private final static int UNPROCESSABLE_ENTITY = 422;


    /**
     * Trata exceções genéricas relacionadas às regras de negócio do schedule-service,
     * funcionando como fallback quando não existe um tratamento mais específico.
     *
     * @param exception exceção base que representa uma violação de regra de negócio
     * @return resposta HTTP 400 com os detalhes do erro
     */
    @ExceptionHandler(ScheduleException.class)
    public ResponseEntity<ScheduleExceptionResponseDTO> handleScheduleException(
            ScheduleException exception) {

        var response = new ScheduleExceptionResponseDTO(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Trata exceções relacionadas a períodos de turno inválidos,
     * quando o horário de término não é posterior ao horário de início.
     *
     * @param exception exceção que representa a invalidade do período informado
     * @return resposta HTTP 400 com os detalhes do erro
     */
    @ExceptionHandler(InvalidShiftPeriodException.class)
    public ResponseEntity<ScheduleExceptionResponseDTO> handleInvalidShiftPeriod(
            InvalidShiftPeriodException exception){

        var response = new ScheduleExceptionResponseDTO(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Trata exceções relacionadas a colaboradores que não existem
     * ou que não estão ativos no sistema.
     *
     * @param exception exceção que representa a ausência ou inatividade do colaborador
     * @return resposta HTTP 422 com os detalhes do erro
     */
    @ExceptionHandler(EmployeeNotActiveException.class)
    public ResponseEntity<ScheduleExceptionResponseDTO> handleEmployeeNotActive(
            EmployeeNotActiveException exception){

        var response = new ScheduleExceptionResponseDTO(
                exception.getMessage(),
                HttpStatusCode.valueOf(UNPROCESSABLE_ENTITY).value()
        );

        return ResponseEntity.unprocessableContent().body(response);
    }

    /**
     * Trata conflitos de horário durante a criação ou alteração de turnos.
     *
     * @param exception exceção que representa o conflito identificado
     * @return resposta HTTP 409 com os detalhes do erro
     */
    @ExceptionHandler(ShiftConflictException.class)
    public ResponseEntity<ScheduleExceptionResponseDTO> handleShiftConflict(
            ShiftConflictException exception){

        var response = new ScheduleExceptionResponseDTO(
                exception.getMessage(),
                HttpStatus.CONFLICT.value()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
