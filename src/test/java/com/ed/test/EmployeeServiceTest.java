package com.ed.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ed.pojo.EmployeeEntity;
import com.ed.repo.EmployeeRepository;
import com.ed.service.EmployeeService;

import java.util.Arrays;
import java.util.List;

public class EmployeeServiceTest {
	
    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    public EmployeeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEmployee() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName("John Doe");
        employee.setRole("Developer");

        when(repository.save(any(EmployeeEntity.class))).thenReturn(employee);

        EmployeeEntity savedEmployee = service.saveEmployee(employee);
        assertNotNull(savedEmployee);
        assertEquals("John Doe", savedEmployee.getName());
    }

    @Test
    public void testGetAllEmployees() {
        EmployeeEntity employee1 = new EmployeeEntity();
        employee1.setName("John Doe");
        employee1.setRole("Developer");

        EmployeeEntity employee2 = new EmployeeEntity();
        employee2.setName("Jane Doe");
        employee2.setRole("Manager");

        List<EmployeeEntity> employees = Arrays.asList(employee1, employee2);

        when(repository.findAll()).thenReturn(employees);

        List<EmployeeEntity> result = service.getAllEmployees();
        assertEquals(2, result.size());
    }
}
