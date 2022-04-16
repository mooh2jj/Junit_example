package com.dsg.junit_example.service;

import com.dsg.junit_example.exception.ResourceNotFoundException;
import com.dsg.junit_example.model.Employee;
import com.dsg.junit_example.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        savedEmployee.ifPresent( e -> {
            throw new ResourceNotFoundException("이미 같은 직원 이메일이 존재합니다. : " + employee.getEmail());
        });
//        if (savedEmployee.isPresent()) {        // 같은 이메일이 존재한다면
//            throw new ResourceNotFoundException("이미 같은 직원 이메일이 존재합니다. : " + employee.getEmail());
//        }

//        Employee savedEmployee = employeeRepository.findByEmail(employee.getEmail())
//                .orElseThrow(() -> new RuntimeException("문제 발생"));  // 무조건 오류 생김!


        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
}
