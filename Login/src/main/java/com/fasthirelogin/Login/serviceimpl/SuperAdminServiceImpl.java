package com.fasthirelogin.Login.serviceimpl;

import com.fasthirelogin.Login.entity.FastHireSuperAdmin;
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
    public FastHireSuperAdmin create(FastHireSuperAdmin admin) {
        // ✅ Encode password before saving
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return repo.save(admin);
    }

    @Override
    public List<FastHireSuperAdmin> getAll() {
        return repo.findAll();
    }

    @Override
    public FastHireSuperAdmin update(Long id, FastHireSuperAdmin admin) {
        FastHireSuperAdmin existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("SuperAdmin not found with id: " + id));

        // ✅ Basic authentication
        if (admin.getEmail() != null && !admin.getEmail().isBlank()) {
            existing.setEmail(admin.getEmail());
        }
        if (admin.getPassword() != null && !admin.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(admin.getPassword()));
        }

        // ✅ Personal details
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
        if (admin.getPinCode() != null && !admin.getPinCode().isBlank()) {
            existing.setPinCode(admin.getPinCode());
        }

        // ✅ Identity
        if (admin.getAadhar() != null) {
            existing.setAadhar(admin.getAadhar());
        }
        if (admin.getPancard() != null && !admin.getPancard().isBlank()) {
            existing.setPancard(admin.getPancard());
        }

        // ✅ GST details
        if (admin.getGstNumber() != null && !admin.getGstNumber().isBlank()) {
            existing.setGstNumber(admin.getGstNumber());
        }

        // ✅ Preferences
        if (admin.getProfileImageUrl() != null && !admin.getProfileImageUrl().isBlank()) {
            existing.setProfileImageUrl(admin.getProfileImageUrl());
        }

        // ✅ Permissions
        existing.setCanCreate(admin.isCanCreate());
        existing.setCanUpdate(admin.isCanUpdate());
        existing.setCanDelete(admin.isCanDelete());
        existing.setCanRead(admin.isCanRead());

        // ✅ Role
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
