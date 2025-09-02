package com.fasthirelogin.Login.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String contactPerson;
    private String email;
    private String password;

    private boolean isApproved;

    // ✅ Permissions (like SuperAdmin)
    private boolean canCreate;
    private boolean canUpdate;
    private boolean canDelete;
    private boolean canRead;

    @Enumerated(EnumType.STRING)
    private Role role = Role.EMPLOYER;

}
