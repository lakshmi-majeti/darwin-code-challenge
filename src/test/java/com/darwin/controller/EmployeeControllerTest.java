package com.darwin.controller;

import com.darwin.exception.ResourceNotFoundException;
import com.darwin.model.Employee;
import com.darwin.service.EmployeeService;
import com.darwin.utils.UtilOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private Employee mockEmployee;
    private Employee mockEmployee2;
    private List<Employee> mockEmployeeList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        mockEmployee = new Employee(10, "full name", "test@gmail.com", "password", "1234567890",
                "dept", "title");
        mockEmployee2 = new Employee(11, "full name2", "test2@gmail.com", "password2", "1234567890",
                "dept2", "title2");
        mockEmployeeList.add(mockEmployee);
        mockEmployeeList.add(mockEmployee2);
    }

    @Test
    public void shouldCreateEmployee() throws Exception {
        String createEmployeeJson =  UtilOperations.readFileAsString("json/createEmployee.json");
        when(employeeService.saveEmployee(any())).thenReturn(mockEmployee);
        MvcResult employeeResult = this.mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createEmployeeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName", is("full name")))
                .andExpect(jsonPath("$.email", is("test@gmail.com")))
                .andExpect(jsonPath("$.department", is("dept")))
                .andReturn();
    }

    @Test
    public void shouldGetEmployeeValidInput() throws Exception {
        when(employeeService.getEmployee(any(Integer.class))).thenReturn(mockEmployee);
        MvcResult employeeResult = this.mockMvc.perform(get("/api/employees/{id}", mockEmployee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10)))
                .andReturn();
    }

    @Test
    public void getEmployeeReturnNotFoundException_nonexsitent_input() throws Exception {
        when(employeeService.getEmployee(any(Integer.class))).thenThrow(ResourceNotFoundException.class);
        MvcResult employeeResult = this.mockMvc.perform(get("/api/employees/{id}", 150))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void shouldReturnAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(mockEmployeeList);
        MvcResult employeeResult = this.mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteEmployeeShouldDeleteValidInput() throws Exception {
        MvcResult employeeResult = this.mockMvc.perform(delete("/api/employees/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Disabled
    public void deleteEmployeeReturnNotFoundException_nonexsitent_input() throws Exception {
        MvcResult employeeResult = this.mockMvc.perform(delete("/api/employees/{id}", 150))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
