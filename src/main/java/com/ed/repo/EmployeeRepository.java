package com.ed.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ed.pojo.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>  {

}
