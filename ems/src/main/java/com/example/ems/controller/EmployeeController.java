package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:5173") // Allow React frontend
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // ✅ Health-check endpoint
    @GetMapping("/health")
    public String healthCheck() {
        return "✅ Employee API is running successfully!";
    }

    // ✅ Get all employees
    @GetMapping
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    // ✅ Add a new employee
    @PostMapping
    public Employee add(@RequestBody Employee e) {
        return service.addEmployee(e);
    }

    // ✅ Update employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable int id, @RequestBody Employee e) {
        return service.updateEmployee(id, e)
                .map(ResponseEntity::ok)                // 200 OK + updated employee
                .orElse(ResponseEntity.notFound().build()); // 404 if not found
    }

    // ✅ Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        boolean removed = service.deleteEmployee(id);
        if (removed) {
            return ResponseEntity.ok("✅ Employee deleted successfully");
        } else {
            return ResponseEntity.status(404).body("❌ Employee not found");
        }
    }
}
