package com.example.discovery_country.dao.repository.auth;


import com.example.discovery_country.dao.entity.auth.User;
import com.example.discovery_country.dao.entity.auth.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserOtpRepository extends JpaRepository<UserOtp, Integer> {
    UserOtp findByUser(User user);

    @Query("select otp from UserOtp otp where otp.otp = ?1 and otp.user = ?2")

    Optional<UserOtp> findByOtpAndUser(Integer otp, User user);
}