package com.dsg.junit_example.service;

import com.dsg.junit_example.exception.ResourceNotFoundException;
import com.dsg.junit_example.model.Employee;
import com.dsg.junit_example.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (savedEmployee.isPresent()) {        // 같은 이메일이 존재한다면
            throw new ResourceNotFoundException("이미 같은 직원 이메일이 존재합니다. : " + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }
}
