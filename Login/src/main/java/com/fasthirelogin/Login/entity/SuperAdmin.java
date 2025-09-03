package com.fasthirelogin.Login.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuperAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String  phoneNumber;
    @Column(nullable = false)
    private String password;
    private String adminName;
    private String mobileNumber;
    private String address;
    private String city;
    private String state;
    private Long aadhar;
    private String pancard;
    private String country;

    private boolean canCreate;
    private boolean canUpdate;
    private boolean canDelete;
    private boolean canRead;

    @Enumerated(EnumType.STRING)
    private Role role = Role.SUPERADMIN;

}

