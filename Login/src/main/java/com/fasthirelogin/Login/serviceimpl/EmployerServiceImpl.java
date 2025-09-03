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

                    // 🔑 Basic Info
                    if (employer.getCompanyName() != null && !employer.getCompanyName().isBlank()) {
                        existingEmployer.setCompanyName(employer.getCompanyName());
                    }
                    if (employer.getContactPerson() != null && !employer.getContactPerson().isBlank()) {
                        existingEmployer.setContactPerson(employer.getContactPerson());
                    }
                    if (employer.getEmail() != null && !employer.getEmail().isBlank()) {
                        existingEmployer.setEmail(employer.getEmail());
                    }
                    if (employer.getPassword() != null && !employer.getPassword().isBlank()) {
                        existingEmployer.setPassword(employer.getPassword());
                    }

                    // 🔑 Company Details
                    if (employer.getCompanyWebsite() != null && !employer.getCompanyWebsite().isBlank()) {
                        existingEmployer.setCompanyWebsite(employer.getCompanyWebsite());
                    }
                    if (employer.getCompanyLogoUrl() != null && !employer.getCompanyLogoUrl().isBlank()) {
                        existingEmployer.setCompanyLogoUrl(employer.getCompanyLogoUrl());
                    }
                    if (employer.getIndustry() != null && !employer.getIndustry().isBlank()) {
                        existingEmployer.setIndustry(employer.getIndustry());
                    }
                    if (employer.getCompanySize() != null && !employer.getCompanySize().isBlank()) {
                        existingEmployer.setCompanySize(employer.getCompanySize());
                    }
                    if (employer.getFoundedYear() != null && !employer.getFoundedYear().isBlank()) {
                        existingEmployer.setFoundedYear(employer.getFoundedYear());
                    }
                    if (employer.getAboutCompany() != null && !employer.getAboutCompany().isBlank()) {
                        existingEmployer.setAboutCompany(employer.getAboutCompany());
                    }

                    // 🔑 Contact Info
                    if (employer.getPhoneNumber() != null && !employer.getPhoneNumber().isBlank()) {
                        existingEmployer.setPhoneNumber(employer.getPhoneNumber());
                    }
                    if (employer.getAlternatePhone() != null && !employer.getAlternatePhone().isBlank()) {
                        existingEmployer.setAlternatePhone(employer.getAlternatePhone());
                    }
                    if (employer.getAddress() != null && !employer.getAddress().isBlank()) {
                        existingEmployer.setAddress(employer.getAddress());
                    }
                    if (employer.getCity() != null && !employer.getCity().isBlank()) {
                        existingEmployer.setCity(employer.getCity());
                    }
                    if (employer.getDistrict() != null && !employer.getDistrict().isBlank()) {
                        existingEmployer.setDistrict(employer.getDistrict());
                    }
                    if (employer.getState() != null && !employer.getState().isBlank()) {
                        existingEmployer.setState(employer.getState());
                    }
                    if (employer.getCountry() != null && !employer.getCountry().isBlank()) {
                        existingEmployer.setCountry(employer.getCountry());
                    }
                    if (employer.getPinCode() != null && !employer.getPinCode().isBlank()) {
                        existingEmployer.setPinCode(employer.getPinCode());
                    }

                    // 🔑 Compliance
                    if (employer.getRegistrationNumber() != null && !employer.getRegistrationNumber().isBlank()) {
                        existingEmployer.setRegistrationNumber(employer.getRegistrationNumber());
                    }
                    if (employer.getGstNumber() != null && !employer.getGstNumber().isBlank()) {
                        existingEmployer.setGstNumber(employer.getGstNumber());
                    }
                    existingEmployer.setDocumentsVerified(employer.isDocumentsVerified());

                    // 🔑 Job posting details
                    existingEmployer.setJobPostLimit(employer.getJobPostLimit());
                    existingEmployer.setRemainingPosts(employer.getRemainingPosts());

                    // 🔑 Permissions & Approval
                    existingEmployer.setApproved(employer.isApproved());
                    existingEmployer.setCanCreate(employer.isCanCreate());
                    existingEmployer.setCanUpdate(employer.isCanUpdate());
                    existingEmployer.setCanDelete(employer.isCanDelete());
                    existingEmployer.setCanRead(employer.isCanRead());

                    // 🔑 Role
                    if (employer.getRole() != null) {
                        existingEmployer.setRole(employer.getRole());
                    }

                    return employerRepository.save(existingEmployer);
                })
                .orElseThrow(() -> new RuntimeException("Employer not found with id: " + id));
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
