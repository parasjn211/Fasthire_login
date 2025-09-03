package com.fasthirelogin.Login.serviceimpl;

import com.fasthirelogin.Login.entity.SuperAdmin;
import com.fasthirelogin.Login.repository.SuperAdminRepository;
import com.fasthirelogin.Login.service.SuperAdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

    private final SuperAdminRepository repo;
    private final PasswordEncoder passwordEncoder;

    public SuperAdminServiceImpl(SuperAdminRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SuperAdmin create(SuperAdmin admin) {
        // ✅ Encode password before saving
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return repo.save(admin);
    }

    @Override
    public List<SuperAdmin> getAll() {
        return repo.findAll();
    }

    @Override
    public SuperAdmin update(Long id, SuperAdmin admin) {
        SuperAdmin existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("SuperAdmin not found with id: " + id));

        // ✅ Update only if not null (partial update)
        if (admin.getEmail() != null && !admin.getEmail().isBlank()) {
            existing.setEmail(admin.getEmail());
        }
        if (admin.getPassword() != null && !admin.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(admin.getPassword()));
        }
        if (admin.getAdminName() != null && !admin.getAdminName().isBlank()) {
            existing.setAdminName(admin.getAdminName());
        }
        if (admin.getMobileNumber() != null && !admin.getMobileNumber().isBlank()) {
            existing.setMobileNumber(admin.getMobileNumber());
        }
        if (admin.getAlternatePhone() != null && !admin.getAlternatePhone().isBlank()) {
            existing.setAlternatePhone(admin.getAlternatePhone());
        }
        if (admin.getAddress() != null && !admin.getAddress().isBlank()) {
            existing.setAddress(admin.getAddress());
        }
        if (admin.getCity() != null && !admin.getCity().isBlank()) {
            existing.setCity(admin.getCity());
        }
        if (admin.getDistrict() != null && !admin.getDistrict().isBlank()) {
            existing.setDistrict(admin.getDistrict());
        }
        if (admin.getState() != null && !admin.getState().isBlank()) {
            existing.setState(admin.getState());
        }
        if (admin.getCountry() != null && !admin.getCountry().isBlank()) {
            existing.setCountry(admin.getCountry());
        }
        if (admin.getPinCode() != null && !admin.getPinCode().isBlank()) {   // ✅ New field update
            existing.setPinCode(admin.getPinCode());
        }
        if (admin.getAadhar() != null) {
            existing.setAadhar(admin.getAadhar());
        }
        if (admin.getPancard() != null && !admin.getPancard().isBlank()) {
            existing.setPancard(admin.getPancard());
        }
        if (admin.getOrganizationName() != null && !admin.getOrganizationName().isBlank()) {
            existing.setOrganizationName(admin.getOrganizationName());
        }
        if (admin.getDesignation() != null && !admin.getDesignation().isBlank()) {
            existing.setDesignation(admin.getDesignation());
        }
        if (admin.getProfileImageUrl() != null && !admin.getProfileImageUrl().isBlank()) {
            existing.setProfileImageUrl(admin.getProfileImageUrl());
        }

        // ✅ Update permissions (booleans directly taken from request)
        existing.setCanCreate(admin.isCanCreate());
        existing.setCanUpdate(admin.isCanUpdate());
        existing.setCanDelete(admin.isCanDelete());
        existing.setCanRead(admin.isCanRead());

        // ✅ Update role if provided
        if (admin.getRole() != null) {
            existing.setRole(admin.getRole());
        }

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("SuperAdmin not found with id: " + id);
        }
        repo.deleteById(id);
    }
}
