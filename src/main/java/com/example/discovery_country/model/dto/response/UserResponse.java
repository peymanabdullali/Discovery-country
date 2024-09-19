package com.example.discovery_country.model.dto.response;

import com.example.discovery_country.dao.entity.auth.ForgotPassword;
import com.example.discovery_country.dao.entity.auth.RefreshToken;
import com.example.discovery_country.enums.auth.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    private String name;
    private String email;
    private String surname;
    String phoneNumber;
}
