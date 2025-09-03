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
        if (admin.getPhoneNumber() != null && !admin.getPhoneNumber().isBlank()) {
            existing.setPhoneNumber(admin.getPhoneNumber());
        }
        if (admin.getAdminName() != null && !admin.getAdminName().isBlank()) {
            existing.setAdminName(admin.getAdminName());
        }
        if (admin.getMobileNumber() != null && !admin.getMobileNumber().isBlank()) {
            existing.setMobileNumber(admin.getMobileNumber());
        }
        if (admin.getAddress() != null && !admin.getAddress().isBlank()) {
            existing.setAddress(admin.getAddress());
        }
        if (admin.getCity() != null && !admin.getCity().isBlank()) {
            existing.setCity(admin.getCity());
        }
        if (admin.getState() != null && !admin.getState().isBlank()) {
            existing.setState(admin.getState());
        }
        if (admin.getRegistrationNumber() != null && !admin.getRegistrationNumber().isBlank()) {
            existing.setRegistrationNumber(admin.getRegistrationNumber());
        }
        if (admin.getAadhar() != null) {
            existing.setAadhar(admin.getAadhar());
        }
        if (admin.getPancard() != null && !admin.getPancard().isBlank()) {
            existing.setPancard(admin.getPancard());
        }
        if (admin.getCountry() != null && !admin.getCountry().isBlank()) {
            existing.setCountry(admin.getCountry());
        }

        // ✅ Update password only if new password provided
        if (admin.getPassword() != null && !admin.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(admin.getPassword()));
        }

        // ✅ Update permissions (booleans don’t need null check since default = false)
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
