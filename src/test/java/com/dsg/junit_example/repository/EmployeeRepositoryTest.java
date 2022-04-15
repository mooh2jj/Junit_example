package com.dsg.junit_example.repository;

import com.dsg.junit_example.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository repository;

    @Test
    void saveTest() {
        // given
        Employee employee = Employee.builder()
                .lastName("d")
                .firstName("sg")
                .email("ehtjd33@gmail.com")
                .build();

        // when
        Employee savedEmployee = repository.save(employee);

        // then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
        assertThat(savedEmployee.getEmail()).isEqualTo("ehtjd33@gmail.com");

    }
}