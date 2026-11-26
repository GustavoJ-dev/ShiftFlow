package com.gestaoescalas.auth.controller;


import com.gestaoescalas.auth.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    void shouldReturnTrueWhenEmployeeIsActive() throws Exception {

        when(employeeService.isEmployeeActive(1L)).thenReturn(true);

        mockMvc.perform(get("/internal/employees/1/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(true));

       verify(employeeService).isEmployeeActive(1L);
    }

    @Test
    void shouldReturnFalseWhenEmployeeIsInactive() throws Exception {

        when(employeeService.isEmployeeActive(2L)).thenReturn(false);

        mockMvc.perform(get("/internal/employees/2/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(false));

        verify(employeeService).isEmployeeActive(2L);
    }
}
