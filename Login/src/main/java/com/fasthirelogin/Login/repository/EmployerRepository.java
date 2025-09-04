package com.fasthirelogin.Login.repository;
import com.fasthirelogin.Login.entity.FastHireEmployer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<FastHireEmployer, Long> {
    Optional<FastHireEmployer> findByEmail(String email);
}