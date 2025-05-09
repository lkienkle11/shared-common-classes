package com.microservices.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String avatar;

    private boolean gender;

    private String userCode;

    private String email;

    private String phoneNumber;

    private String address;

//    private String password;

    private boolean isEnabled;

    private LocalDate dateOfBirth;

    private List<String> roles;

    private List<String> permissions;
}
