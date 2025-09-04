package com.fasthirelogin.Login.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FastHireEmployer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔑 Basic info
    private String companyName;
    private String contactPerson;
    private String email;
    private String password;

    // 🔑 Company details
    private String companyWebsite;
    private String companyLogoUrl;
    private String industry;
    private String companySize;  // e.g. "50-200 employees"
    private String foundedYear;
    private String aboutCompany;

    // 🔑 Contact info
    private String phoneNumber;
    private String alternatePhone;
    private String address;
    private String city;
    private String district;
    private String state;
    private String country;
    private String pinCode;

    // 🔑 Compliance
    private String registrationNumber;
    private String gstNumber;
    private boolean documentsVerified;

    // 🔑 Permissions
    private boolean isApproved;
    private boolean canCreate;
    private boolean canUpdate;
    private boolean canDelete;
    private boolean canRead;

    // 🔑 Job posting details
    private int jobPostLimit = 10;  // Default limit
    private int remainingPosts = 10;

    @Enumerated(EnumType.STRING)
    private Role role = Role.EMPLOYER;

}
