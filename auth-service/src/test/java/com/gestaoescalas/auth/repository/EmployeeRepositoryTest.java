package com.gestaoescalas.auth.repository;

import com.gestaoescalas.auth.entity.Employee;
import com.gestaoescalas.auth.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee createEmployee(){

        Employee employee = new Employee(
                "Gustavo",
                "gustavo@gmail.com",
                "senha123",
                Role.ADMIN,
                true
        );

        return employee;
    }

    @Test
    void shouldSaveEmployee(){

        Employee employee = createEmployee();

        Employee savedEmployee = employeeRepository.save(employee);

        assertNotNull(savedEmployee.getId());
        assertEquals("Gustavo", savedEmployee.getName());
        assertEquals("gustavo@gmail.com", savedEmployee.getEmail());
        assertEquals(Role.ADMIN, savedEmployee.getRole());
        assertTrue(savedEmployee.isActive());
    }

    @Test
    void shouldPersistEmployeeRole(){

        Employee employee = createEmployee();

        Employee savedEmployee = employeeRepository.save(employee);

        assertEquals(Role.ADMIN, savedEmployee.getRole());
    }

    @Test
    void shouldAutomaticallySetCreationAndUpdateDates(){

        Employee employee = createEmployee();

        Employee savedEmployee = employeeRepository.save(employee);

        assertNotNull(savedEmployee.getCreatedAt());
        assertNotNull(savedEmployee.getUpdatedAt());

        assertEquals(savedEmployee.getCreatedAt(), savedEmployee.getUpdatedAt());
    }

    @Test
    void shouldFindActiveEmployee(){

        Employee employee = createEmployee();

        Employee savedEmployee = employeeRepository.save(employee);

        boolean result = employeeRepository.existsByIdAndActiveTrue(savedEmployee.getId());

        assertTrue(result);
    }

    @Test
    void shouldNotFindInactiveEmployee(){

        Employee employee = createEmployee();
        employee.setActive(false);

        Employee savedEmployee = employeeRepository.save(employee);

        boolean result = employeeRepository.existsByIdAndActiveTrue(savedEmployee.getId());

        assertFalse(result);
    }
}
