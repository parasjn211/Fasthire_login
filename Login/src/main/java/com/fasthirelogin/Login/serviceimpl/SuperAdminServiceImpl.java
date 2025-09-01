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

        // ✅ Update fields
        existing.setEmail(admin.getEmail());

        if (admin.getPassword() != null && !admin.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(admin.getPassword()));
        }

        // ✅ Update permissions
        existing.setCanCreate(admin.isCanCreate());
        existing.setCanUpdate(admin.isCanUpdate());
        existing.setCanDelete(admin.isCanDelete());
        existing.setCanRead(admin.isCanRead());

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
