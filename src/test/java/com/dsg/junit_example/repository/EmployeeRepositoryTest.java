package com.dsg.junit_example.repository;

import com.dsg.junit_example.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Rollback(value = false)
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void saveTest() {
        // given
        Employee employee = Employee.builder()
                .lastName("d")
                .firstName("sg")
                .email("ehtjd33@gmail.com")
                .build();

        // when
        Employee savedEmployee = employeeRepository.save(employee);

        // then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
        assertThat(savedEmployee.getEmail()).isEqualTo("ehtjd33@gmail.com");

    }

    @Test
    void getTest() {
        // given
        Employee employee1 = Employee.builder()
                .lastName("dg")
                .firstName("sgg")
                .email("sgg@gmail.com")
                .build();

        Employee employee2 = Employee.builder()
                .lastName("dg22")
                .firstName("sgg22")
                .email("sgg22@gmail.com")
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        // when
        List<Employee> employees = employeeRepository.findAll();

        // then
//        System.out.println("empoyees: "+ employees.toString());
        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(3);
    }
}