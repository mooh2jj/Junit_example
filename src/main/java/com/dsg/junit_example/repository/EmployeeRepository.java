package com.dsg.junit_example.repository;

import com.dsg.junit_example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    @Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    List<Employee> findByJPQLIndex(String firstName, String lastName);

    @Query("select e from Employee e where e.firstName = :firstName and e.lastName = :lastName")
    List<Employee> findByJPQL(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // NativeQuery
    @Query(value = "select * from employees e", nativeQuery = true)
    List<Employee> findByNativeQuery();

    // 오류뜸
    @Query(value = "select * from employees e where e.firstName = ?1 and e.lastName = ?2", nativeQuery = true)
    List<Employee> findByNativeQuery(String firstName, String lastName);
}
