//package com.ed.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ed.pojo.EmployeeEntity;
//import com.ed.repo.EmployeeRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EmployeeService {
//
//    @Autowired
//    private EmployeeRepository repository;
//
//    @Transactional
//    public EmployeeEntity saveEmployee(EmployeeEntity employee) {
//        return repository.save(employee);
//    }
//
//    @Transactional
//    public EmployeeEntity updateEmployee(Long id, EmployeeEntity employee) {
//        Optional<EmployeeEntity> existingEmployee = repository.findById(id);
//        if (existingEmployee.isPresent()) {
//            EmployeeEntity updatedEmployee = existingEmployee.get();
//            updatedEmployee.setName(employee.getName());
//            updatedEmployee.setRole(employee.getRole());
//            return repository.save(updatedEmployee);
//        }
//        return null;
//    }
//
//    public List<EmployeeEntity> getAllEmployees() {
//        return repository.findAll();
//    }
//}
//
// 

package com.ed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ed.pojo.EmployeeEntity;
import com.ed.repo.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Transactional
    public EmployeeEntity saveEmployee(EmployeeEntity employee) {
        return repository.save(employee);
    }

    @Transactional
    public EmployeeEntity updateEmployee(Long id, EmployeeEntity employee) {
        Optional<EmployeeEntity> existingEmployee = repository.findById(id);
        if (existingEmployee.isPresent()) {
            EmployeeEntity updatedEmployee = existingEmployee.get();
            updatedEmployee.setName(employee.getName());
            updatedEmployee.setRole(employee.getRole());
            return repository.save(updatedEmployee);
        }
        return null;
    }

    public List<EmployeeEntity> getAllEmployees() {
        return repository.findAll();
    }

    // New method to get an employee by ID
    public EmployeeEntity getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }
}