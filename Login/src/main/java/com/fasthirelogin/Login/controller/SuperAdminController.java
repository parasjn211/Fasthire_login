package com.fasthirelogin.Login.controller;
import com.fasthirelogin.Login.entity.SuperAdmin;
import com.fasthirelogin.Login.service.SuperAdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superadmin")
public class SuperAdminController {

    private final SuperAdminService service;

    public SuperAdminController(SuperAdminService service) {
        this.service = service;
    }

    @PostMapping("/createSuperAdmin")
    public SuperAdmin create(@RequestBody SuperAdmin admin) {
        return service.create(admin);
    }

    @GetMapping("/getAllSuperAdmins")
    public List<SuperAdmin> getAll() {
        return service.getAll();
    }

    @PutMapping("/updateSuperAdmin/{id}")
    public SuperAdmin update(@PathVariable Long id, @RequestBody SuperAdmin admin) {
        return service.update(id, admin);
    }

    @DeleteMapping("/deleteSuperAdmin/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

