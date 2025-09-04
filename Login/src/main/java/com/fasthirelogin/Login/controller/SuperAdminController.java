package com.fasthirelogin.Login.controller;
import com.fasthirelogin.Login.entity.FastHireSuperAdmin;
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
    public FastHireSuperAdmin create(@RequestBody FastHireSuperAdmin admin) {
        return service.create(admin);
    }

    @GetMapping("/getAllSuperAdmins")
    public List<FastHireSuperAdmin> getAll() {
        return service.getAll();
    }

    @PutMapping("/updateSuperAdmin/{id}")
    public FastHireSuperAdmin update(@PathVariable Long id, @RequestBody FastHireSuperAdmin admin) {
        return service.update(id, admin);
    }

    @DeleteMapping("/deleteSuperAdmin/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

