package com.fasthirelogin.Login.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FastHireSuperAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔑 Basic authentication
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    // 🔑 Personal details
    private String adminName;
    private String mobileNumber;
    private String alternatePhone;
    private String address;
    private String city;
    private String district;
    private String state;
    private String country;
    private String pinCode;

    // 🔑 Identity
    private Long aadhar;
    private String pancard;

    //Gst details
    private String gstNumber;

    // 🔑 Permissions
    private boolean canCreate;
    private boolean canUpdate;
    private boolean canDelete;
    private boolean canRead;

    // 🔑 Preferences
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private Role role = Role.SUPERADMIN;

}
