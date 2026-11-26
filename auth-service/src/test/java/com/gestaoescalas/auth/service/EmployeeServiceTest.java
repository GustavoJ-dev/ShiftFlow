package com.gestaoescalas.auth.service;

import com.gestaoescalas.auth.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void shouldReturnTrueWhenEmployeeIsActive(){

        when(employeeRepository.existsByIdAndActiveTrue(1L)).thenReturn(true);

        boolean result = employeeService.isEmployeeActive(1L);

        assertTrue(result);

        verify(employeeRepository).existsByIdAndActiveTrue(1L);
        verify(employeeRepository, times(1))
                .existsByIdAndActiveTrue(1L);
    }

    @Test
    void shouldReturnFalseWhenEmployeeIsNotActive(){

        when(employeeRepository.existsByIdAndActiveTrue(2L)).thenReturn(false);

        boolean result = employeeService.isEmployeeActive(2L);

        assertFalse(result);

        verify(employeeRepository).existsByIdAndActiveTrue(2L);
    }
}
