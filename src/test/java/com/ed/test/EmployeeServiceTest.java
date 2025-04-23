//package com.ed.test;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.ed.pojo.EmployeeEntity;
//import com.ed.repo.EmployeeRepository;
//import com.ed.service.EmployeeService;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class EmployeeServiceTest {
//	
//    @Mock
//    private EmployeeRepository repository;
//
//    @InjectMocks
//    private EmployeeService service;
//
//    public EmployeeServiceTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testSaveEmployee() {
//        EmployeeEntity employee = new EmployeeEntity();
//        employee.setName("mohan");
//        employee.setRole("Developer");
//
//        when(repository.save(any(EmployeeEntity.class))).thenReturn(employee);
//
//        EmployeeEntity savedEmployee = service.saveEmployee(employee);
//        assertNotNull(savedEmployee);
//        assertEquals("mohan", savedEmployee.getName());
//    }
//
//    @Test
//    public void testGetAllEmployees() {
//        EmployeeEntity employee1 = new EmployeeEntity();
//        employee1.setName("mohan");
//        employee1.setRole("Developer");
//
//        EmployeeEntity employee2 = new EmployeeEntity();
//        employee2.setName("manju");
//        employee2.setRole("Manager");
//
//        List<EmployeeEntity> employees = Arrays.asList(employee1, employee2);
//
//        when(repository.findAll()).thenReturn(employees);
//
//        List<EmployeeEntity> result = service.getAllEmployees();
//        assertEquals(2, result.size());
//    }
//}

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
        employee.setName("mohan");
        employee.setRole("Developer");

        when(repository.save(any(EmployeeEntity.class))).thenReturn(employee);

        EmployeeEntity savedEmployee = service.saveEmployee(employee);
        assertNotNull(savedEmployee);
        assertEquals("mohan", savedEmployee.getName());
    }

    @Test
    public void testGetAllEmployees() {
        EmployeeEntity employee1 = new EmployeeEntity();
        employee1.setName("mohan");
        employee1.setRole("Developer");

        EmployeeEntity employee2 = new EmployeeEntity();
        employee2.setName("manju");
        employee2.setRole("Manager");

        List<EmployeeEntity> employees = Arrays.asList(employee1, employee2);

        when(repository.findAll()).thenReturn(employees);

        List<EmployeeEntity> result = service.getAllEmployees();
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateEmployee() {
        Long employeeId = 1L;

        EmployeeEntity existingEmployee = new EmployeeEntity();
        existingEmployee.setId(employeeId);
        existingEmployee.setName("mohan");
        existingEmployee.setRole("Developer");

        EmployeeEntity updatedEmployee = new EmployeeEntity();
        updatedEmployee.setName("manju");
        updatedEmployee.setRole("Manager");

        when(repository.findById(employeeId)).thenReturn(java.util.Optional.of(existingEmployee));
        when(repository.save(any(EmployeeEntity.class))).thenReturn(updatedEmployee);

        EmployeeEntity result = service.updateEmployee(employeeId, updatedEmployee);

        assertNotNull(result);
        assertEquals("manju", result.getName());
        assertEquals("Manager", result.getRole());
    }

    @Test
    public void testGetEmployeeById() {
        Long employeeId = 1L;

        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(employeeId);
        employee.setName("mohan");
        employee.setRole("Developer");

        when(repository.findById(employeeId)).thenReturn(java.util.Optional.of(employee));

        EmployeeEntity result = service.getEmployeeById(employeeId);

        assertNotNull(result);
        assertEquals("mohan", result.getName());
        assertEquals("Developer", result.getRole());
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        Long employeeId = 1L;

        when(repository.findById(employeeId)).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.getEmployeeById(employeeId);
        });

        assertEquals("Employee not found with ID: " + employeeId, exception.getMessage());
    }

    @Test
    public void testGetAllEmployeesWhenEmpty() {
        when(repository.findAll()).thenReturn(Arrays.asList());

        List<EmployeeEntity> result = service.getAllEmployees();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}