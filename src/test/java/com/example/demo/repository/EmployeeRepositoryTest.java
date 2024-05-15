package com.example.demo.repository;

import com.example.demo.entity.EmployeeTbl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeAll
    public static void setUp(@Autowired EmployeeRepository employeeRepository) {
        // Given
        EmployeeTbl employee1 = new EmployeeTbl();
        employee1.setId(1L);
        employee1.setName("John");
        employeeRepository.save(employee1);

        EmployeeTbl employee2 = new EmployeeTbl();
        employee2.setId(2L);
        employee2.setName("Jane");
        employeeRepository.save(employee2);

        EmployeeTbl employee3 = new EmployeeTbl();
        employee3.setId(3L);
        employee3.setName("John Doe");
        employeeRepository.save(employee3);
    }

    @Test
    void findAllTest() {
        assertEquals(3, employeeRepository.findAll().size());
    }

    @Test
    void findByIdTest() {
        log.info("testFindById - start");
        EmployeeTbl employee = employeeRepository.findById(1L).get();
        log.info("testFindById - employee: {}", employee);
        assertEquals("John", employee.getName());
        log.info("testFindById - end");
    }

    @Test
    void findByNameTest() {
        assertEquals(1, employeeRepository.findByName("Jane").size());
    }
}