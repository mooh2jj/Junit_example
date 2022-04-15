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
    void getAllTest() {
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

    @Test
    void getById() {

        // given
        Employee employee = Employee.builder()
                .lastName("d")
                .firstName("sg")
                .email("ehtjd33@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when
        Employee employee1 = employeeRepository.findById(employee.getId()).get();

        // then
        assertThat(employee1).isNotNull();

    }

    @Test
    void getByEmail() {
        // given
        Employee employee = Employee.builder()
                .lastName("d4")
                .firstName("sg4")
                .email("ehtjd34@gmail.com")
                .build();

        employeeRepository.save(employee);

        // when
        Employee employee1 = employeeRepository.findByEmail(employee.getEmail()).get();

        // then
        assertThat(employee1).isNotNull();
    }
}