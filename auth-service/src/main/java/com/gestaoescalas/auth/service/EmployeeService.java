package com.gestaoescalas.auth.service;

import com.gestaoescalas.auth.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service responsável por gerenciar os colaboradores
 * e aplicar suas regras relacionadas.
 */
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Verifica se um colaborador existe e está ativo no sistema.
     *
     * @param employeeId identificador do colaborador.
     * @return true se o colaborador existir e estiver ativo.
     */

    public boolean isEmployeeActive(Long employeeId){

        return employeeRepository.existsByIdAndActiveTrue(employeeId);
    }
}
