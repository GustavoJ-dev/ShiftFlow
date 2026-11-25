package com.gestaoescalas.auth.repository;

import com.gestaoescalas.auth.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository responsável pelo acesso e gerenciamento
 * dos dados dos colaboradores.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Verifica se existe um colaborador com o identificador informado
     * e que esteja ativo no sistema.
     *
     * @param id identificador do colaborador.
     * @return true se o colaborador existir e estiver ativo.
     */
    boolean existsByIdAndActiveTrue(Long id);
}
