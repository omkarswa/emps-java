package com.example.ems.service;

import com.example.ems.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private int nextId = 11; // since we already have 10 employees

    public EmployeeService() {
        // Realistic dummy employee data
        employees.add(new Employee(1, "Alice Johnson", "Engineering", 75000));
        employees.add(new Employee(2, "Bob Smith", "Human Resources", 60000));
        employees.add(new Employee(3, "Carol Martinez", "Finance", 82000));
        employees.add(new Employee(4, "David Lee", "Engineering", 70000));
        employees.add(new Employee(5, "Eva Brown", "Marketing", 65000));
        employees.add(new Employee(6, "Frank Wilson", "Sales", 55000));
        employees.add(new Employee(7, "Grace Kim", "Engineering", 78000));
        employees.add(new Employee(8, "Henry Davis", "Finance", 72000));
        employees.add(new Employee(9, "Isabella Lopez", "Customer Support", 50000));
        employees.add(new Employee(10, "Jack Patel", "IT", 69000));
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // Add a new employee with auto-generated ID
    public Employee addEmployee(Employee e) {
        e.setId(nextId++);
        employees.add(e);
        return e;
    }

    // Update an existing employee
    public Optional<Employee> updateEmployee(int id, Employee updated) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .map(e -> {
                    e.setName(updated.getName());
                    e.setDept(updated.getDept());
                    e.setSalary(updated.getSalary());
                    return e;
                });
    }

    // Delete employee by ID
    public boolean deleteEmployee(int id) {
        return employees.removeIf(e -> e.getId() == id);
    }
}
