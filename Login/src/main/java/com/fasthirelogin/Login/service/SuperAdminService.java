package com.fasthirelogin.Login.service;

import com.fasthirelogin.Login.entity.SuperAdmin;

import java.util.List;

public interface SuperAdminService {
    SuperAdmin create(SuperAdmin admin);
    List<SuperAdmin> getAll();
    SuperAdmin update(Long id, SuperAdmin admin);
    void delete(Long id);
}
