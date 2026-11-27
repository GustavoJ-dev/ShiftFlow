package com.gestaoescalas.schedule.dto;

/**
 * Representa a resposta recebida do auth-service
 * sobre o status de um colaborador.
 */
public record EmployeeStatusResponseDTO(

        boolean active
) {
}
