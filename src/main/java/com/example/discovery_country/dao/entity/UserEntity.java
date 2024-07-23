package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

//    @NotBlank(message = "The name field can't be blank")
    String name;

//    @NotBlank(message = "The username field can't be blank")
    @Column(unique = true)
    String username;

//    @NotBlank(message = "The email field can't be blank")
//    @Column(unique = true)
//    @Email(message = "Please enter email in proper format!")
    String email;

//    @NotBlank(message = "The password field can't be blank")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{7,}$",
//            message = "Password must be at least 7 characters long, contain at least one uppercase letter, one lowercase letter, and one number.")
    String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<NotificationEntity> notificationsEntities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<ReviewEntity> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<RoomReservationEntity> roomReservations;


    //@OneToOne(mappedBy = "user")
   // private RefreshToken refreshToken;

    //@OneToOne(mappedBy = "user")
    //private ForgotPassword forgotPassword;


//    @NotBlank(message = "The phoneNumber field can't be blank")
//    @Pattern(regexp = "^\\+?[0-9]{10,15}$",
//            message = "Phone number must be between 10 and 15 digits, optionally starting with '+'. format=+994551212333")
    String phoneNumber;

//    @NotBlank(message = "The surname field can't be blank")
    String surname;

    boolean enabled;

    boolean accountNonLocked;

    int failedAttempt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    List<RoleEntity> roles;


    Date lockTime;

    @CreationTimestamp
    LocalDateTime createdDate;
    @UpdateTimestamp
    LocalDateTime updatedDate;

    boolean emailVerified;
    String verificationToken;


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (lockTime == null) {
            return true;  // Account is not locked if lockTime is null
        }

        long lockTimeInMillis = lockTime.getTime();
        long currentTimeInMillis = System.currentTimeMillis();
        long lockDurationInMillis = 60 * 1000;  // 1 minute

        return lockTimeInMillis + lockDurationInMillis < currentTimeInMillis;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}