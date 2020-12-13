package com.darwin.service;

import com.darwin.exception.ResourceNotFoundException;
import com.darwin.model.Employee;
import com.darwin.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository mockedEmployeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee mockEmployee;
    private Employee mockEmployee2;
    private Employee mockInputEmployee;
    private List<Employee> mockEmployeeList = new ArrayList<>();

    @BeforeEach
    public void setUp(){

        mockInputEmployee = new Employee("full name", "test@gmail.com", "password", "1234567890",
                "dept", "title");

        mockEmployee = new Employee(10, "full name", "test@gmail.com", "password", "1234567890",
                "dept", "title");
        mockEmployee2 = new Employee(11, "full name2", "test2@gmail.com", "password2", "1234567890",
                "dept2", "title2");
        mockEmployeeList.add(mockEmployee);
        mockEmployeeList.add(mockEmployee2);
    }

    @Test
    public void createEmployeeSuccessTest(){
        Mockito.when(mockedEmployeeRepository.save(mockInputEmployee)).thenReturn(mockEmployee);
        Employee employeeResponse= employeeService.saveEmployee(mockInputEmployee);
        assertEquals(mockEmployee, employeeResponse);
    }

    @Test
    public void getEmployeeShouldReturnEmployeeGivenValidId() throws ResourceNotFoundException {
        Mockito.when(mockedEmployeeRepository.findById(10)).thenReturn((Optional.of(mockEmployee)));
        Employee employee= employeeService.getEmployee(10);
        assertEquals(employee, mockEmployee);
    }

    @Test
    public void getEmployeeShouldReturnNotFoundException() {
        Mockito.when(mockedEmployeeRepository.findById(any(Integer.class))).
                thenThrow( new ResourceNotFoundException("not found"));
        Assertions.assertThrows(ResourceNotFoundException.class,()->employeeService.getEmployee(100));
    }

    @Test
    public void getAllEmployeesShouldReturnEmployeeList() {
        Mockito.when(mockedEmployeeRepository.findAll()).
                thenReturn(mockEmployeeList);
        List<Employee> employeeList = employeeService.getAllEmployees();
        assertEquals(employeeList.size(), 2);
    }

    @Test
    public void deleteEmployeeShouldReturnNotFoundException() {
        Mockito.when(mockedEmployeeRepository.findById(any(Integer.class))).
                thenThrow( new ResourceNotFoundException("not found"));
        Assertions.assertThrows(ResourceNotFoundException.class,()->employeeService.deleteEmployee(100));
    }

    @Test
    public void deleteEmployeeShouldDeleteEmployee() {
        Mockito.when(mockedEmployeeRepository.findById(any(Integer.class))).
                thenReturn(Optional.ofNullable(mockEmployee));
        employeeService.deleteEmployee(100);
    }

}
