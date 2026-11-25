package com.gestaoescalas.auth.controller;

import com.gestaoescalas.auth.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller responsável por disponibilizar endpoints
 * relacionados à consulta de colaboradores.
 */
@RestController
@RequestMapping("/internal/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    /**
     * Verifica se um colaborador existe e está ativo.
     *
     * @param employeeId identificador do colaborador.
     * @return resposta indicando se o colaborador está ativo.
     */
    @GetMapping("{employeeId}/active")
    public ResponseEntity<Map<String, Boolean>> isEmployeeActive(
            @PathVariable Long employeeId){

        boolean active = employeeService.isEmployeeActive(employeeId);
        return ResponseEntity.ok(Map.of("active", active));
    }
}
