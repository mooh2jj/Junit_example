package com.dsg.junit_example.service;

import com.dsg.junit_example.exception.ResourceNotFoundException;
import com.dsg.junit_example.model.Employee;
import com.dsg.junit_example.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    void setup() {
        employee = Employee.builder()
                .id(1L)
                .firstName("dsg")
                .lastName("ddd")
                .email("ehtjd333@gmail.com")
                .build();
    }

    @Test
    public void given_when_then(){
        // given - precondition or setup

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
        assertThat(savedEmployee).isNotNull();

    }

    @Test
    public void given_when_thenThrowsException(){
        // given - precondition or setup
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

//        given(employeeRepository.save(employee))
//                .willReturn(employee);

        log.info("employeeRepository: {}", employeeRepository);
        log.info("employeeService: {}", employeeService);

        // when - action or the behaviour that we are going test
//        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, ()->{
//            employeeService.saveEmployee(employee);
//        });
        assertThatThrownBy(() -> {
            employeeService.saveEmployee(employee);
        }).isInstanceOf(ResourceNotFoundException.class);

        // then - verify the output
        verify(employeeRepository, never()).save(any(Employee.class));

    }

    @Test
    public void given_when_thenGetAll(){
        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("dsg")
                .lastName("ddd")
                .email("ehtjd333@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(List.of(employee, employee1));

        // when - action or the behaviour that we are going test
        List<Employee> employees = employeeService.getAllEmployees();

        // then - verify the output
        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(2);

    }

    @Test
    public void given_when_thenGetAll_negative(){
        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("dsg")
                .lastName("ddd")
                .email("ehtjd333@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        // when - action or the behaviour that we are going test
        List<Employee> employees = employeeService.getAllEmployees();

        // then - verify the output
        assertThat(employees).isEmpty();
        assertThat(employees.size()).isEqualTo(0);

    }

    @Test
    public void given_when_thenGetById(){
        // given - precondition or setup
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        // when - action or the behaviour that we are going test
        Employee getEmployee = employeeService.getEmployeeById(this.employee.getId());

        // then - verify the output
        assertThat(getEmployee).isNotNull();

    }

    @Test
    public void given_when_thenUpdate(){
        // given - precondition or setup
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setEmail("kmv@test.com");
        employee.setFirstName("kkk");
        // when - action or the behaviour that we are going test
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        // then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("kmv@test.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("kkk");

    }


}