package com.microservices.dto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo implements IAppUserInfo {
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String avatar;

    private boolean gender;

    private String userTz;

    private String userCode;

    private String email;

    private String phoneNumber;

    private String address;

//    private String password;

    private Boolean isEnabled;

    private Boolean confirmed;

    private LocalDate dateOfBirth;

    private List<String> roles;

    private List<String> permissions;

    @Override
    public Long getUserId() {
        return id;
    }

    @Override
    public Boolean getGender() {
        return gender;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public Boolean getIsEnabled() {
        return !Objects.isNull(isEnabled) && isEnabled;
    }

    @Override
    public Boolean getConfirmed() {
        return !Objects.isNull(confirmed) && confirmed;
    }

    public static UserInfo from(UserPrincipal p) {
        List<String> roles = p.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return UserInfo.builder()
                .id(p.getId())
                .userName(p.getUsername())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .avatar(p.getAvatar())
                .gender(p.isGender())
                .userTz(p.getUserTz())
                .userCode(p.getUserCode())
                .email(p.getEmail())
                .phoneNumber(p.getPhoneNumber())
                .address(p.getAddress())
                .isEnabled(p.isEnabled())
                .confirmed(p.isConfirmed())
                .dateOfBirth(p.getDateOfBirth())
                .roles(roles)
                .permissions(List.of())
                .build();
    }
}
