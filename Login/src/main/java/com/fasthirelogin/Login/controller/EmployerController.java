package com.fasthirelogin.Login.controller;
import com.fasthirelogin.Login.entity.Employer;
import com.fasthirelogin.Login.service.EmployerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employers")
public class EmployerController {

    private final EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }


    // ✅ Update Employer
    @PutMapping("/{id}")
    public ResponseEntity<Employer> updateEmployer(@PathVariable Long id, @RequestBody Employer employer) {
        return ResponseEntity.ok(employerService.updateEmployer(id, employer));
    }

    // ✅ Delete Employer
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployer(@PathVariable Long id) {
        employerService.deleteEmployer(id);
        return ResponseEntity.ok("Employer deleted successfully");
    }

    // ✅ Get Employer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employer> getEmployerById(@PathVariable Long id) {
        return employerService.getEmployerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get All Employers
    @GetMapping
    public ResponseEntity<List<Employer>> getAllEmployers() {
        return ResponseEntity.ok(employerService.getAllEmployers());
    }
}

