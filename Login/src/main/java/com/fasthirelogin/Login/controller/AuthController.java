package com.fasthirelogin.Login.controller;

import com.fasthirelogin.Login.entity.Employer;
import com.fasthirelogin.Login.entity.SuperAdmin;
import com.fasthirelogin.Login.repository.EmployerRepository;
import com.fasthirelogin.Login.repository.SuperAdminRepository;
import com.fasthirelogin.Login.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final SuperAdminRepository superAdminRepo;
    private final EmployerRepository employerRepo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(SuperAdminRepository superAdminRepo,
                          EmployerRepository employerRepo,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.superAdminRepo = superAdminRepo;
        this.employerRepo = employerRepo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ SuperAdmin Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SuperAdmin loginRequest) {
        Optional<SuperAdmin> adminOpt = superAdminRepo.findByEmail(loginRequest.getEmail());

        if (adminOpt.isPresent()) {
            SuperAdmin admin = adminOpt.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {
                String token = jwtUtil.generateToken(admin);
                return ResponseEntity.ok(Map.of("token", token, "email", admin.getEmail(), "role", "SUPERADMIN"));
            }
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    // ✅ Employer Registration
    @PostMapping("/registerEmployer")
    public ResponseEntity<?> registerEmployer(@RequestBody Employer employer) {
        employer.setPassword(passwordEncoder.encode(employer.getPassword()));
        employer.setApproved(false); // default false until approved by SuperAdmin
        Employer saved = employerRepo.save(employer);
        return ResponseEntity.ok(saved);
    }

    // ✅ Employer Login
    @PostMapping("/loginEmployer")
    public ResponseEntity<?> loginEmployer(@RequestBody Employer loginRequest) {
        Optional<Employer> employerOpt = employerRepo.findByEmail(loginRequest.getEmail());

        if (employerOpt.isPresent()) {
            Employer employer = employerOpt.get();
            System.out.println("DB Password: " + employer.getPassword()); // hashed
            System.out.println("Login Password: " + loginRequest.getPassword()); // raw

            if (passwordEncoder.matches(loginRequest.getPassword(), employer.getPassword())) {
                String token = jwtUtil.generateToken(employer);
                return ResponseEntity.ok(Map.of(
                        "token", token,
                        "email", employer.getEmail(),
                        "role", "EMPLOYER"
                ));
            } else {
                System.out.println("❌ Password mismatch");
            }
        } else {
            System.out.println("❌ Employer not found with email " + loginRequest.getEmail());
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

}
