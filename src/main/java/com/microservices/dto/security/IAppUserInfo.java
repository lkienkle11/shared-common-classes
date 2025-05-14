package com.microservices.dto.security;

import java.time.LocalDate;
import java.util.List;

public interface IAppUserInfo {
    Long getUserId();

    String getFirstName();

    String getLastName();

    String getUserName();

    String getAvatar();

    String getUserTz();

    Boolean getGender();

    String getUserCode();

    String getEmail();

    String getPhoneNumber();

    String getAddress();

    String getPassword();

    Boolean getIsEnabled();

    Boolean getConfirmed();

    LocalDate getDateOfBirth();

    List<String> getRoles();

    List<String> getPermissions();
}
