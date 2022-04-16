package com.dsg.junit_example.service;

import com.dsg.junit_example.model.Employee;
import com.dsg.junit_example.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@Slf4j
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void given_when_then(){
        // given - precondition or setup
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("dsg")
                .lastName("ddd")
                .email("ehtjd333@gmail.com")
                .build();
//        given(employeeRepository.findByEmail(employee.getEmail()))
//                .willReturn(Optional.empty());

        given(employeeRepository.save(employee))
                .willReturn(employee);

        log.info("employeeRepository: {}", employeeRepository);
        log.info("employeeService: {}", employeeService);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeService.saveEmployee(employee);
        log.info("savedEmployee: {}", savedEmployee);

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();

    }


}