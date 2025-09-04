package com.fasthirelogin.Login.service;

import com.fasthirelogin.Login.entity.FastHireSuperAdmin;

import java.util.List;

public interface SuperAdminService {
    FastHireSuperAdmin create(FastHireSuperAdmin admin);
    List<FastHireSuperAdmin> getAll();
    FastHireSuperAdmin update(Long id, FastHireSuperAdmin admin);
    void delete(Long id);
}
