package com.example.demo.service;

import com.example.demo.entity.EmployeeTbl;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService implements CrudService<EmployeeTbl, Long> {
    private final EmployeeRepository employeeRepository;

    public List<EmployeeTbl> findEmployeesByName(String name) {
        return employeeRepository.findByName(name);
    }

    public void saveIfNotExist(EmployeeTbl entity) {
        List<EmployeeTbl> existingEmployee = employeeRepository.findByNameAndDepartment(entity.getName(), entity.getDepartment());
        if (existingEmployee.isEmpty()) {
            employeeRepository.save(entity);
        }
    }

    @Override
    public List<EmployeeTbl> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<EmployeeTbl> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public EmployeeTbl save(EmployeeTbl entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public EmployeeTbl update(EmployeeTbl entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
