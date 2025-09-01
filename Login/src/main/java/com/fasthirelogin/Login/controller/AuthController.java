package com.fasthirelogin.Login.controller;

import com.fasthirelogin.Login.entity.SuperAdmin;
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

    private final SuperAdminRepository repo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(SuperAdminRepository repo, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SuperAdmin loginRequest) {
        Optional<SuperAdmin> adminOpt = repo.findByEmail(loginRequest.getEmail());

        if (adminOpt.isPresent()) {
            SuperAdmin admin = adminOpt.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {

                // ✅ Directly generate token using SuperAdmin object
                String token = jwtUtil.generateToken(admin);

                return ResponseEntity.ok(Map.of(
                        "token", token,
                        "email", admin.getEmail()
                ));
            }
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

}
