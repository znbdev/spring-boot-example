package com.example.demo.service;

import com.example.demo.entity.EmployeeTbl;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    EmployeeTbl employee1;
    EmployeeTbl employee2;

    @BeforeEach
    public void setUp() {
        // Given
        employee1 = new EmployeeTbl();
        employee1.setId(1L);
        employee1.setName("John");
        employee2 = new EmployeeTbl();
        employee2.setId(2L);
        employee2.setName("John");
    }

    @Test
    void findEmployeesByNameTest() {

        // 模拟数据
        List<EmployeeTbl> expectedEmployees = Arrays.asList(employee1, employee2);

        String name = "John";


        // 预期行为
        when(employeeRepository.findByName(name)).thenReturn(expectedEmployees);

        // When
        List<EmployeeTbl> actualEmployees = employeeService.findEmployeesByName(name);

        // Then
        assertEquals(expectedEmployees.size(), actualEmployees.size());
        for (int i = 0; i < expectedEmployees.size(); i++) {
            assertEquals(expectedEmployees.get(i).getId(), actualEmployees.get(i).getId());
            assertEquals(expectedEmployees.get(i).getName(), actualEmployees.get(i).getName());
            // Add more assertions if needed for other fields
        }
        verify(employeeRepository, times(1)).findByName(name);
    }

    @Test
    void saveIfNotExist_existingEmployee() {
        // Given
        EmployeeTbl existingEmployee = new EmployeeTbl();
        existingEmployee.setId(1L);
        existingEmployee.setName("John");
        existingEmployee.setDepartment("IT");
        when(employeeRepository.findByNameAndDepartment("John", "IT")).thenReturn(Collections.singletonList(existingEmployee));

        // When
        EmployeeTbl newEmployee = new EmployeeTbl();
        newEmployee.setName("John");
        newEmployee.setDepartment("IT");
        employeeService.saveIfNotExist(newEmployee);

        // Then
        verify(employeeRepository, never()).save(any(EmployeeTbl.class));
    }

    @Test
    void saveIfNotExist_newEmployee() {
        // Given
        when(employeeRepository.findByNameAndDepartment("John", "IT")).thenReturn(Collections.emptyList());

        // When
        EmployeeTbl newEmployee = new EmployeeTbl();
        newEmployee.setName("John");
        newEmployee.setDepartment("IT");
        employeeService.saveIfNotExist(newEmployee);

        // Then
        verify(employeeRepository, times(1)).save(newEmployee);
    }

    @Test
    void findAllTest() {
        // Given
        List<EmployeeTbl> expectedEmployees = Arrays.asList(employee1, employee2);

        // 预期行为
        when(employeeRepository.findAll()).thenReturn(expectedEmployees);

        // When
        List<EmployeeTbl> actualEmployees = employeeService.findAll();

        // Then
        assertEquals(expectedEmployees.size(), actualEmployees.size());
        for (int i = 0; i < expectedEmployees.size(); i++) {
            assertEquals(expectedEmployees.get(i).getId(), actualEmployees.get(i).getId());
            // Add more assertions if needed for other fields
        }
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void findByIdTest() {
        // Given
        Long id = 1L;
        EmployeeTbl expectedEmployee = new EmployeeTbl();
        expectedEmployee.setId(id);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(expectedEmployee));

        // When
        Optional<EmployeeTbl> actualEmployeeOptional = employeeService.findById(id);

        // Then
        assertEquals(expectedEmployee.getId(), actualEmployeeOptional.orElseThrow().getId());
        // Add more assertions if needed for other fields
        verify(employeeRepository, times(1)).findById(id);
    }

    @Test
    void saveTest() {
        // Given
        EmployeeTbl employee = new EmployeeTbl();
        employee.setId(1L);
        when(employeeRepository.save(employee)).thenReturn(employee);

        // When
        EmployeeTbl savedEmployee = employeeService.save(employee);

        // Then
        assertEquals(employee.getId(), savedEmployee.getId());
        // Add more assertions if needed for other fields
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void updateTest() {
        // Given
        EmployeeTbl employee = new EmployeeTbl();
        employee.setId(1L);
        when(employeeRepository.save(employee)).thenReturn(employee);

        // When
        EmployeeTbl updatedEmployee = employeeService.update(employee);

        // Then
        assertEquals(employee.getId(), updatedEmployee.getId());
        // Add more assertions if needed for other fields
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void deleteById() {
        // Given
        Long id = 1L;

        // When
        employeeService.deleteById(id);

        // Then
        verify(employeeRepository, times(1)).deleteById(id);
    }
}