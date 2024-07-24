//package com.example.discovery_country.model.dto.request;
//
//import com.example.discovery_country.dao.entity.NotificationEntity;
//import com.example.discovery_country.dao.entity.ReviewEntity;
//import com.example.discovery_country.dao.entity.RoleEntity;
//import com.example.discovery_country.dao.entity.RoomReservationEntity;
//import com.example.discovery_country.model.dto.response.NotificationResponse;
//import com.example.discovery_country.model.dto.response.ReviewResponse;
//import com.example.discovery_country.model.dto.response.RoomReservationResponse;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.time.LocalDateTime;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//
//
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class UserRequest  {
//
//
////
////    @NotBlank(message = "The name field can't be blank")
////    String name;
////
////    @NotBlank(message = "The username field can't be blank")
////    String username;
////
////    @NotBlank(message = "The email field can't be blank")
////    @Email(message = "Please enter email in proper format!")
////    String email;
//
//
//
//    List<NotificationResponse> notifications;
//
//    List<ReviewResponse> reviews;
//
//    List<RoomReservationResponse> roomReservations;
//
//
//    //@OneToOne(mappedBy = "user")
//   // private RefreshToken refreshToken;
//
//    //@OneToOne(mappedBy = "user")
//    //private ForgotPassword forgotPassword;
//
//
////    @NotBlank(message = "The phoneNumber field can't be blank")
////    @Pattern(regexp = "^\\+?[0-9]{10,15}$",
////            message = "Phone number must be between 10 and 15 digits, optionally starting with '+'. format=+994551212333")
//    String phoneNumber;
//
////    @NotBlank(message = "The surname field can't be blank")
//    String surname;
//
//    boolean enabled;
//
//    boolean accountNonLocked;
//
//    int failedAttempt;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
//    )
//    List<RoleEntity> roles;
//
//
//    Date lockTime;
//
//    @CreationTimestamp
//    LocalDateTime createdDate;
//    @UpdateTimestamp
//    LocalDateTime updatedDate;
//
//    boolean emailVerified;
//    String verificationToken;
//
//
//
//
//
//}