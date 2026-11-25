package com.gestaoescalas.schedule.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

/**
 * DTO responsável por transportar os dados necessários
 * para a criação de um turno*/
public record ShiftCreateRequestDTO(

        @NotNull(message = "O ID do colaborador é obrigatório.")
        @Positive(message = "O ID do colaborador deve ser positivo.")
        Long employeeId,

        @NotNull(message = "O horário de início é obrigatório.")
        LocalDateTime startAt,

        @NotNull(message = "O horário de término é obrigatório.")
        LocalDateTime endAt
) {
}
