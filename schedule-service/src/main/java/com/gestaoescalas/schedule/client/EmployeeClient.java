package com.gestaoescalas.schedule.client;

import com.gestaoescalas.schedule.dto.EmployeeStatusResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * Client responsável pela comunicação HTTP com o auth-service
 * para consultar o status dos colaboradores.
 */
@Component
@RequiredArgsConstructor
public class EmployeeClient {

    private final RestClient authServiceRestClient;

    /**
     * Consulta se um colaborador existe e está ativo no auth-service.
     *
     * @param employeeId identificador do colaborador.
     * @return true se o colaborador estiver ativo; caso contrário, false.
     */
    public boolean isEmployeeActive(Long employeeId){

        EmployeeStatusResponseDTO responseDTO = authServiceRestClient
                .get()
                .uri("/internal/employees/{employeeId}/active", employeeId)
                .retrieve()
                .body(EmployeeStatusResponseDTO.class);

        return responseDTO != null && responseDTO.active();
    }
}
