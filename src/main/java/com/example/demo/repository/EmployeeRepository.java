package com.example.demo.repository;

import com.example.demo.entity.EmployeeTbl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeTbl, Long> {

    List<EmployeeTbl> findByName(String name);

    List<EmployeeTbl> findByNameAndDepartment(String name, String department);
}