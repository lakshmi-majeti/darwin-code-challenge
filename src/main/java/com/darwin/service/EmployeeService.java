package com.darwin.service;

import com.darwin.exception.ResourceNotFoundException;
import com.darwin.model.Employee;
import com.darwin.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee){
        Employee employeeResponse = employeeRepository.save(employee);
        return employeeResponse;
    }

    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    public Employee getEmployee(Integer employeeId) throws ResourceNotFoundException {
       return employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("No Employee found for " + employeeId));
    }

    public void deleteEmployee(Integer employeeId) throws ResourceNotFoundException{
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent())
        {
            employeeRepository.deleteById(employeeId);
        } else {
            throw new ResourceNotFoundException("No employee record exist for given id "+ employeeId);
        }
    }

}
