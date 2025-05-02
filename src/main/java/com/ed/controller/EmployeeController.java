package com.ed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ed.pojo.EmployeeEntity;
import com.ed.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee) {
        return service.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public EmployeeEntity updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity employee) {
        return service.updateEmployee(id, employee);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<EmployeeEntity> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }
}