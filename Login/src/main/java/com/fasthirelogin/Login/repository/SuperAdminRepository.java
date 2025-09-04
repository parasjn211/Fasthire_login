package com.fasthirelogin.Login.repository;

import com.fasthirelogin.Login.entity.FastHireSuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuperAdminRepository  extends JpaRepository<FastHireSuperAdmin, Long> {
    Optional<FastHireSuperAdmin> findByEmail(String email);
}
