package com.fasthirelogin.Login.service;


import com.fasthirelogin.Login.entity.FastHireEmployer;

import java.util.List;
import java.util.Optional;

public interface EmployerService {
    FastHireEmployer createEmployer(FastHireEmployer employer);
    FastHireEmployer updateEmployer(Long id, FastHireEmployer employer);
    void deleteEmployer(Long id);
    Optional<FastHireEmployer> getEmployerById(Long id);
    List<FastHireEmployer> getAllEmployers();
}

