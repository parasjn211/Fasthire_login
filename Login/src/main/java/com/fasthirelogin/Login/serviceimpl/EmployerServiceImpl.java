package com.fasthirelogin.Login.serviceimpl;
import com.fasthirelogin.Login.entity.Employer;
import com.fasthirelogin.Login.repository.EmployerRepository;
import com.fasthirelogin.Login.service.EmployerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;

    public EmployerServiceImpl(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Override
    public Employer createEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    @Override
    public Employer updateEmployer(Long id, Employer employer) {
        return employerRepository.findById(id)
                .map(existingEmployer -> {
                    existingEmployer.setCompanyName(employer.getCompanyName());
                    existingEmployer.setContactPerson(employer.getContactPerson());
                    existingEmployer.setEmail(employer.getEmail());
                    existingEmployer.setPassword(employer.getPassword());
                    existingEmployer.setApproved(employer.isApproved());
                    existingEmployer.setCanCreate(employer.isCanCreate());
                    existingEmployer.setCanUpdate(employer.isCanUpdate());
                    existingEmployer.setCanDelete(employer.isCanDelete());
                    existingEmployer.setCanRead(employer.isCanRead());
                    return employerRepository.save(existingEmployer);
                }).orElseThrow(() -> new RuntimeException("Employer not found with id: " + id));
    }

    @Override
    public void deleteEmployer(Long id) {
        if (!employerRepository.existsById(id)) {
            throw new RuntimeException("Employer not found with id: " + id);
        }
        employerRepository.deleteById(id);
    }

    @Override
    public Optional<Employer> getEmployerById(Long id) {
        return employerRepository.findById(id);
    }

    @Override
    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }
}
