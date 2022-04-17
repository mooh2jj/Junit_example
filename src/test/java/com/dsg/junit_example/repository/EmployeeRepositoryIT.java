package com.dsg.junit_example.repository;

import com.dsg.junit_example.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryIT {

    @Autowired
    EmployeeRepository employeeRepository;

    Employee employee;

    @BeforeEach
    void setup() {
        employee = Employee.builder()
                .lastName("ddd")
                .firstName("sg")
                .email("ehtjd33@gmail.com")
                .build();
    }

    @Test
    void saveTest() {
        // given
//        Employee employee = Employee.builder()
//                .lastName("d")
//                .firstName("sg")
//                .email("ehtjd33@gmail.com")
//                .build();

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
//        Employee employee1 = Employee.builder()
//                .lastName("dg")
//                .firstName("sgg")
//                .email("sgg@gmail.com")
//                .build();

        Employee employee2 = Employee.builder()
                .lastName("dg22")
                .firstName("sgg22")
                .email("sgg22@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        // when
        List<Employee> employees = employeeRepository.findAll();

        // then
//        System.out.println("empoyees: "+ employees.toString());
        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(2);
    }

    @Test
    void getById() {

        // given
//        Employee employee = Employee.builder()
//                .lastName("d")
//                .firstName("sg")
//                .email("ehtjd33@gmail.com")
//                .build();

        employeeRepository.save(employee);

        // when
        Employee employee1 = employeeRepository.findById(employee.getId()).get();

        // then
        assertThat(employee1).isNotNull();

    }

    @Test
    void getByEmail() {
        // given
//        Employee employee = Employee.builder()
//                .lastName("d4")
//                .firstName("sg4")
//                .email("ehtjd34@gmail.com")
//                .build();

        employeeRepository.save(employee);

        // when
        Employee employee1 = employeeRepository.findByEmail("rash@test.com").get();

        // then
        assertThat(employee1).isNotNull();
    }

    @Test
    void updateTest() {
        // given
//        Employee employee = Employee.builder()
//                .lastName("d4")
//                .firstName("sg4")
//                .email("ehtjd34@gmail.com")
//                .build();

        employeeRepository.save(employee);

        // when
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("rash@test.com");

        Employee updatedEmployee = employeeRepository.save(savedEmployee);
        System.out.println("updatedEmployee: "+ updatedEmployee);
        Employee employee1 = employeeRepository.findByEmail(savedEmployee.getEmail()).get();
        System.out.println("savedEmployee: "+ savedEmployee);
        System.out.println("employee1: "+ employee1);
        // then
//        assertThat(updatedEmployee.getEmail()).isEqualTo("rash@test.com");

    }

    @Test
    void deleteTest() {
        // given
//        Employee employee = Employee.builder()
//                .lastName("d4")
//                .firstName("sg4")
//                .email("ehtjd34@gmail.com")
//                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        // when
        employeeRepository.deleteById(savedEmployee.getId());

        Optional<Employee> deletedEmployeeOptional = employeeRepository.findById(savedEmployee.getId());

        // then
        assertThat(deletedEmployeeOptional).isEmpty();
    }

    @Test
    void JPQLTest() {
        // given
//        Employee employee = Employee.builder()
//                .lastName("d45")
//                .firstName("sg45")
//                .email("ehtjd34@gmail.com")
//                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        String firstName = "ddd";
        String lastName = "sg";

        // when
        // 반환값은 List로! 단순 엔티티 반환안됨!
        List<Employee> byJPQLIndex = employeeRepository.findByJPQLIndex(firstName, lastName);
        List<Employee> byJPQL = employeeRepository.findByJPQL(firstName, lastName);

        // then
        assertThat(byJPQLIndex).isNotNull();
        assertThat(byJPQL).isNotNull();
    }

    @Test
    void NativeQueryTest() {
        // given
//        Employee employee = Employee.builder()
//                .lastName("d45")
//                .firstName("sg45")
//                .email("ehtjd34@gmail.com")
//                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        String firstName = "ddd";
        String lastName = "sg";

        // when
        // 반환값은 List로! 단순 엔티티 반환안됨!
        List<Employee> byNativeQuery = employeeRepository.findByNativeQuery();
//        List<Employee> byNativeQuery = employeeRepository.findByNativeQuery(firstName, lastName);

        // then
        assertThat(byNativeQuery).isNotNull();
    }
}