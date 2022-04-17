package com.dsg.junit_example.service;

import com.dsg.junit_example.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(Long id);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Long id);

    Optional<Employee> updateEmployee(Employee employee, Long employeeId);
}
