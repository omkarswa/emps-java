package com.example.ems.model;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private String dept;
    private String designation;
    private String email;
    private String phone;
    private double salary;
    private LocalDate joiningDate;

    // Constructors
    public Employee() {}

 public Employee(int id, String name, String dept, double salary) {
    this.id = id;
    this.name = name;
    this.dept = dept;
    this.salary = salary;
}


    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public LocalDate getJoiningDate() { return joiningDate; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }
}
