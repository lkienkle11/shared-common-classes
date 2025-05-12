package com.microservices.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String avatar;

    private boolean gender;

    @JsonIgnore
    private String userCode;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String phoneNumber;

    @JsonIgnore
    private String address;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private boolean isEnabled;

    @JsonIgnore
    private boolean isConfirmed;

    @JsonIgnore
    private LocalDate dateOfBirth;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(IAppUserInfo user) {
        // Kết hợp cả roles và permissions làm authorities nếu cần
        List<GrantedAuthority> authorities = Stream
                .concat(
                        user.getRoles().stream().map(SimpleGrantedAuthority::new),
                        user.getPermissions().stream().map(SimpleGrantedAuthority::new)
                )
                .collect(Collectors.toList());

        return UserPrincipal.builder()
                .id(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .avatar(user.getAvatar())
                .gender(Boolean.TRUE.equals(user.getGender()))
                .userCode(user.getUserCode())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .dateOfBirth(user.getDateOfBirth())
                .isConfirmed(Boolean.TRUE.equals(user.getConfirmed()))
                .password(user.getPassword())
                .isEnabled(Boolean.TRUE.equals(user.getIsEnabled()))
                .authorities(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities == null ? null : new ArrayList<>(authorities);
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public List<String> getRolesBaseAuthorities() {
        return authorities == null ? new ArrayList<>() :
                authorities.stream().map(GrantedAuthority::getAuthority).toList();
    }
}
