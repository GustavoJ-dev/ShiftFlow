package com.gestaoescalas.schedule.repository;

import com.gestaoescalas.schedule.entity.Shift;
import com.gestaoescalas.schedule.enums.ShiftStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

/**
 * Repository responsável pelo acesso e gerenciamento
 * dos dados dos turnos no banco de dados
 */
public interface ShiftRepository extends JpaRepository<Shift, Long> {

    /**
     * Verifica se um colaborador possui um turno ativo
     * que apresenta sobreposição com período informado
     *
     * @param employeeId identificador do colaborador
     * @param status estado do turno
     * @param requestedEndAt horário de término do período solicitado
     * @param requestedStartAt horário de início do período solicitado
     * @return  true se existir sobreposição; caso contrário, false.
     */

    boolean existsByEmployeeIdAndStatusAndStartAtLessThanAndEndAtGreaterThan(Long employeeId,
                                                                             ShiftStatus status,
                                                                             LocalDateTime requestedEndAt,
                                                                             LocalDateTime requestedStartAt);
}
