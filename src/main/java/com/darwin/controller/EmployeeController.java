package com.darwin.controller;

import com.darwin.model.Employee;
import com.darwin.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
@Api(value = " employee-api ", tags = "Employee API")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/employees")
    @ApiOperation(value= "Creates Employee", response = Employee.class)
    public ResponseEntity<Employee> createEmployee
            (@Valid @RequestBody Employee employee){
        Employee employeeResponse = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/employees/{id}")
    @ApiOperation(value= "Get Employee", response = Employee.class)
    public ResponseEntity<Employee> getEmployee(
    @PathVariable(value = "id")  Integer employeeId){
        Employee employee = employeeService.getEmployee(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping(value = "/employees")
    @ApiOperation(value= "Get All Employees", response = List.class)
    public ResponseEntity<List<Employee>> getAllEmployee(){

        List<Employee> employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @DeleteMapping(value = "/employees/{id}")
    @ApiOperation(value= "Delete Employee")
    public ResponseEntity<Integer> deleteEmployee(
            @PathVariable(value = "id")  Integer employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(employeeId, HttpStatus.OK);
    }
}
