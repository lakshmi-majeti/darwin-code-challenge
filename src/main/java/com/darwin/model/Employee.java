package com.darwin.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "full name is mandatory")
    @Column(name = "full_name",  nullable = false)
    private String fullName;

    @NotBlank(message = "email is mandatory")
    @Column(name = "email",  nullable = false)
    private String email;

    @NotBlank(message = " password cannot be null")
    @Column(name = "password",  nullable = false)
    private String password;

    @NotBlank(message = " phoneNumber cannot be null")
    @Column(name = "phone_number",  nullable = false)
    private String phoneNumber;

    @Column(name = "department")
    private String department;

    @Column(name = "job_title")
    private String jobTitle;

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", department='" + department + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
