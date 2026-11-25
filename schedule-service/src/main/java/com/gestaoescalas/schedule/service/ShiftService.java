package com.gestaoescalas.schedule.service;

import com.gestaoescalas.schedule.client.EmployeeClient;
import com.gestaoescalas.schedule.dto.ShiftCreateRequestDTO;
import com.gestaoescalas.schedule.entity.Shift;
import com.gestaoescalas.schedule.enums.ShiftStatus;
import com.gestaoescalas.schedule.repository.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Classe responsável por gerenciar turnos e aplicar suas
 * regras relacionadas as escalas*/
@Service
@RequiredArgsConstructor
public class ShiftService {

    private final EmployeeClient employeeClient;
    private final ShiftRepository shiftRepository;

    public Shift createShift(ShiftCreateRequestDTO requestDTO){

        if (!requestDTO.endAt().isAfter(requestDTO.startAt())){

            throw new IllegalArgumentException("O horário de término deve ser posterior ao horário de início.");
        }

        if (!employeeClient.isEmployeeActive(requestDTO.employeeId())){

            throw new RuntimeException("Colaborador não encontrado ou inativo.");
        }

        var hasScheduleConflict = shiftRepository
                .existsByEmployeeIdAndStatusAndStartAtLessThanAndEndAtGreaterThan(
                        requestDTO.employeeId(),
                        ShiftStatus.ACTIVE,
                        requestDTO.endAt(),
                        requestDTO.startAt());

        if (hasScheduleConflict){

            throw new RuntimeException("Esse funcionário já opera um turno nesse horário");
        }

        Shift shift = new Shift(
                requestDTO.employeeId(),
                requestDTO.startAt(),
                requestDTO.endAt()
        );

        return shiftRepository.save(shift);
    }
}
