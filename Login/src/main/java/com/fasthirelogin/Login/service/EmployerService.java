package com.fasthirelogin.Login.service;


import com.fasthirelogin.Login.entity.Employer;

import java.util.List;
import java.util.Optional;

public interface EmployerService {
    Employer createEmployer(Employer employer);
    Employer updateEmployer(Long id, Employer employer);
    void deleteEmployer(Long id);
    Optional<Employer> getEmployerById(Long id);
    List<Employer> getAllEmployers();
}

