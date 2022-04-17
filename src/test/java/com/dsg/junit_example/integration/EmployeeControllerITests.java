package com.dsg.junit_example.integration;

import com.dsg.junit_example.model.Employee;
import com.dsg.junit_example.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerITests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        employeeRepository.deleteAll();
    }

    @Test
    public void given_when_thenCreate() throws Exception {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("dsg")
                .lastName("do")
                .email("ehtjd33@gmail.com")
                .build();

        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // then - verify the output
        response
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        CoreMatchers.is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        CoreMatchers.is(employee.getLastName())))
                .andExpect(jsonPath("$.email",
                        CoreMatchers.is(employee.getEmail())));
    }

    @Test
    public void given_when_thenGetAll() throws Exception {
        // given - precondition or setup
        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(Employee.builder().firstName("fgh").lastName("fo").email("fog@test.com").build());
        listOfEmployees.add(Employee.builder().firstName("tony").lastName("stark").email("tony@test.com").build());
        employeeRepository.saveAll(listOfEmployees);

        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"));

        // then - verify the output
        response
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",
                        CoreMatchers.is(listOfEmployees.size())));
    }
}
