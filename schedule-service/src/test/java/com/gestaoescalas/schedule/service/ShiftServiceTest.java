package com.gestaoescalas.schedule.service;

import com.gestaoescalas.schedule.client.EmployeeClient;
import com.gestaoescalas.schedule.dto.ShiftCreateRequestDTO;
import com.gestaoescalas.schedule.entity.Shift;
import com.gestaoescalas.schedule.enums.ShiftStatus;
import com.gestaoescalas.schedule.exception.EmployeeNotActiveException;
import com.gestaoescalas.schedule.exception.InvalidShiftPeriodException;
import com.gestaoescalas.schedule.exception.ShiftConflictException;
import com.gestaoescalas.schedule.repository.ShiftRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ShiftServiceTest {

    @Mock
    private EmployeeClient employeeClient;

    @Mock
    private ShiftRepository shiftRepository;

    @InjectMocks
    private ShiftService service;

    private ShiftCreateRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new ShiftCreateRequestDTO(
                1L,
                LocalDateTime.of(2026, 12, 10, 8, 0),
                LocalDateTime.of(2026, 12, 10, 12, 0)
        );
    }

    @Test
    void ShouldReturnInvalidShiftPeriodExceptionWhenEndAtIsNotAfterStartAt(){

        requestDTO = new ShiftCreateRequestDTO(
                1L,
                LocalDateTime.of(2026, 12, 10, 12, 0),
                LocalDateTime.of(2026, 12, 10, 8, 0)
        );


        assertThrows(InvalidShiftPeriodException.class,
                ()-> service.createShift(requestDTO));

        verifyNoInteractions(shiftRepository, employeeClient);

    }

    @Test
    void ShouldReturnEmployeeNotActiveException(){

        when(employeeClient.isEmployeeActive(1L)).thenReturn(false);

        assertThrows(EmployeeNotActiveException.class,
                () -> service.createShift(requestDTO));

        verifyNoInteractions(shiftRepository);
    }

    @Test
    void ShouldReturnShiftConflictException(){

        when(employeeClient.isEmployeeActive(1L)).thenReturn(true);

        when(shiftRepository.existsByEmployeeIdAndStatusAndStartAtLessThanAndEndAtGreaterThan(
                1L,
                ShiftStatus.ACTIVE,
                requestDTO.endAt(),
                requestDTO.startAt()
        )).thenReturn(true);

        assertThrows(ShiftConflictException.class,
                ()-> service.createShift(requestDTO));

        verify(shiftRepository, never()).save(any(Shift.class));

    }

    @Test
    void shouldCreateShift(){

        when(employeeClient.isEmployeeActive(1L)).thenReturn(true);

        when(shiftRepository.existsByEmployeeIdAndStatusAndStartAtLessThanAndEndAtGreaterThan(
                1L,
                ShiftStatus.ACTIVE,
                requestDTO.endAt(),
                requestDTO.startAt()
        )).thenReturn(false);

        when(shiftRepository.save(any(Shift.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Shift shift = service.createShift(requestDTO);

        assertNotNull(shift);
        assertEquals(requestDTO.employeeId(), shift.getEmployeeId());
        assertEquals(requestDTO.startAt(), shift.getStartAt());
        assertEquals(requestDTO.endAt(), shift.getEndAt());
        assertEquals(ShiftStatus.ACTIVE, shift.getStatus());

        verify(shiftRepository).save(any(Shift.class));
    }
}
